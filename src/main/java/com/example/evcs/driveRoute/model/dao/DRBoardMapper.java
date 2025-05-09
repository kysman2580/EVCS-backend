package com.example.evcs.driveRoute.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.driveRoute.model.dto.DRBoardDTO;
import com.example.evcs.driveRoute.model.vo.DRBoardVo;

@Mapper
public interface DRBoardMapper {

	// 1. 게시글 추가하기
	int insertBoard(DRBoardVo drBoardData);
	
	Long getBoardNo();
	void insertBoardFile(DRBoardVo boardFileData);
	void insertDriveRouteFile(DRBoardVo driveRouteFileData);

	List<DRBoardDTO> getAllBoard(RowBounds rowBounds);
	List<DRBoardDTO> getAllBoardImages( @Param("min") Long minBoardNo,
										@Param("max") Long maxBoardNo);

	int countBoardByBoardNo(Long boardNo);

	
	// 게시글 삭제 상태바꾸
	void deleteBoard(Long boardNo);
	
}
