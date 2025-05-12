package com.example.evcs.reporting.service;

import java.util.List;

import com.example.evcs.reporting.model.vo.ReComment;

public interface ReCommentService {
    List<ReComment> getReCommentsForUser(Long memberNo,
                                         String startDate,
                                         String endDate,
                                         String title,
                                         int offset,
                                         int size);
    List<ReComment> getReCommentsForAdim(
            String startDate,
            String endDate,
            String title,
            int offset,
            int size);

    int getTotalReCommentCountForUser(Long memberNo,
                                      String startDate,
                                      String endDate,
                                      String title);
    
    int getTotalReCommentCountForAdim(
            String startDate,
            String endDate,
            String title);

    ReComment getReCommentById(Long reNo);
    
    void updateReportComStatusForUser(Long rpNo, Long memberNo, String status);
}
