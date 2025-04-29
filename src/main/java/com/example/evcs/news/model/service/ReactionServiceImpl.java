package com.example.evcs.news.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evcs.news.model.dao.NewsReactionMapper;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private NewsReactionMapper newsreactionMapper;

    @Override
    public void likeNews(Long newsNo, Long memberNo) {
        if (newsreactionMapper.hasLiked(newsNo, memberNo) > 0) {
        	newsreactionMapper.deleteLike(newsNo, memberNo);
        } else {
            // 싫어요 눌렀던 경우 취소
            if (newsreactionMapper.hasHated(newsNo, memberNo) > 0) {
            	newsreactionMapper.deleteHate(newsNo, memberNo);
            }
            newsreactionMapper.insertLike(newsNo, memberNo);
        }
    }

    @Override
    public void hateNews(Long newsNo, Long memberNo) {
        if (newsreactionMapper.hasHated(newsNo, memberNo) > 0) {
        	newsreactionMapper.deleteHate(newsNo, memberNo);
        } else {
            // 좋아요 눌렀던 경우 취소
            if (newsreactionMapper.hasLiked(newsNo, memberNo) > 0) {
            	newsreactionMapper.deleteLike(newsNo, memberNo);
            }
            newsreactionMapper.insertHate(newsNo, memberNo);
        }
    }

    @Override
    public boolean hasLiked(Long newsNo, Long memberNo) {
        return newsreactionMapper.hasLiked(newsNo, memberNo) > 0;
    }

    @Override
    public boolean hasHated(Long newsNo, Long memberNo) {
        return newsreactionMapper.hasHated(newsNo, memberNo) > 0;
    }

    @Override
    public void toggleBookmark(Long newsNo, Long memberNo) {
        int count = newsreactionMapper.countBookmarks(newsNo, memberNo);
        if (count > 0) {
        	newsreactionMapper.deleteBookmark(newsNo, memberNo);
        } else {
        	newsreactionMapper.insertBookmark(newsNo, memberNo);
        }
    }
    
    @Override
    public int getLikeCount(Long newsNo) {
        return newsreactionMapper.countLikeByNews(newsNo);
    }

    @Override
    public int getHateCount(Long newsNo) {
        return newsreactionMapper.countHateByNews(newsNo);
    }
    
    @Override
    public boolean hasBookmarked(Long newsNo, Long memberNo) {
        return newsreactionMapper.countBookmarks(newsNo, memberNo) > 0;
    }

}
