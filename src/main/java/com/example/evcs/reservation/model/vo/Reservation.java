package com.example.evcs.reservation.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Reservation {

    private Long reservationNo;
    private String rentCarNo;
    private Long memberNo;
    private String returnStatus; // PENDING / COMPLETED / EXPIRED
    private LocalDateTime  expireAt;
    private String orderId;
    private int amount;
    private Long paymentId; 
    private String paymentsStatus;
    private LocalDateTime rentalTime;
    private LocalDateTime returnTime;
}
