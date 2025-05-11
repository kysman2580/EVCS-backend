package com.example.evcs.reservation.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.reservation.model.dto.TossPaymentsDTO;

@Mapper
public interface TossPaymentMapper {

    void insertTossPayment(TossPaymentsDTO payment);
    
    TossPaymentsDTO selectByOrderId(String orderId);
    
    TossPaymentsDTO selectByPaymentKey(String paymentKey);
    
}
