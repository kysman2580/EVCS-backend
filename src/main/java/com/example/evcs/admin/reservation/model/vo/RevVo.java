package com.example.evcs.admin.reservation.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RevVo {
	
	private int carNo;
	private Long reservationNo;
	private String rentCarNo;
	private Long memberNo;
	private String categoryName;
	private int categoryNo;
	private LocalDateTime rentalTime;
	private LocalDateTime returnTime;
	private LocalDateTime realReturnTime;
	private char returnStatus;
}
