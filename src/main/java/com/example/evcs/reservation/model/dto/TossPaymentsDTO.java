package com.example.evcs.reservation.model.dto;

import java.time.LocalDateTime;

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
public class TossPaymentsDTO {
    private Long paymentId;        // PAYMENT_ID (PK)
    private Long memberNo;         // EV_MEMBER의 MEMBER_NO
    private String orderId;        // ORDER_ID (고유 주문번호)
    private String paymentKey;     // Toss 결제 키
    private int amount;            // 결제 금액
    private String method;         // 결제 수단 (카드, 계좌이체 등)
    private LocalDateTime requestedAt;   // Toss 결제 요청 시각
    private LocalDateTime approvedAt;    // Toss 결제 승인 시각
    private LocalDateTime createdAt;     // 등록일
    private String returnAmount;
}
