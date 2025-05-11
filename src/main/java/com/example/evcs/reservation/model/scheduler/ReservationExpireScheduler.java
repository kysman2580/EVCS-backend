package com.example.evcs.reservation.model.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.evcs.reservation.model.dao.ReservationMapper;
import com.example.evcs.reservation.model.dto.ReservationDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationExpireScheduler {
    
	private final ReservationMapper reservationMapper;
	
	@Scheduled(fixedRate = 60000)
    public void expireUnpaidReservations() {
        List<ReservationDTO> expiredList = reservationMapper.selectExpiredReservations();
        for (ReservationDTO res : expiredList) {
            reservationMapper.expireReservationStatus(res.getReservationNo());
        }
    }
}
