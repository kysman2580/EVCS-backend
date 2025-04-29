package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dto.CommentDTO;
import java.util.List;

public interface NewsCommentService {
    void writeComment(Long newsNo, Long memberNo, String content, Long parentId);
    void updateComment(Long commentId, String content);
    void softDeleteComment(Long commentId);
    List<CommentDTO> findCommentsByNews(Long newsNo);
}
