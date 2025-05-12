package com.example.evcs.auth.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@ToString
public class CustomUserDetails implements UserDetails {

	private final Long memberNo;
	private final String username;
	private final String password;
	private final String memberName;
	private final String memberStatus;
	private final Collection<? extends GrantedAuthority> authorities;

	@Builder
	public CustomUserDetails(Long memberNo, String username, String password, String memberName, String memberStatus,
			Collection<? extends GrantedAuthority> authorities) {
		this.memberNo = memberNo;
		this.username = username;
		this.password = password;
		this.memberName = memberName;
		this.memberStatus = memberStatus;
		this.authorities = authorities;
	}

	public boolean isAdmin() {
		return this.getAuthorities().stream().anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));
	}
}
