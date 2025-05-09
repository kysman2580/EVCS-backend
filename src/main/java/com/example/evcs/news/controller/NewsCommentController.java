package com.example.evcs.news.controller;

import com.example.evcs.news.model.dto.CommentDTO;
import com.example.evcs.news.model.service.NewsCommentService;
import com.example.evcs.auth.model.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news/comment")
@RequiredArgsConstructor
public class NewsCommentController {

    private final NewsCommentService newsCommentService;

    @GetMapping("/list")
    public List<CommentDTO> getCommentList(@RequestParam("newsNo") Long newsNo,
                                           @AuthenticationPrincipal CustomUserDetails user) {
        Long memberNo = (user != null) ? user.getMemberNo() : -1L;  // 비회원은 -1로 처리
        return newsCommentService.findCommentsByNews(newsNo, memberNo);
    }

    @PostMapping
    public List<CommentDTO> comment(@RequestBody Map<String, Object> body,
                                    @AuthenticationPrincipal CustomUserDetails user) {
        Long newsNo = Long.valueOf(body.get("newsNo").toString());
        String content = body.get("content").toString();
        Long parentId = (body.get("parentId") != null) ? Long.valueOf(body.get("parentId").toString()) : null;

        newsCommentService.writeComment(newsNo, user.getMemberNo(), content, parentId);
        return newsCommentService.findCommentsByNews(newsNo, user.getMemberNo());
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or @newsCommentService.isOwner(#body['commentId'], principal.memberNo)")
    public String updateComment(
            @RequestBody Map<String, Object> body,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long commentId = Long.valueOf(body.get("commentId").toString());
        String content   = body.get("content").toString();
        newsCommentService.updateComment(commentId, content);
        return "updated";
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('ADMIN') or @newsCommentService.isOwner(#commentId, principal.memberNo)")
    public String deleteComment(
    		@PathVariable("commentId")Long commentId,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        newsCommentService.softDeleteComment(commentId);
        return "deleted";
    }
}
