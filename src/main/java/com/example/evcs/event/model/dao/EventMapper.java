package com.example.evcs.event.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.event.model.dto.EventDTO;
import com.example.evcs.event.model.vo.Event;

@Mapper
public interface EventMapper {

	void insertEvent(Event event);
	
	void insertEventFile(String filePath);
	
	List<EventDTO> selctEventAll(Map<String, String> map,RowBounds rb);
	
	EventDTO selectByEventNo(Long eventNo);
	
	void updateEvent(Event event);
	
	void updateEventFile(Map event);

	void deleteByEventNo(Long eventNo);
	
}
