package com.example.evcs.news.model.service;

public interface CommentReactionService {

    void like(Long newsCmtId, Long memberNo);

    void cancelLike(Long newsCmtId, Long memberNo);

    void hate(Long newsCmtId, Long memberNo);

    void cancelHate(Long newsCmtId, Long memberNo);

    int getLikeCount(Long newsCmtId);

    int getHateCount(Long newsCmtId);

    boolean hasLiked(Long newsCmtId, Long memberNo);

    boolean hasHated(Long newsCmtId, Long memberNo);
}
