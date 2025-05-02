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

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.auth.service.AuthService;
import com.example.evcs.common.board.BoardUtil;
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
	private final AuthService authService;
	private final BoardUtil boardUtil;
	
	// 의존성 주입 문제로 new 로 생성
	private FileUtil fileUtil = new FileUtil("uploads/event");
	
	@Override
	public void insertEvent(EventDTO event, MultipartFile file) {
		
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();

		Event requestData = null;
		
		Map<String, String> utilMap = boardUtil.validateContentAndTitle(event.getEventName(), event.getEventContent());
		
		requestData = Event.builder()
				.memberNo(memberNo)
				.eventName(utilMap.get("title"))
				.startDate(event.getStartDate())
				.endDate(event.getEndDate())
				.eventContent(utilMap.get("content"))
				.build();

		eventMapper.insertEvent(requestData);
		
		if(file != null && !file.isEmpty()) {
			String filePath = fileUtil.saveFile(file);

			eventMapper.insertEventFile(filePath);
			
		} else {
			throw new NoFileException("EventService : 이벤트 사진이 없습니다."); 
		}
	}
	
	@Override
	public List<EventDTO> selctEventAll(Map<String, String> map) {
		int size = 10;
		
		RowBounds rowBounds = new RowBounds(Integer.parseInt(map.get("page")) * size, size);
		
		// 특수문자 이스케이프 처리
		map.put("searchKeyword", boardUtil.escapeLikeParam(map.get("searchKeyword")));
		log.info("map {}" , map);
		
		return eventMapper.selctEventAll(map, rowBounds);
	}

	@Override
	public EventDTO selectByEventNo(Long eventNo) {
		return eventMapper.selectByEventNo(eventNo);
	}

	@Override
	public EventDTO updateEvent(EventDTO event, MultipartFile file) {
		
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();

		Event requestData = null;
		
		Map<String, String> utilMap = boardUtil.validateContentAndTitle(event.getEventName(), event.getEventContent());
		
		requestData = Event.builder()
				.memberNo(memberNo)
				.eventName(utilMap.get("title"))
				.startDate(event.getStartDate())
				.endDate(event.getEndDate())
				.eventContent(utilMap.get("content"))
				.eventNo(event.getEventNo())
				.build();

		eventMapper.updateEvent(requestData);
		
		
		if(file != null && !file.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			String filePath = fileUtil.saveFile(file);
			
			map.put("filePath", filePath);
			map.put("eventNo", String.valueOf(event.getEventNo()));

			eventMapper.updateEventFile(map);
			
		} else {
			throw new NoFileException("EventService : 이벤트 사진이 없습니다."); 
		}
		
		return null;
	}

	@Override
	public void deleteByEventNo(Long eventNo) {
		eventMapper.deleteByEventNo(eventNo);
	}
	
	



}

