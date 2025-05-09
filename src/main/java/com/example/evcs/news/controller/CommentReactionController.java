package com.example.evcs.news.controller;

import com.example.evcs.news.model.service.CommentReactionService;
import com.example.evcs.auth.model.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news/comment")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @PostMapping("/like")
    public String like(@RequestParam("newsCmtId") Long newsCmtId,
                       @AuthenticationPrincipal CustomUserDetails user) {
        commentReactionService.like(newsCmtId, user.getMemberNo());
        return "liked";
    }

    @DeleteMapping("/like")
    public String cancelLike(@RequestParam("newsCmtId") Long newsCmtId,
                             @AuthenticationPrincipal CustomUserDetails user) {
        commentReactionService.cancelLike(newsCmtId, user.getMemberNo());
        return "like canceled";
    }

    @PostMapping("/hate")
    public String hate(@RequestParam("newsCmtId") Long newsCmtId,
                       @AuthenticationPrincipal CustomUserDetails user) {
        commentReactionService.hate(newsCmtId, user.getMemberNo());
        return "hated";
    }

    @DeleteMapping("/hate")
    public String cancelHate(@RequestParam("newsCmtId") Long newsCmtId,
                             @AuthenticationPrincipal CustomUserDetails user) {
        commentReactionService.cancelHate(newsCmtId, user.getMemberNo());
        return "hate canceled";
    }

    @GetMapping("/like/count")
    public int likeCount(@RequestParam("newsCmtId") Long newsCmtId) {
        return commentReactionService.getLikeCount(newsCmtId);
    }

    @GetMapping("/hate/count")
    public int hateCount(@RequestParam("newsCmtId") Long newsCmtId) {
        return commentReactionService.getHateCount(newsCmtId);
    }

    @GetMapping("/like/status")
    public boolean hasLiked(@RequestParam("newsCmtId") Long newsCmtId,
                            @AuthenticationPrincipal CustomUserDetails user) {
        return commentReactionService.hasLiked(newsCmtId, user.getMemberNo());
    }

    @GetMapping("/hate/status")
    public boolean hasHated(@RequestParam("newsCmtId") Long newsCmtId,
                            @AuthenticationPrincipal CustomUserDetails user) {
        return commentReactionService.hasHated(newsCmtId, user.getMemberNo());
    }
}
