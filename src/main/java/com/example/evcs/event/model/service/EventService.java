package com.example.evcs.event.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.event.model.dto.EventDTO;
import com.example.evcs.event.model.vo.Event;

public interface EventService {

	void insertEvent(EventDTO event, MultipartFile file);
	
	List<EventDTO> selctEventAll();
	
	EventDTO selectByEventNo(Long eventNo);
	
	void updateEvent(Event event);
	
	void deleteByEventNo(Long eventNo);
}
