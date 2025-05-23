package com.example.evcs.reservation.model.dto;

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
public class ConfirmRequestDTO {
    private String paymentKey;
    private String orderId;
    private String amount;
}
