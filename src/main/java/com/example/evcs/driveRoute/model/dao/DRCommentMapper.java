package com.example.evcs.driveRoute.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.driveRoute.model.dto.DRCommentDTO;
import com.example.evcs.driveRoute.model.vo.DRCommentVo;

@Mapper
public interface DRCommentMapper {

	void insertComment(DRCommentVo drCommetData);

	List<DRCommentDTO> getAllComment(@Param("boardNo") Long boardNo, RowBounds rowBounds);

	int countCommentByCommentNo(Long commentNo);

	void deleteComment(Long commentNo);

}
