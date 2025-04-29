package com.example.evcs.news.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsMainImageDto {
    private List<NewsItem> items;
}

@Getter
@Setter
class ImageItem {
    private String img_title;
    private String img_link;
    private String img_thumbnail;
    private String img_sizeheight;    
    private String img_sizewidth;
    }


    

