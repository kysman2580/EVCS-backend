package com.example.evcs.common.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class BoardUtil {

	/**
	 * 검색 기능에서 LIKE 사용시 특수문자 처리 해주는 메서드
	 * 
	 * @param param
	 * @return
	 */
	public static String escapeLikeParam(String param) {
	    if (param == null) return null;
	    return param
	        .replace("\\", "\\\\")  // 역슬래시 먼저 처리
	        .replace("_", "\\_")
	        .replace("%", "\\%");
	}
	
	
	/**
	 *  제목이랑 내용에 XSS (Cross-Site Scripting) 막는 메서드
	 * 
	 * @param title
	 * @param content
	 * 
	 * @return
	 */
	public Map<String, String> validateContentAndTitle(String title, String content){
		
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
