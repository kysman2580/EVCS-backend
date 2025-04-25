package com.example.evcs.news.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evcs.news.model.dao.CommentMapper;
import com.example.evcs.news.model.dao.NewsMapper;
import com.example.evcs.news.model.dao.NewsReactionMapper;
import com.example.evcs.news.model.dto.CommentDTO;
import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.dto.NewsDetailResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsDetailServiceImpl implements NewsDetailService {
    
    private NewsMapper newsMapper;
    private final CommentMapper commentMapper;
    private final NewsReactionMapper newsreactionMapper;
    
    @Override
    @Transactional
    public NewsDetailResponse getOrInsertNewsByTitleAndUrl(NewsDTO news) {
        NewsDTO dto = newsMapper.findByTitleAndUrl(news.getTitle(), news.getOriginUrl());
        if (dto == null) {
            return insertAndReturn(news);
        }
        
        System.out.println("NEWS_NO 전달 값: " + dto.getNewsNo());

        newsMapper.incrementWatchCount(dto.getNewsNo());
        
        return buildNewsDetailResponse(dto);
    }
    
    @Override
    @Transactional
    public NewsDetailResponse insertAndReturn(NewsDTO dto) {
        System.out.println("---이미지 링크 오긴 함? : " + dto.getImageUrl() + "dto야 넌 뭔 값을 가지고 있니 : " + dto.getQuery());
    	// 뉴스 정보 저장
        newsMapper.insert(dto);
        
        // 생성된 뉴스 번호 가져오기
        Long newsNo = newsMapper.getLastInsertId();
        
        
        newsMapper.incrementWatchCount(newsNo);
        
        
        // 이미지 URL이 있으면 이미지 정보 저장
        if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            newsMapper.imageInsert(newsNo, dto.getImageUrl());
        }
        
        
        // 저장된 뉴스 정보 조회 및 반환
        NewsDTO inserted = newsMapper.findByTitleAndUrl(dto.getTitle(), dto.getOriginUrl());
        return buildNewsDetailResponse(inserted);
    }
    
    private NewsDetailResponse buildNewsDetailResponse(NewsDTO news) {
        Long newsNo = news.getNewsNo();
        List<CommentDTO> comments = commentMapper.findByNews(news.getTitle(), news.getOriginUrl());
        int likeCount = newsreactionMapper.countLikeByNews(newsNo);
        int hateCount = newsreactionMapper.countHateByNews(newsNo);
        boolean isBookmarked = newsreactionMapper.countBookmarks(newsNo, 1L) > 0;
        return new NewsDetailResponse(news, comments, likeCount, hateCount, isBookmarked);
    }
}