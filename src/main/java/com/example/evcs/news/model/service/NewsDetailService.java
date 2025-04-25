package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.dto.NewsDetailResponse;

public interface NewsDetailService{

	static String findByTitle(String title) {
		return title;
	}
	
    NewsDetailResponse getOrInsertNewsByTitleAndUrl(NewsDTO news);
    
    NewsDetailResponse insertAndReturn(NewsDTO dto);

}
