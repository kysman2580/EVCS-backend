package com.example.evcs.reservation.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.evcs.reservation.model.dao.TossPaymentMapper;
import com.example.evcs.reservation.model.dto.ConfirmRequestDTO;
import com.example.evcs.reservation.model.dto.ReservationDTO;
import com.example.evcs.reservation.model.dto.TossPaymentsDTO;
import com.example.evcs.reservation.model.service.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class TossPaymentController {

    private final ReservationService reservationService;
    private final TossPaymentMapper tossPaymentMapper;

    @Value("${toss.secretKey}")
    private String tossSecretKey;

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody ConfirmRequestDTO req) {
    	
    	log.info("req : {}", req);
    	
        ReservationDTO res = reservationService.selectByOrderId(req.getOrderId());
        log.info("res :  {}",  res);
        if (res == null) throw new RuntimeException("예약 없음");
        if (res.getAmount() != Integer.parseInt(req.getAmount())) throw new RuntimeException("금액 불일치");
        

        // Toss confirm
        RestTemplate rest = new RestTemplate();
        String auth = Base64.getEncoder().encodeToString((tossSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + auth);

        Map<String, Object> body = new HashMap<>();	
        body.put("paymentKey", req.getPaymentKey());
        body.put("orderId", req.getOrderId());
        body.put("amount", Integer.parseInt(req.getAmount()));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = rest.exchange(
                "https://api.tosspayments.com/v1/payments/confirm",
                HttpMethod.POST, entity, String.class
        );

        // TossPayment insert
        TossPaymentsDTO payment = new TossPaymentsDTO();
        payment.setMemberNo(res.getMemberNo());
        payment.setOrderId(req.getOrderId());
        payment.setPaymentKey(req.getPaymentKey());
        payment.setAmount(res.getAmount());
        payment.setMethod("CARD");
        payment.setRequestedAt(LocalDateTime.now());
        payment.setApprovedAt(LocalDateTime.now());
        
        log.info("payment : {}" , payment);
        tossPaymentMapper.insertTossPayment(payment);

        reservationService.setCompleted(res.getReservationNo());

        return ResponseEntity.ok(response.getBody());
    }
    
    @PostMapping("/fail")
    public ResponseEntity<?> fail(@RequestBody ConfirmRequestDTO req) {
        log.info("fail req : {}", req);

        // 1) 예약 조회
        ReservationDTO res = reservationService.selectByOrderId(req.getOrderId());
        if (res == null) {
          throw new RuntimeException("예약 없음");
        }

        // 2) 상태 FAILED 로 업데이트
        reservationService.updateReservationFailed(res.getReservationNo());

        return ResponseEntity.ok().build();
      }
    
    @GetMapping("/{paymentId}")
    public ResponseEntity<TossPaymentsDTO> getPayment(@PathVariable("paymentId") String paymentId) {
        
    	log.info("paymentKey : {}", paymentId);
    	
    	TossPaymentsDTO payment = tossPaymentMapper.selectByPaymentKey(paymentId);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }
    
    
}
