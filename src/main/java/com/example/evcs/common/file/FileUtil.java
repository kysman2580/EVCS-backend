package com.example.evcs.common.file;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.evcs.exception.InvalidContentsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {
	
	// 파일 저장할 때 제목, 내용, 작성자 비어있는지 확인하는 메서드
	public void validateContents(String content , String title, String writer) {
		
		if(title == null || title.trim().isEmpty() ||
		   content == null || content.trim().isEmpty() ||
		   writer == null || writer.trim().isEmpty()) {
			throw new InvalidContentsException("FileUtil : 유효하지 않은 contents 입니다.");
		}
	}
	
	// 제목이랑 내용에 XSS (Cross-Site Scripting) 막는 메서드
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
	
	// 파일 저장할 때 이름 바꿔주는 메서드
	public String makeRandomName (String originalFileName) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("evcs_");
		
		String crrentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		sb.append(crrentTime);
		
		sb.append("_");
		
		int rNum = (int)(Math.random()*900) + 100;
		
		sb.append(rNum);
		
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		sb.append(ext);
		
		log.info("바뀐이름 나오는지 test : FileUtile class 바뀐 파일 이름 : {}", sb.toString());
		
		String changeFileName = sb.toString();
		
		return changeFileName;
	}

}
