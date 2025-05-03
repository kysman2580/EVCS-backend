package com.example.evcs.event.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.event.model.dto.EventDTO;
import com.example.evcs.event.model.vo.Event;

public interface EventService {

	void insertEvent(EventDTO event, MultipartFile file);
	
	Map<String , Object> selctEventAll(Map<String, String> map);
	
	EventDTO selectByEventNo(Long eventNo);
	
	EventDTO updateEvent(EventDTO event, MultipartFile file);
	
	void deleteByEventNo(Long eventNo);
	
	Map<String , Object> selctEventAllUser(int page);
	
}
