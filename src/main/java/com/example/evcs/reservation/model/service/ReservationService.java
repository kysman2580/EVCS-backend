package com.example.evcs.reservation.model.service;

import java.util.Map;

import com.example.evcs.reservation.model.dto.ReservationDTO;
import com.example.evcs.reservation.model.dto.ResponseDTO;
import com.example.evcs.reservation.model.vo.TossPayments;

public interface ReservationService {

    public ResponseDTO createReservation(ReservationDTO dto);

    public ReservationDTO selectByOrderId(String orderId) ;

    public void setCompleted(Long revNo);
    
    public int findRentCarPrice(String rentCarNo);
    
    public void updateReservationFailed(Long reservationNo);
    
    public Map<String, Object> selectAllReservation(Map<String, String> map);
    
    
}
