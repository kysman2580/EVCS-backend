package com.example.evcs.news.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {
    private Long id;
    private Long newsNo;
    private Long memberNo;
    private String memberNick;
    private String content;
    private String commentDate;
    private Long parentId;
    private int likes;
    private int dislikes;
    private String status; // "Y" or "N"
}