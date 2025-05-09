package com.example.evcs.oauth.kakao.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

public class KakaoDTO {

	@Getter
	public static class OAuthToken {
		private String access_token;
		private String token_type;
		private String refresh_token;
		private int expires_in;
		private String scope;
		private int refresh_token_expires_in;
	    private String id_token; 
	}

	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)  // 알 수 없는 필드는 무시
	public static class KakaoProfile {
		private Long id;
		private String connected_at;
		private Properties properties;
		private KakaoAccount kakao_account;

		@Getter
		public class Properties {
			private String nickname;
	        private String profile_image;
	        private String thumbnail_image; 
		}

		@Getter
		public class KakaoAccount {
			private String email;
			private Boolean is_email_verified;
			private Boolean has_email;
			private Boolean profile_nickname_needs_agreement;
			private Boolean email_needs_agreement;
			private Boolean is_email_valid;
			private Profile profile;
			private Boolean profile_image_needs_agreement;

			@Getter
			public class Profile {
				private String nickname;
				private Boolean is_default_nickname;
				private String profile_image_url;
				private String thumbnail_image_url;  // 추가된 필드
				private Boolean is_default_image;
			}
		}
	}

}
