package com.example.evcs.news.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsMainResponseDto {
    private int total;
    private int start;
    private int display;
    private List<NewsItem> items;
}

@Getter
@Setter
class NewsItem {
    private String title;
    private String originallink;
    private String link; // 네이버 뉴스 링크
    private String description;
    private String pubDate;  // 🆕 뉴스 기사 발행 날짜 추가 
}
