package com.example.evcs.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.reservation.model.dto.ReservationDTO;
import com.example.evcs.reservation.model.dto.ResponseDTO;
import com.example.evcs.reservation.model.service.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	
    @PostMapping
    public ResponseEntity<ResponseDTO> createReservation(@RequestBody ReservationDTO req) {
    	log.info("reservation : {}" , req.toString());

    	ResponseDTO res = reservationService.createReservation(req);
        
        return ResponseEntity.ok(res);
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> selectAllReservation(
    		@RequestParam (name="page", defaultValue="0") int page
    		,@RequestParam (name="memberNo",required = false) Long memberNo
    		){
    
		log.info("ReservationController selectAllReservation : page : {} "
				, page);
		
		Map <String, String> map = new HashMap<String, String>();
		map.put("page", String.valueOf(page-1));
		map.put("memberNo", String.valueOf(memberNo));
		
		Map<String, Object> returnMap = reservationService.selectAllReservation(map);
    	
    	return ResponseEntity.ok(returnMap);
    }
}
