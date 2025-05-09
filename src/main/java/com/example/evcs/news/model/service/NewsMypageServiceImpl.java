package com.example.evcs.news.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.evcs.news.model.dao.NewsMypageMapper;
import com.example.evcs.news.model.dto.NewsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsMypageServiceImpl implements NewsMypageService {

    private final NewsMypageMapper newsMypageMapper;

    @Override
    public List<NewsDTO> getLikedNews(Long memberNo) {
        return newsMypageMapper.selectLikedNews(memberNo);
    }

    @Override
    public List<NewsDTO> getBookmarkedNews(Long memberNo) {
        return newsMypageMapper.selectBookmarkedNews(memberNo);
    }
}
