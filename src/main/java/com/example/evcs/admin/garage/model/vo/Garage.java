package com.example.evcs.admin.garage.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Garage {
	private Long garageNo;
	private String allAddress;
	private String regionSido;       // 시/도
	private String regionSigungu;    // 시/군/구
	private String regionDong;       // 동
	private String address;          // 상세 주소
	private String postAdd;          // 우편번호
	private Date enrollDate;    // 등록일
	private Long writer;             // 등록자 (EV_MEMBER 테이블의 MEMBER_NO 참조)
	private String status;           // 상태 ('Y' 또는 'N')
}
