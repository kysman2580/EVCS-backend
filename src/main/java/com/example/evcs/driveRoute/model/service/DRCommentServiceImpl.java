package com.example.evcs.driveRoute.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.auth.service.AuthServiceImpl;
import com.example.evcs.driveRoute.model.dao.DRCommentMapper;
import com.example.evcs.driveRoute.model.dto.DRCommentDTO;
import com.example.evcs.driveRoute.model.vo.DRCommentVo;
import com.example.evcs.exception.NonExistingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DRCommentServiceImpl implements DRCommentService {
	
	private final DRCommentMapper drCommentMapper;
	private final AuthServiceImpl authServiceImpl;
	
	@Override
	public void insertComment(DRCommentDTO drComment) {
		
		CustomUserDetails user = authServiceImpl.getUserDetails();
		Long memberNo = user.getMemberNo();
		if(memberNo == null) {
			throw new NonExistingException("존재하지 않는 회원입니다.");
		}
		
		DRCommentVo drCommetData = DRCommentVo.builder()
											  .boardNo(drComment.getBoardNo())
											  .commentWriter(memberNo)
											  .commentContent(drComment.getCommentContent())
											  .build();
		
		drCommentMapper.insertComment(drCommetData);
	}
	
	@Override
	public Map<String, Object> selectComment(Long boardNo, int currentCommentPage) {
		
		Map<String, Object> map = new HashMap();
		
		int countCommentPerPage = 10;
		RowBounds rowBounds = new RowBounds(0,currentCommentPage*countCommentPerPage);
		
		List<DRCommentDTO> drComment = drCommentMapper.getAllComment(boardNo,rowBounds);
		log.info("commentttttttttttt:{}",drComment);
		map.put("drComment", drComment);
		
		return map;
		
	}

	@Override
	public void deleteComment(Long commentNo) {
		/*
		 * 존재하는 boardNo인지 확인
		 * => 있으면 삭제
		 * => 없으면 예외처리
		 */
		
		int countCommentResult = drCommentMapper.countCommentByCommentNo(commentNo);
		
		if(countCommentResult==0) {
			throw new NonExistingException("존재하지 않는 댓글입니다.");
		} else {
			drCommentMapper.deleteComment(commentNo);
		}
		
	}

	@Override
	public void updateComment(DRCommentDTO drComment) {
		CustomUserDetails user = authServiceImpl.getUserDetails();
		Long memberNo = user.getMemberNo();
		
		
		DRCommentVo drCommetData = DRCommentVo.builder()
											  .commentNo(drComment.getCommentNo())
											  .commentWriter(memberNo)
											  .commentContent(drComment.getCommentContent())
											  .build();
		int countCommentResult = drCommentMapper.countCommentByCommentNo(drComment.getCommentNo());
		
		if(countCommentResult==0) {
			throw new NonExistingException("존재하지 않는 댓글입니다.");
		} else {
			drCommentMapper.updateComment(drCommetData);
		}
		
	}

}
