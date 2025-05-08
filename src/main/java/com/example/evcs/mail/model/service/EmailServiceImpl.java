package com.example.evcs.mail.model.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.example.evcs.exception.EmailNotFoundException;
import com.example.evcs.exception.EmailNotVerifiedException;
import com.example.evcs.exception.InvalidVerificationCodeException;
import com.example.evcs.exception.MissingEmailException;
import com.example.evcs.exception.VerificationCodeMismatchException;
import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.dto.PassWordEmailVerifyDTO;
import com.example.evcs.mail.model.dao.EmailMapper;
import com.example.evcs.member.model.service.MemberService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;
	private final EmailMapper emailMapper;

	@Override
	@Transactional
	public void sendVerificationCode(EmailVerifyDTO emailVerifyDTO) {
		String email = emailVerifyDTO.getEmail();
		boolean exists = emailMapper.existsByEmail(email) > 0;
		
		if(email == null || email.trim().isEmpty()) {
	        throw new EmailNotFoundException("이메일을 입력해주세요.");
		}
		
		
		String code = generateRandomCode();
		Timestamp expiresAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)); // 30분 유효

		emailVerifyDTO.setCode(code);
		emailVerifyDTO.setExpiresAt(expiresAt);
		emailVerifyDTO.setVerified('N');

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("[전기충만] 이메일 인증번호 입니다.");
			helper.setText(buildEmailBody(code), true);

			

			if (exists) {
				emailMapper.updateCode(emailVerifyDTO);
			} else {
				emailMapper.saveCode(emailVerifyDTO);

			}

			mailSender.send(message);

		} catch (MessagingException e) {
	        throw new RuntimeException("인증 메일 발송 중 오류가 발생했습니다.", e);
		}
	}

	@Override
	public void sendPassWordVerificationCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO) {
		String email = passWordEmailVerifyDTO.getEmail();

		if (email == null || email.trim().isEmpty()) {
			throw new MissingEmailException ("이메일 주소가 입력되지 않았습니다.");
		}

		String code = generateRandomCode();
		Timestamp expiresAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)); // 30분 유효

		passWordEmailVerifyDTO.setCode(code);
		passWordEmailVerifyDTO.setExpiresAt(expiresAt);
		passWordEmailVerifyDTO.setVerified('N');

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("[전기충만] 이메일 인증번호 입니다.");
			helper.setText(buildEmailBody(code), true);

			boolean exists = emailMapper.passwordExistsByEmail(email) > 0;

			if (exists) {
				emailMapper.passwordUpdateCode(passWordEmailVerifyDTO);
			} else {
				emailMapper.passwordSaveCode(passWordEmailVerifyDTO);

			}

			mailSender.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException("이메일 전송 실패", e);
		}
	}

	public void verifyCode(EmailVerifyDTO emailVerifyDTO) {
		EmailVerifyDTO verifyCode = emailMapper.getEmailVerification(emailVerifyDTO);

		if (verifyCode == null || !verifyCode.getCode().equals(emailVerifyDTO.getCode())) {
			throw new EmailNotVerifiedException("인증번호가 유효하지 않습니다.");
		}

		emailMapper.updateEmailVerified(emailVerifyDTO);
	}

	@Override
	public void passwordVerifyCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO) {
		EmailVerifyDTO verifyCode = emailMapper.getEmailVerificationByPassword(passWordEmailVerifyDTO);


		if (passWordEmailVerifyDTO.getCode() == null || passWordEmailVerifyDTO.getCode().trim().isEmpty()) {
		    throw new InvalidVerificationCodeException("인증번호를 입력해주세요.");
		}

		if (!verifyCode.getCode().equals(passWordEmailVerifyDTO.getCode())) {
		    throw new VerificationCodeMismatchException("입력한 인증번호가 일치하지 않습니다.");
		}
		


		emailMapper.updateEmailVerifiedByPassword(passWordEmailVerifyDTO);
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
