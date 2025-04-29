package com.example.evcs.news.model.service;

public interface ReactionService {
    void likeNews(Long newsNo, Long memberNo);
    void hateNews(Long newsNo, Long memberNo);
    boolean hasLiked(Long newsNo, Long memberNo);
    boolean hasHated(Long newsNo, Long memberNo);
    int getLikeCount(Long newsNo);
    int getHateCount(Long newsNo);
    
    void toggleBookmark(Long newsNo, Long memberNo);
    boolean hasBookmarked(Long newsNo, Long memberNo);

}