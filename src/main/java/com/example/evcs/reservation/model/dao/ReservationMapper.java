package com.example.evcs.reservation.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.admin.hotdeal.model.dto.HotdealDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;
import com.example.evcs.reservation.model.dto.ReservationDTO;
import com.example.evcs.reservation.model.vo.Reservation;

@Mapper
public interface ReservationMapper {

    void createReservation(Reservation reservation);
    
    ReservationDTO selectByOrderId(String orderId);
    
    List<ReservationDTO> selectExpiredReservations();
    
    void expireReservationStatus(Long revNo);
    
    void updateReservationToCompleted(Long reservationNo);
    
    int findRentCarPrice(String rentCarNo);

	HotdealDTO selectHotdealDealPercent(String rentCarNo);

	RentCarInfoDTO selectRentCarInfo(String rentCarNo);
	
	void updateReservationFailed(long reservationNo);
	
	int selectReservationTotalCount(Map<String, String> map);
	
	List<ReservationDTO> selectAllReservation(Map<String, String> map, RowBounds rb);
}
