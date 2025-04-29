package com.example.evcs.admin.carInfo.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private final Path fileLocation;
	
	public FileService() {
		this.fileLocation = Paths.get("uploads/car").toAbsolutePath().normalize();
	}
	
	public String save(MultipartFile file) {
		
		String originalFileName = file.getOriginalFilename();
		
		Path targetLocation = this.fileLocation.resolve(fileLocation);
		
		try {
			Files.copy(file.getInputStream(),targetLocation,StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost/uploads/car/" + originalFileName; 
		} catch (IOException e) {
			throw new RuntimeException("잘못된 파일입니다~");
		}
		
		
	}
	
}
