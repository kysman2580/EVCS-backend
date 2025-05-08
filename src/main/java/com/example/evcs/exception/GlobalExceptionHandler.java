package com.example.evcs.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.evcs.exception.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MemberEmailDuplicationException.class)
	public ResponseEntity<?> handlerMemberEmailDuplicationError(MemberEmailDuplicationException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(EmailNotVerifiedException.class)
	public ResponseEntity<?> handlerEmailNotVerificationError(EmailNotVerifiedException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<?> handlerCustomAuthenticationError(CustomAuthenticationException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(NotAdminRoleException.class)
	public ResponseEntity<?> handlerNotAdminRoleError(NotAdminRoleException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<?> EmailNotFoundExceptionError(EmailNotFoundException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(MissingEmailException.class)
	public ResponseEntity<?> MissingEmailExceptionError(MissingEmailException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<?> PasswordMismatchExceptionError(PasswordMismatchException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(InvalidVerificationCodeException.class)
	public ResponseEntity<?> InvalidVerificationCodeExceptionError(InvalidVerificationCodeException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(VerificationCodeMismatchException.class)
	public ResponseEntity<?> VerificationCodeMismatchExceptionError(VerificationCodeMismatchException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(MissingNewPasswordException.class)
	public ResponseEntity<?> MissingNewPasswordExceptionError(MissingNewPasswordException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(MissingConfirmPasswordException.class)
	public ResponseEntity<?> MissingConfirmPasswordExceptionError(MissingConfirmPasswordException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(DuplicatedCarInfoException.class)
	public ResponseEntity<?> DuplicatedCarInfoError(DuplicatedCarInfoException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(NonExistingException.class)
	public ResponseEntity<?> NonExistingError(NonExistingException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	// 게시글 등록 시 파일 없으면 예외처리
	@ExceptionHandler(NoFileException.class)
	public ResponseEntity<?> NoFileException(NoFileException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	// 파일 등록시 예외
	@ExceptionHandler(InsertFileException.class)
	public ResponseEntity<?> InsertFileException(InsertFileException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(NoticeNotFoundException.class)
	public ResponseEntity<?> NonExistingError(NoticeNotFoundException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	// 핫딜 중복차량 있을때 발생하는 예외
    @ExceptionHandler(DuplicateHotdealException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateHotdeal(DuplicateHotdealException ex) {
        List<String> carNos = ex.getCarNos();
        
        // join 쓰면 사이에 ", " 넣어서 합쳐줌 
        String joined = String.join(", ", carNos);
        String message = String.format("%s: %s", ex.getMessage(), joined);

        ErrorResponse body = new ErrorResponse("HOTDEAL_OVERLAP", message);
        
        return ResponseEntity
                .status(HttpStatus.CONFLICT)    // 409
                .body(body);
    }

}
