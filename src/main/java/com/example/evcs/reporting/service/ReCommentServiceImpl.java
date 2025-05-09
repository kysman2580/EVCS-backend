package com.example.evcs.reporting.service;

import java.util.List;

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
                                                String endDate, String keyword,
                                                int offset, int size) {
        return mapper.selectReCommentsForUser(memberNo, startDate, endDate, keyword, offset, size);
    }

    @Override
    public int getTotalReCommentCountForUser(Long memberNo, String startDate,
                                             String endDate, String keyword) {
        return mapper.countReCommentsForUser(memberNo, startDate, endDate, keyword);
    }

    @Override
    public ReComment getReCommentById(Long reNo) {
        return mapper.selectReCommentById(reNo);
    }
}