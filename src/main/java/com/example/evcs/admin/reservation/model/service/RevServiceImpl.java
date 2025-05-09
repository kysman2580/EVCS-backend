package com.example.evcs.admin.reservation.model.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.reservation.model.dao.RevMapper;
import com.example.evcs.admin.reservation.model.dto.RevDTO;
import com.example.evcs.admin.reservation.model.vo.RevVo;
import com.example.evcs.exception.NonExistingException;
import com.example.evcs.exception.WrongTimeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RevServiceImpl implements RevService {
	
	private final RevMapper revMapper;
	
	
	@Override
	public void insertRev(RevDTO reservation) {

		/*
		 * 렌터카 예약하기 ~~
		 * 
		 * 0. 대여시각이 반납시각보다 작은지 확인
		 * 
		 * 1. 존재하는 렌터카인지 확인하기
		 * 
		 * 2. 존재한다면 내가 에약한 시간에 존재하는지 확인
		 * 
		 * 3. 없다면 insert 하기 있다면 예외처리
		 * 
		 * 4. 존재하지 않는다면 예외처리
		 */
		
		LocalDateTime rentalTime = reservation.getRentalTime();
		LocalDateTime returnTime = reservation.getReturnTime();
		
		log.info("rentalTime : {}, returnTime : {}",rentalTime,returnTime);
		
		Duration duration = Duration.between(rentalTime, returnTime);
		log.info("duration.toHours() : {}",duration.toHours());
		if(duration.toHours() < 1) {
			throw new WrongTimeException("반납시각은 대여시각보다 최소 1시간 이상이어야합니다.");
		} 
		
		String rentCarNo = reservation.getRentCarNo();
		
		int result = revMapper.findByCarNo(rentCarNo);
		log.info("{}",result);
		if(result == 1) {
			
			RevVo RevData = RevVo.builder()
								 .rentCarNo(reservation.getRentCarNo())
								 .memberNo(reservation.getMemberNo())
								 .rentalTime(reservation.getRentalTime())
								 .returnTime(reservation.getReturnTime())
								 .build();
			
			// 존재하는 예약인지 확인
			int countRev = revMapper.checkRev(RevData);
			log.info("countRev : {}",countRev);
			if(countRev == 0) {
				
				revMapper.insertRev(RevData);
			}
			
		} else {
			throw new NonExistingException("존재하지 않는 렌트카입니다");
		}

	
		
		
	}

}
