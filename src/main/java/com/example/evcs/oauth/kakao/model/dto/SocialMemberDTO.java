package com.example.evcs.oauth.kakao.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SocialMemberDTO {

    private Long socialMemberNo;  
    private Long memberNo;        
    private String email;
    private String provider;      
    private String providerId;    
    private Timestamp createdAt;

}

