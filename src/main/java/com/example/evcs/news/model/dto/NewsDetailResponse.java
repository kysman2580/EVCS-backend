package com.example.evcs.news.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsDetailResponse {
    private NewsDTO news;
    private List<CommentDTO> comments;
    private int likeCount;
    private int hateCount;
    private boolean isBookmarked;
}