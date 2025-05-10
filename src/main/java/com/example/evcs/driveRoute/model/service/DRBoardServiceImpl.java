package com.example.evcs.driveRoute.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.auth.service.AuthServiceImpl;
import com.example.evcs.common.file.FileUtil;
import com.example.evcs.driveRoute.model.dao.DRBoardMapper;
import com.example.evcs.driveRoute.model.dto.DRBoardDTO;
import com.example.evcs.driveRoute.model.vo.DRBoardVo;
import com.example.evcs.exception.NoFileException;
import com.example.evcs.exception.NonExistingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DRBoardServiceImpl implements DRBoardService {
	
	private final AuthServiceImpl authServiceImpl;
	private final DRBoardMapper drBoardMapper;
	FileUtil boardFile = new FileUtil("uploads/board");
	FileUtil driveRouteFile = new FileUtil("uploads/driveRoute");
	
	@Override
	public void insertBoard(DRBoardDTO drBoard, MultipartFile[] boardFiles, MultipartFile drFile) {
		
		/*
		 * memberNo 존재하는지, 게시글 내용이 존재하는지 , 
		 * 게시판 이미지들, 드라이브루트 이미지 존재하는지 확인
		 */
		CustomUserDetails user = authServiceImpl.getUserDetails();
		Long memberNo = user.getMemberNo();
		//Long memberNo = drBoard.getBoardWriter();
		if(memberNo == null) {
			throw new NonExistingException("존재하지 않는 회원입니다.");
		}
		
		for(MultipartFile file: boardFiles) {
			if(file == null || file.isEmpty()) {
				throw new NoFileException("이미지가 존재하지 않습니다.");
			}
		}
		
		if(drFile == null || drFile.isEmpty()) {
			throw new NoFileException("드라이브 경로를 선택해주세요.");
		}
		
		
		DRBoardVo drBoardData = DRBoardVo.builder()
				 						 .boardWriter(memberNo)
				 						 .boardContent(drBoard.getBoardContent())
				 						 .build();
		
		
		int result = drBoardMapper.insertBoard(drBoardData);
		
		if(result == 1) {
			
			Long boardNo = drBoardMapper.getBoardNo();
			
			for(MultipartFile file: boardFiles) {
				String boardFilePath = boardFile.saveFile(file);
				DRBoardVo boardFileData = DRBoardVo.builder()
												   .boardNo(boardNo)
												   .boardImage(boardFilePath)
												   .build();
				drBoardMapper.insertBoardFile(boardFileData);
			}
			
			String driveRouteFilePath = driveRouteFile.saveFile(drFile);
			
			DRBoardVo driveRouteFileData = DRBoardVo.builder()
											   .boardNo(boardNo)
											   .driveRouteImage(driveRouteFilePath)
											   .build();
			drBoardMapper.insertDriveRouteFile(driveRouteFileData);
			
		}
		
		
		
	}

	@Override
	public Map<String, Object> selectBoard(int currentPage) {
		
		Map<String,Object> map = new HashMap();
		
		int boardPerPage = 10;
		RowBounds rowBounds = new RowBounds(0,boardPerPage*currentPage);
		List<DRBoardDTO> drBoard = drBoardMapper.getAllBoard(rowBounds);
		
		// BOARD_NO 최소/최대 추출
		Long min = drBoard.stream().mapToLong(DRBoardDTO::getBoardNo).min().orElse(0);
		Long max = drBoard.stream().mapToLong(DRBoardDTO::getBoardNo).max().orElse(0);

		List<DRBoardDTO> drBoardImages = drBoardMapper.getAllBoardImages(min, max);
		
		map.put("drBoard", drBoard);
		map.put("drBoardImages", drBoardImages);
		
		return map;
	}
	

	@Override
	public void updateBoard(DRBoardDTO drBoard, MultipartFile[] boardFiles, MultipartFile drFile) {
	    CustomUserDetails user = authServiceImpl.getUserDetails();
	    Long memberNo = user.getMemberNo();

	    if (memberNo == null) {
	        throw new NonExistingException("존재하지 않는 회원입니다.");
	    }
	    if (drFile == null || drFile.isEmpty()) {
	        throw new NoFileException("드라이브 경로를 선택해주세요.");
	    }

	    DRBoardVo drBoardData = DRBoardVo.builder()
	            .boardNo(drBoard.getBoardNo())
	            .boardWriter(memberNo)
	            .boardContent(drBoard.getBoardContent())
	            .build();

	    int result = drBoardMapper.updateBoard(drBoardData);

	    if (result == 1) {
	        // boardFiles가 null이 아니고 비어있지 않은 경우에만 실행
	        if (boardFiles != null && boardFiles.length > 0) {
	            for (MultipartFile file : boardFiles) {
	                if (file != null && !file.isEmpty()) {
	                    String boardFilePath = boardFile.saveFile(file);
	                    DRBoardVo boardFileData = DRBoardVo.builder()
	                            .boardNo(drBoard.getBoardNo())
	                            .boardImage(boardFilePath)
	                            .build();
	                    drBoardMapper.updateBoardFile(boardFileData);
	                }
	            }
	        }

	        // 드라이브 경로 파일 저장 (항상 실행)
	        String driveRouteFilePath = driveRouteFile.saveFile(drFile);
	        DRBoardVo driveRouteFileData = DRBoardVo.builder()
	                .boardNo(drBoard.getBoardNo())
	                .driveRouteImage(driveRouteFilePath)
	                .build();
	        drBoardMapper.updateDriveRouteFile(driveRouteFileData);
	    }
	}
	

	@Override
	public void deleteBoard(Long boardNo) {
		
		/*
		 * 존재하는 boardNo인지 확인
		 * => 있으면 삭제
		 * => 없으면 예외처리
		 */
		
		int countBoardResult = drBoardMapper.countBoardByBoardNo(boardNo);
		
		if(countBoardResult==0) {
			throw new NonExistingException("존재하지 않는 게시글입니다.");
		} else {
			drBoardMapper.deleteBoard(boardNo);
		}
	}

	@Override
	public void boardLikes(Long boardNo) {
		
		CustomUserDetails user = authServiceImpl.getUserDetails();
		Long memberNo = user.getMemberNo();
		
		DRBoardVo boardLikesData = DRBoardVo.builder()
											 .boardWriter(memberNo)
											 .boardNo(boardNo)
											 .build();
		drBoardMapper.boardLikes(boardLikesData);
		
		
	}

	@Override
	public void boardLikesCancel(Long boardNo) {
		CustomUserDetails user = authServiceImpl.getUserDetails();
		Long memberNo = user.getMemberNo();
		
		DRBoardVo boardLikesCancelData = DRBoardVo.builder()
											 .boardWriter(memberNo)
											 .boardNo(boardNo)
											 .build();
		drBoardMapper.boardLikesCancel(boardLikesCancelData);
		
	}

	@Override
	public List<DRBoardDTO> selectBoardLikes() {
		CustomUserDetails user = authServiceImpl.getUserDetails();
		Long boardWriter = user.getMemberNo();
		
		List<DRBoardDTO> boardLikesInfo = drBoardMapper.selectBoardLikes(boardWriter);
		log.info("boardLikesInfo:{}",boardLikesInfo);
		return boardLikesInfo;
	}

	

	
}



































