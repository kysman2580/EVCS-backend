package com.example.evcs.news.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evcs.exception.NewsDisabledException;
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
        // 1) 활성(Y) 상태인 뉴스 조회
        NewsDTO dto = newsMapper.findByTitleAndUrl(news.getTitle(), news.getOriginUrl());
        if (dto == null) {
            // 2) Y가 없으면 상태 무관 조회
            NewsDTO all = newsMapper.findByTitleAndUrlAll(news.getTitle(), news.getOriginUrl());
            if (all != null && "N".equals(all.getNewsStatus())) {
                // 이미 비활성화된 뉴스면 예외 던져서 컨트롤러로 복제 방지
                throw new NewsDisabledException("이 뉴스는 더 이상 활성화되어 있지 않습니다.");
            }
            // 3) 신규 뉴스면 삽입
            return insertAndReturn(news, memberNo);
        }

        // 4) 기존 활성 뉴스면 조회수 증가 후 상세 반환
        newsMapper.incrementWatchCount(dto.getNewsNo());
        return buildNewsDetailResponse(dto, memberNo);
    }
    
    @Override
    @Transactional
    public NewsDetailResponse insertAndReturn(NewsDTO dto, Long memberNo) {
        // (insert 로직은 기존과 동일)
        newsMapper.insert(dto);
        Long newsNo = newsMapper.getLastInsertId();
        newsMapper.incrementWatchCount(newsNo);
        if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            newsMapper.imageInsert(newsNo, dto.getImageUrl());
        }
        NewsDTO inserted = newsMapper.findByTitleAndUrl(dto.getTitle(), dto.getOriginUrl());
        return buildNewsDetailResponse(inserted, memberNo);
    }
    
    private NewsDetailResponse buildNewsDetailResponse(NewsDTO news, Long memberNo) {
        Long newsNo = news.getNewsNo();
        List<CommentDTO> comments = newscommentMapper.findCommentsByNews(newsNo, memberNo);
        int likeCount = newsreactionMapper.countLikeByNews(newsNo);
        int hateCount = newsreactionMapper.countHateByNews(newsNo);
        boolean isBookmarked = (memberNo != null) && newsreactionMapper.countBookmarks(newsNo, memberNo) > 0;
        return new NewsDetailResponse(news, comments, likeCount, hateCount, isBookmarked, news.getNewsStatus());
    }

    
    @Override
    @Transactional
    public NewsDetailResponse getByNewsNo(Long newsNo, Long memberNo) {
        NewsDTO news = newsMapper.findByNewsNo(newsNo);
        List<CommentDTO> comments = newscommentMapper.findCommentsByNews(newsNo, memberNo);
        int likeCount = newsreactionMapper.countLikeByNews(newsNo);
        int hateCount = newsreactionMapper.countHateByNews(newsNo);
        boolean isBookmarked = newsreactionMapper.countBookmarks(newsNo, memberNo) > 0;

        return new NewsDetailResponse(news, comments, likeCount, hateCount, isBookmarked, news.getNewsStatus());
    }

    @Override
    @Transactional
    public void deleteNews(Long newsNo) {
        newsMapper.softDeleteByNewsNo(newsNo);
    }

}