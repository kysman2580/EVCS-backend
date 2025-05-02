package com.example.evcs.news.model.service;

import java.util.List;

import com.example.evcs.news.model.dto.NewsDTO;

public interface NewsMypageService {
    List<NewsDTO> getLikedNews(Long memberNo);
    List<NewsDTO> getBookmarkedNews(Long memberNo);
}