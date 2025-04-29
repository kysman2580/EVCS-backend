/*
package com.example.evcs.common.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.exception.InsertFileException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {
	
	private final Path fileLocation;
	
	public FileUtil (String fileLoad) {
		//                  파일의 경로를 가져 fileLoad 를 가리키는 경로를 만들고 , 절대경로로 바꿔준 후, 경로 안에 "..", "." 같은 걸 깔끔하게 정리해준다.
		this.fileLocation = Paths.get(fileLoad).toAbsolutePath().normalize();
	}
	
	public String saveFile(MultipartFile file) {
		
		String originalFileName = file.getOriginalFilename();
		
		// 파일 이름 변경
		String changeFileName = makeRandomName(originalFileName);
		
		// 파일경로에 이름을 붙여줌
		Path targetLocation = this.fileLocation.resolve(originalFileName);

		//
		try {
			
			// 파일을 저장함 inputStream 만들어주고, 저장할 경로 입력, 혹시 이름이 같으면 덮어씌운다.
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost/uploads/" + originalFileName;
		} catch (IOException e) {
			throw new InsertFileException("파일 에러에유");
		}
	}
	

	// 파일 저장할 때 이름 바꿔주는 메서드
	private String makeRandomName (String originalFileName) {
		
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
*/
