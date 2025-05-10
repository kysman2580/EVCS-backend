package com.example.evcs.driveRoute.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.driveRoute.model.dto.DRBoardDTO;

public interface DRBoardService {

	void insertBoard(DRBoardDTO drBoard, MultipartFile[] boardFiles, MultipartFile drFile);

	Map<String, Object> selectBoard(int currentPage);

	void updateBoard(DRBoardDTO drBoard, MultipartFile[] boardFiles, MultipartFile drFile);
	
	void deleteBoard(Long boardNo);

	void boardLikes(Long boardNo);
	void boardLikesCancel(Long boardNo);
	List<DRBoardDTO> selectBoardLikes();

	

}
