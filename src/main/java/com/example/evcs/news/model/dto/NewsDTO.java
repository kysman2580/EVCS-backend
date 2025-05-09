package com.example.evcs.news.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsDTO {
    private Long newsNo;
    private String title;
    private String originUrl;
    private String description;
    private String imageUrl;
    private String pubDate;
    private String query;
    private String count;
}