package com.example.evcs.news.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryDTO {
    private String newsCategoryNo;
    private String newsCategory;
    private int newsKeyNum;
}
