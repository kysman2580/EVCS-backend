package com.example.evcs.news.controller;

import com.example.evcs.news.model.service.CommentReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @PostMapping("/like")
    public String like(@RequestParam("newsCmtId") Long newsCmtId, @RequestParam("memberNo") Long memberNo) {
        commentReactionService.like(newsCmtId, memberNo);
        return "liked";
    }

    @DeleteMapping("/like")
    public String cancelLike(@RequestParam("newsCmtId") Long newsCmtId, @RequestParam("memberNo") Long memberNo) {
        commentReactionService.cancelLike(newsCmtId, memberNo);
        return "like canceled";
    }

    @PostMapping("/hate")
    public String hate(@RequestParam("newsCmtId") Long newsCmtId, @RequestParam("memberNo") Long memberNo) {
        commentReactionService.hate(newsCmtId, memberNo);
        return "hated";
    }

    @DeleteMapping("/hate")
    public String cancelHate(@RequestParam("newsCmtId") Long newsCmtId, @RequestParam("memberNo") Long memberNo) {
        commentReactionService.cancelHate(newsCmtId, memberNo);
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
    public boolean hasLiked(@RequestParam("newsCmtId") Long newsCmtId, @RequestParam("memberNo") Long memberNo) {
        return commentReactionService.hasLiked(newsCmtId, memberNo);
    }

    @GetMapping("/hate/status")
    public boolean hasHated(@RequestParam("newsCmtId") Long newsCmtId, @RequestParam("memberNo") Long memberNo) {
        return commentReactionService.hasHated(newsCmtId, memberNo);
    }
}
