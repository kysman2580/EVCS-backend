package com.example.evcs.oauth.kakao.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.evcs.member.model.dao.MemberMapper;
import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.member.model.vo.Member;
import com.example.evcs.oauth.kakao.model.dao.SocialMemberMapper;
import com.example.evcs.oauth.kakao.model.dto.KakaoDTO;
import com.example.evcs.oauth.kakao.model.dto.LoginMemberDTO;
import com.example.evcs.oauth.kakao.model.dto.SocialMemberDTO;
import com.example.evcs.oauth.util.KakaoUtil;
import com.example.evcs.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    
    private final KakaoUtil kakaoUtil;
    private final MemberMapper memberMapper; // EV_MEMBER 접근용 DAO
    private final SocialMemberMapper socialMemberMapper; // EV_SOCIAL_MEMBER 접근용 DAO
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);

    @Override
    public LoginMemberDTO oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        try {
            // 1. 카카오 토큰과 프로필 요청
            KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
            KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

            String email = kakaoProfile.getKakao_account().getEmail();
            String nickname = kakaoProfile.getKakao_account().getProfile().getNickname();
            String providerId = kakaoProfile.getId().toString();

            logger.info("Kakao Login Request: Email = {}, Nickname = {}, ProviderId = {}", email, nickname, providerId);

            // 2. 소셜 회원 조회 (EV_SOCIAL_MEMBER)
            SocialMemberDTO socialMember = (SocialMemberDTO) socialMemberMapper.getSocialMemberByProviderId(providerId);
            MemberDTO member = null;
            
            if (socialMember == null) {
                logger.info("No social member found for providerId: {}", providerId);

                // 3. 기존 회원 조회 (EV_MEMBER)
                member = memberMapper.getMemberByEmail(email);

                if (member == null) {
                    logger.info("No member found for email: {}, registering new member.", email);

                    // 새 회원 가입 처리
                    Member memberEntity = Member.builder()
                                                .email(email)
                                                .memberPw(passwordEncoder.encode(providerId)) // 비밀번호는 providerId로 설정 (소셜 로그인 전용)
                                                .memberNickname(nickname)
                                                .role("USER")
                                                .emailVerified('Y')  // 이메일 인증 여부 설정
                                                .memberStatus("Y")   // 회원 상태 활성화
                                                .build();
                    memberMapper.signUp(memberEntity);  // 새로운 회원 DB에 저장

                    // 새로 가입된 회원 정보 조회 (필수)
                    member = memberMapper.getMemberByEmail(email); // 회원 가입 후 다시 조회하여 memberNo 가져오기
                    logger.info("New member registered: {}", member);
                }

                // 4. 소셜 로그인 정보 추가 (EV_SOCIAL_MEMBER)
                socialMember = new SocialMemberDTO();
                socialMember.setMemberNo(member.getMemberNo());  // memberNo가 제대로 설정되도록
                socialMember.setProvider("kakao");
                socialMember.setProviderId(providerId);
                socialMember.setEmail(email);

                // 소셜 회원 등록
                socialMemberMapper.insertSocialMember(socialMember);
                logger.info("New social member registered for providerId: {}", providerId);
                
                // 소셜 회원 정보 다시 조회하여 최신 정보 가져오기
                socialMember = (SocialMemberDTO) socialMemberMapper.getSocialMemberByProviderId(providerId);
                if (socialMember == null) {
                    logger.error("Failed to retrieve social member after insertion");
                    throw new RuntimeException("Failed to register social member");
                }
            } else {
                logger.info("Found existing social member for providerId: {}", providerId);
                // 기존 소셜 회원이 있는 경우, 해당 회원의 memberNo를 이용해 회원 정보 조회
                member = memberMapper.getMemberByMemberNo(socialMember.getMemberNo());
                if (member == null) {
                    logger.error("Member not found for socialMember: {}", socialMember);
                    throw new RuntimeException("Member not found for social account");
                }
            }

            // 5. JWT 토큰 발급
            String accessToken = jwtUtil.getAccessToken(email);  // 이메일을 사용하여 토큰 발급
            String refreshToken = jwtUtil.getRefreshToken(accessToken);
            httpServletResponse.setHeader("Authorization", accessToken);
            
            
            // socialMember의 memberNo가 null인지 확인
            if (socialMember.getMemberNo() == null) {
                logger.error("socialMember.getMemberNo() is null! Social member details: {}", socialMember);
                throw new RuntimeException("Member number not found");
            }

            // 6. 로그인 결과 DTO 생성
            LoginMemberDTO logMemberDTO = new LoginMemberDTO(
                    socialMember.getMemberNo(),  // 소셜 회원 번호 사용
                    member.getEmail(),           // 회원 이메일
                    nickname,  					 // 회원 닉네임
                    member.getRole(),			 // 권한
                    accessToken,
                    refreshToken
            );
            
            return logMemberDTO;

        } catch (Exception e) {
            logger.error("Error occurred during OAuth login: {}", e.getMessage(), e);
            throw new RuntimeException("OAuth login failed, please try again later.");
        }
    }
}
