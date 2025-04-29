package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dao.NewsCommentMapper;
import com.example.evcs.news.model.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCommentServiceImpl implements NewsCommentService {

    private final NewsCommentMapper newsCommentMapper;

    @Override
    public void writeComment(Long newsNo, Long memberNo, String content, Long parentId) {
        newsCommentMapper.insertComment(newsNo, memberNo, content, parentId);
    }

    @Override
    public void updateComment(Long commentId, String content) {
        newsCommentMapper.updateComment(commentId, content);
    }

    @Override
    public void softDeleteComment(Long commentId) {
        newsCommentMapper.softDeleteComment(commentId);
    }

    @Override
    public List<CommentDTO> findCommentsByNews(Long newsNo) {
        return newsCommentMapper.findCommentsByNews(newsNo);
    }
}
