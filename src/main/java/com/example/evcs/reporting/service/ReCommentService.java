package com.example.evcs.reporting.service;

import java.util.List;

import com.example.evcs.reporting.model.vo.ReComment;

public interface ReCommentService {
    List<ReComment> getReCommentsForUser(Long memberNo,
                                         String startDate,
                                         String endDate,
                                         String keyword,
                                         int offset,
                                         int size);

    int getTotalReCommentCountForUser(Long memberNo,
                                      String startDate,
                                      String endDate,
                                      String keyword);

    ReComment getReCommentById(Long reNo);
}
