package com.example.evcs.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MemberEmailDuplicationException.class)
	public ResponseEntity<?> handlerMemberEmailDuplicationError(MemberEmailDuplicationException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(EmailNotVerifiedException.class)
	public ResponseEntity<?> handlerEmailNotVerificationError(EmailNotVerifiedException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<?> handlerCustomAuthenticationError(CustomAuthenticationException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(NotAdminRoleException.class)
	public ResponseEntity<?> handlerNotAdminRoleError(NotAdminRoleException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	

	@ExceptionHandler(DuplicatedCarInfoException.class)
	public ResponseEntity<?> DuplicatedCarInfoError(DuplicatedCarInfoException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
  }
  
	@ExceptionHandler(NonExistingException.class)
	public ResponseEntity<?> NonExistingError(NonExistingException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	// 게시글 등록 시 파일 없으면 예외처리
	@ExceptionHandler(NoFileException.class)
	public ResponseEntity<?> handlerNotAdminRoleError(NoFileException e){
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	// 파일 등록시 에러
	@ExceptionHandler(InsertFileException.class)
	public ResponseEntity<?> handlerNotAdminRoleError(InsertFileException e){
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
	
}
