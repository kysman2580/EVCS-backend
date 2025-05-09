package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.dto.NewsDetailResponse;

public interface NewsDetailService{

	static String findByTitle(String title) {
		return title;
	}
	
    NewsDetailResponse getOrInsertNewsByTitleAndUrl(NewsDTO news, Long memberNo);
    
    NewsDetailResponse insertAndReturn(NewsDTO dto, Long memberNo);

	NewsDetailResponse getByNewsNo(Long newsNo, Long memberNo);

	void deleteNews(Long newsNo);

}
