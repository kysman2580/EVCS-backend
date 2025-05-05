package com.example.evcs.news.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evcs.news.model.dao.NewsCommentMapper;
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
    private final NewsCommentMapper newscommentMapper;
    private final NewsReactionMapper newsreactionMapper;
    
    @Override
    @Transactional
    public NewsDetailResponse getOrInsertNewsByTitleAndUrl(NewsDTO news, Long memberNo) {
        NewsDTO dto = newsMapper.findByTitleAndUrl(news.getTitle(), news.getOriginUrl());
        if (dto == null) {
            return insertAndReturn(news, memberNo);
        }
        
        System.out.println("NEWS_NO 전달 값: " + dto.getNewsNo());

        newsMapper.incrementWatchCount(dto.getNewsNo());
        
        return buildNewsDetailResponse(dto, memberNo);
    }
    
    @Override
    @Transactional
    public NewsDetailResponse insertAndReturn(NewsDTO dto, Long memberNo) {
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
        return buildNewsDetailResponse(inserted, memberNo);
    }
    
    private NewsDetailResponse buildNewsDetailResponse(NewsDTO news, Long memberNo) {
        Long newsNo = news.getNewsNo();
        List<CommentDTO> comments = newscommentMapper.findCommentsByNews(newsNo, memberNo); 
        int likeCount = newsreactionMapper.countLikeByNews(newsNo);
        int hateCount = newsreactionMapper.countHateByNews(newsNo);
        boolean isBookmarked = newsreactionMapper.countBookmarks(newsNo, memberNo) > 0;
        return new NewsDetailResponse(news, comments, likeCount, hateCount, isBookmarked);
    }
    
    public NewsDetailResponse getByNewsNo(Long newsNo, Long memberNo) {
        NewsDTO news = newsMapper.findByNewsNo(newsNo);
        List<CommentDTO> comments = newscommentMapper.findCommentsByNews(newsNo, memberNo);
        int likeCount = newsreactionMapper.countLikeByNews(newsNo);
        int hateCount = newsreactionMapper.countHateByNews(newsNo);
        boolean isBookmarked = newsreactionMapper.countBookmarks(newsNo, memberNo) > 0;

        return new NewsDetailResponse(news, comments, likeCount, hateCount, isBookmarked);
    }

}