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
    private String status; // "Y" or "N"
    private int likes;      // 좋아요 수
    private int dislikes;   // 싫어요 수
    private boolean hasLiked; // 로그인한 유저가 좋아요 눌렀는지
    private boolean hasHated; // 로그인한 유저가 싫어요 눌렀는지
}