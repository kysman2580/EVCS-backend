//package com.example.evcs.event.controller;
//
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.evcs.event.model.dto.EventDTO;
//import com.example.evcs.event.model.service.EventService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//
//@Slf4j
//@RestController
//@Validated
//@RequiredArgsConstructor
//@RequestMapping("/events")
//public class EventController {
//
//	private final EventService eventService;
//	
//	@PostMapping
//	public void insertEvent(EventDTO event, @RequestParam(name="file", required=false) MultipartFile file) {
//		
//		log.info("EventController : EventDTO 값 확인 {} , file 값 확인 {}" , event, file);
//		
//		eventService.insertEvent(event, file);
//		
//	}
//	
//}
