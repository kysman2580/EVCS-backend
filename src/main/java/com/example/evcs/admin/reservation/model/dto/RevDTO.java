package com.example.evcs.admin.reservation.model.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

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
public class RevDTO {

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
