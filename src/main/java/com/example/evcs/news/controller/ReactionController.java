package com.example.evcs.news.controller;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.news.model.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/like")
    public String like(@RequestBody Map<String, Long> body,
                       @AuthenticationPrincipal CustomUserDetails user) {
        reactionService.likeNews(body.get("newsNo"), user.getMemberNo());
        return "liked or unliked";
    }

    @PostMapping("/hate")
    public String hate(@RequestBody Map<String, Long> body,
                       @AuthenticationPrincipal CustomUserDetails user) {
        reactionService.hateNews(body.get("newsNo"), user.getMemberNo());
        return "hated or unhated";
    }

    @GetMapping("/like")
    public int getLikeCount(@RequestParam("newsNo") Long newsNo) {
        return reactionService.getLikeCount(newsNo);
    }

    @GetMapping("/hate")
    public int getHateCount(@RequestParam("newsNo") Long newsNo) {
        return reactionService.getHateCount(newsNo);
    }

    @GetMapping("/like/status")
    public boolean hasLiked(@RequestParam("newsNo") Long newsNo,
                            @AuthenticationPrincipal CustomUserDetails user) {
        return reactionService.hasLiked(newsNo, user.getMemberNo());
    }

    @GetMapping("/hate/status")
    public boolean hasHated(@RequestParam("newsNo") Long newsNo,
                            @AuthenticationPrincipal CustomUserDetails user) {
        return reactionService.hasHated(newsNo, user.getMemberNo());
    }

    @PostMapping("/bookmark")
    public String bookmark(@RequestBody Map<String, Long> body,
                           @AuthenticationPrincipal CustomUserDetails user) {
        reactionService.toggleBookmark(body.get("newsNo"), user.getMemberNo());
        return "bookmarked or unbookmarked";
    }

    @GetMapping("/bookmark/status")
    public boolean hasBookmarked(@RequestParam("newsNo") Long newsNo,
                                 @AuthenticationPrincipal CustomUserDetails user) {
        return reactionService.hasBookmarked(newsNo, user.getMemberNo());
    }
}
