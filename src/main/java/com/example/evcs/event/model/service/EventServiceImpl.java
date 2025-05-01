//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.evcs.auth.model.vo.CustomUserDetails;
//import com.example.evcs.auth.service.AuthService;
//import com.example.evcs.common.file.FileUtil;
//import com.example.evcs.event.model.dao.EventMapper;
//import com.example.evcs.event.model.dto.EventDTO;
//import com.example.evcs.event.model.vo.Event;
//import com.example.evcs.exception.NoFileException;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class EventServiceImpl implements EventService {
//
//	private final EventMapper eventDAO;
//	private FileUtil fileUtil = new FileUtil("uploads/event");
//	private final AuthService authService;
//	
//	@Override
//	public void insertEvent(EventDTO event, MultipartFile file) {
//		
//		CustomUserDetails user = authService.getUserDetails();
//		Long memberNo = user.getMemberNo();
//		
//		Event requestData = null;
//		
//		if(file != null && !file.isEmpty()) {
//			String filePath = fileUtil.saveFile(file);
//			
//			requestData = Event.builder()
//					.memberNo(memberNo)
//					.eventName(event.getEventName())
//					.startDate(event.getStartDate())
//					.endDate(event.getEndDate())
//					.eventContent(event.getEventContent())
//					.build();
//			
//			eventDAO.insertFile(filePath);
//			
//		} else {
//			throw new NoFileException("EventService : 이벤트 사진이 없습니다."); 
//		}
//		
//		eventDAO.insertEvent(requestData);
//	}
//	
//	@Override
//	public List<EventDTO> selctEventAll() {
//		return null;
//	}
//
//	@Override
//	public EventDTO selectByEventNo(Long eventNo) {
//		return null;
//	}
//
//	@Override
//	public void updateEvent(Event event) {
//		
//	}
//
//	@Override
//	public void deleteByEventNo(Long eventNo) {
//		
//	}
//	
//	// 제목이랑 내용에 XSS (Cross-Site Scripting) 막는 메서드
//	private Map<String, String> validateContentAndTitle(String title, String content){
//		
//		Map<String, String> map = new HashMap<>();
//		
//		String changeTitle = title.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
//				"<br>");
//
//		String changeContent = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
//				"<br>");
//		
//		map.put("title", changeTitle);
//		map.put("content", changeContent);
//		
//		return map;
//	}
//	
//
//
//
//}
package com.example.evcs.event.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.auth.service.AuthService;
import com.example.evcs.common.file.FileUtil;
import com.example.evcs.event.model.dao.EventMapper;
import com.example.evcs.event.model.dto.EventDTO;
import com.example.evcs.event.model.vo.Event;
import com.example.evcs.exception.NoFileException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventMapper eventMapper;
	private FileUtil fileUtil = new FileUtil("uploads/event");
	private final AuthService authService;
	
	@Override
	public void insertEvent(EventDTO event, MultipartFile file) {
		
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();
		String memeberName = user.getMemberName();
		
		Event requestData = null;
		
		if(file != null && !file.isEmpty()) {
			String filePath = fileUtil.saveFile(file);
			
			requestData = Event.builder()
					.memberNo(memberNo)
					.eventName(event.getEventName())
					.startDate(event.getStartDate())
					.endDate(event.getEndDate())
					.eventContent(event.getEventContent())
					.build();

			eventMapper.insertFile(filePath);
			
		} else {
			throw new NoFileException("EventService : 이벤트 사진이 없습니다."); 
		}
		eventMapper.insertEvent(requestData);
	}
	
	@Override
	public List<EventDTO> selctEventAll() {
		return null;
	}

	@Override
	public EventDTO selectByEventNo(Long eventNo) {
		return null;
	}

	@Override
	public void updateEvent(Event event) {
		
	}

	@Override
	public void deleteByEventNo(Long eventNo) {
		
	}
	
	// 제목이랑 내용에 XSS (Cross-Site Scripting) 막는 메서드
	private Map<String, String> validateContentAndTitle(String title, String content){
		
		Map<String, String> map = new HashMap<>();
		
		String changeTitle = title.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
				"<br>");

		String changeContent = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
				"<br>");
		
		map.put("title", changeTitle);
		map.put("content", changeContent);
		
		return map;
	}
	



}

