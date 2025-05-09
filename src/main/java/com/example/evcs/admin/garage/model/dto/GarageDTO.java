package com.example.evcs.admin.garage.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GarageDTO {
	
	private Long garageNo;
	
	@NotNull(message = "전체 주소가 없습니다.")
	private String allAddress;
	
	@NotNull(message = "대분류가 없습니다.")
	private String regionSido;       // 시/도
	
	@NotNull(message = "중분류가 없습니다.")
	private String regionSigungu;    // 시/군/구
	
	@NotNull(message = "소분류가 없습니다.")
	private String regionDong;       // 동
	
	@NotNull(message = "세부주소가 없습니다.")
	private String address;          // 상세 주소
	
	@NotNull(message = "우편번호가 없습니다.")
	private String postAdd;          // 우편번호
	
	private Date enrollDate;    // 등록일
	private Long writer;             // 등록자 (EV_MEMBER 테이블의 MEMBER_NO 참조)
	private String status;           // 상태 ('Y' 또는 'N')
	private String memberNickname;
	private String statusName;
	
}
