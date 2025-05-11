package com.example.evcs.reservation.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDTO {

    private Long reservationNo;
    private String rentCarNo;
    private Long memberNo;
    private String returnStatus; // Y: 예약중 I: 사용중 N : 반납완료
    private LocalDateTime  expireAt;
    private String orderId;
    private int amount;
    private String paymentsStatus;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime rentalTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime returnTime;
    private String selectedPeriod;
    private String paymentId;
    private String returnAmount; 
}
