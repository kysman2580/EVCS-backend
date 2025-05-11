package com.example.evcs.mail.model.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evcs.exception.EmailAlreadyExistsException;
import com.example.evcs.exception.EmailNotExistsException;
import com.example.evcs.exception.EmailNotFoundException;
import com.example.evcs.exception.MissingEmailException;
import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.dto.PassWordEmailVerifyDTO;
import com.example.evcs.mail.model.dao.EmailMapper;
import com.example.evcs.member.model.dao.MemberMapper;
import com.example.evcs.member.model.dto.MemberDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender mailSender;
	private final EmailMapper emailMapper;
	private final MemberMapper memberMapper;
	

	@Override
	@Transactional
	public void sendVerificationCode(EmailVerifyDTO emailVerifyDTO) {
	    String email = emailVerifyDTO.getEmail();

	    // 이메일 검증
	    if (email == null || email.trim().isEmpty()) {
	        throw new EmailNotFoundException("이메일을 입력해주세요.");
	    }

	    // 이미 이메일이 존재하면 인증 코드 전송을 막음 (회원가입 시)
	    MemberDTO emailExists = memberMapper.getMemberByEmail(email);
	    if (emailExists != null) {
	        throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다. 인증 코드를 전송할 수 없습니다.");
	    }

	    // 인증 코드 생성 및 유효기간 설정
	    String code = generateRandomCode();
	    Timestamp expiresAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)); // 5분 유효

	    emailVerifyDTO.setCode(code);
	    emailVerifyDTO.setExpiresAt(expiresAt);
	    emailVerifyDTO.setVerified('N');

	 // 이메일 인증 테이블에 이메일 존재 여부 확인
	    boolean existsInVerification = emailMapper.existsByEmail(email) > 0;

	    if (existsInVerification) {
	        emailMapper.updateCode(emailVerifyDTO); // UPDATE
	    } else {
	        emailMapper.saveCode(emailVerifyDTO); // INSERT
	    }

	    // 이메일 전송을 비동기로 처리
	    sendEmailAsync(email, code);
	}

	@Override
	@Transactional
	public void resendVerificationCode(EmailVerifyDTO emailVerifyDTO) {
		String email = emailVerifyDTO.getEmail();
	    // 이메일 검증
	    if (email == null || email.trim().isEmpty()) {
	        throw new EmailNotFoundException("이메일을 입력해주세요.");
	    }

	    // 이메일이 존재하는지 확인
	    boolean emailExists = emailMapper.existsByEmail(email) > 0;
	    if (!emailExists) {
	        throw new EmailNotExistsException("등록된 이메일이 없습니다. 이메일 인증을 먼저 진행해주세요.");
	    }

	    // 기존 인증 코드 갱신
	    String code = generateRandomCode();
	    Timestamp expiresAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)); // 5분 유효

	    EmailVerifyDTO reSendEmailVerifyDTO = new EmailVerifyDTO();
	    reSendEmailVerifyDTO.setEmail(email);
	    reSendEmailVerifyDTO.setCode(code);
	    reSendEmailVerifyDTO.setExpiresAt(expiresAt);
	    reSendEmailVerifyDTO.setVerified('N');

	    // 기존 인증 코드 업데이트
	    emailMapper.updateCode(reSendEmailVerifyDTO);

	    // 이메일 전송을 비동기로 처리
	    sendEmailAsync(email, code);
	}


	@Async
	public void sendEmailAsync(String email, String code) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("[전기충만] 이메일 인증번호 입니다.");
			helper.setText(buildEmailBody(code), true);

			// 이메일 발송
			mailSender.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException("인증 메일 발송 중 오류가 발생했습니다.", e);
		}
	}

	@Async
	@Override
	public void sendPassWordVerificationCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO) {
		String email = passWordEmailVerifyDTO.getEmail();

		// 이메일 검증
		if (email == null || email.trim().isEmpty()) {
			throw new MissingEmailException("이메일 주소가 입력되지 않았습니다.");
		}

		// 인증 코드 생성 및 유효기간 설정
		String code = generateRandomCode();
		Timestamp expiresAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)); // 5분 유효

		passWordEmailVerifyDTO.setCode(code);
		passWordEmailVerifyDTO.setExpiresAt(expiresAt);
		passWordEmailVerifyDTO.setVerified('N');

		// 비밀번호 인증 DB 업데이트/삽입 처리
		boolean exists = emailMapper.passwordExistsByEmail(email) > 0;
		if (exists) {
			emailMapper.passwordUpdateCode(passWordEmailVerifyDTO);
		} else {
			emailMapper.passwordSaveCode(passWordEmailVerifyDTO);
		}

		// 이메일 전송을 비동기로 처리
		sendEmailAsync(email, code);
	}

	private String generateRandomCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}

	private String buildEmailBody(String code) {
		return String.format(
				"""
						<html>
						    <head>
						        <meta charset="UTF-8">
						    </head>
						    <body style="font-family: 'Malgun Gothic', 'Apple SD Gothic Neo', sans-serif; background-color: #f5f5f5; padding: 30px;">
						        <table width="100%%" cellpadding="0" cellspacing="0" border="0" style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);">
						            <tr>
						                <td style="padding: 30px; text-align: center;">
						                    <h2 style="color: #2C3E50;">🔋 전기충만 이메일 인증</h2>
						                    <p style="font-size: 16px; color: #444;">안녕하세요, <strong>전기충만</strong>입니다.</p>
						                    <p style="font-size: 16px; color: #444;">아래 <strong style="color: #e74c3c;">인증번호</strong>를 입력하여 이메일 인증을 완료해 주세요.</p>
						                    <div style="margin: 30px auto; width: fit-content; padding: 15px 30px; background-color: #3498db; color: white; font-size: 24px; font-weight: bold; border-radius: 8px;">
						                        인증번호: <span style="letter-spacing: 2px;">%s</span>
						                    </div>
						                    <p style="font-size: 14px; color: #888;">⏰ 해당 인증번호는 발송 시점으로부터 <strong>5분간</strong> 유효합니다.</p>
						                    <br>
						                    <p style="font-size: 14px; color: #999;">감사합니다.<br>전기충만 드림</p>
						                </td>
						            </tr>
						        </table>
						    </body>
						</html>
						""",
				code);
	}
}
