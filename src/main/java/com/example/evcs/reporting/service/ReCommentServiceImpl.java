package com.example.evcs.reporting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.evcs.reporting.mapper.ReCommentMapper;
import com.example.evcs.reporting.model.vo.ReComment;

@Service
public class ReCommentServiceImpl implements ReCommentService {
    private final ReCommentMapper mapper;

    public ReCommentServiceImpl(ReCommentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ReComment> getReCommentsForUser(Long memberNo, String startDate,
                                                String endDate, String title,
                                                int offset, int size) {
        return mapper.selectReCommentsForUser(memberNo, startDate, endDate, title, offset, size);
    }

    @Override
    public int getTotalReCommentCountForUser(Long memberNo, String startDate,
                                             String endDate, String title) {
        return mapper.countReCommentsForUser(memberNo, startDate, endDate, title);
    }
    
    @Override
    public List<ReComment> getReCommentsForAdim(String startDate,
                                                String endDate, String title,
                                                int offset, int size) {
        return mapper.selectReCommentsForAdim(startDate, endDate, title, offset, size);
    }

    @Override
    public int getTotalReCommentCountForAdim(String startDate,
                                             String endDate, String title) {
        return mapper.countReCommentsForAdim(startDate, endDate, title);
    }

    @Override
    public ReComment getReCommentById(Long reNo) {
        return mapper.selectReCommentById(reNo);
    }

    @Override
    public void updateReportComStatusForUser(Long reNo, Long memberNo, String status) {
        Map<String, Object> map = new HashMap<>();
        map.put("reNo", reNo);
        map.put("memberNo", memberNo);
        map.put("status", status);
        mapper.updateReportComStatusForUser(map);
    }
}