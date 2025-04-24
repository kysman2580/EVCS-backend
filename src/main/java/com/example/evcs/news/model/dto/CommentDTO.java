package com.example.evcs.news.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String user;
    private String content;
    private String date;
    private int likes;
    private int dislikes;
}
