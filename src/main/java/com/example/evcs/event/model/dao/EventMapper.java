package com.example.evcs.event.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.event.model.dto.EventDTO;
import com.example.evcs.event.model.vo.Event;

@Mapper
public interface EventMapper {

	void insertEvent(Event event);
	
	void insertFile(String filePath);
	
	List<EventDTO> selctEventAll(RowBounds rb);
	
	EventDTO selectByEventNo(Long eventNo);
	
	void updateEvent(Event event);
	
	void deleteByEventNo(Long eventNo);
	
}
