package com.example.evcs.driveRoute.model.service;

import java.util.Map;

import com.example.evcs.driveRoute.model.dto.DRCommentDTO;

public interface DRCommentService {

	void insertComment(DRCommentDTO drComment);

	public Map<String, Object> selectComment(Long boardNo,int currentCommentPage);

	void deleteComment(Long commentNo);

	void updateComment(DRCommentDTO drComment);
}
