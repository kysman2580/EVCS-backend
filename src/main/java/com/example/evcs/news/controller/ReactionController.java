package com.example.evcs.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.evcs.news.model.service.ReactionService;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:5173")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/like")
    public String like(@RequestBody Map<String, Long> body) {
        reactionService.likeNews(body.get("newsNo"), body.get("memberNo"));
        return "liked or unliked";
    }

    @PostMapping("/hate")
    public String hate(@RequestBody Map<String, Long> body) {
        reactionService.hateNews(body.get("newsNo"), body.get("memberNo"));
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
    public boolean hasLiked(@RequestParam("newsNo") Long newsNo, @RequestParam("memberNo") Long memberNo) {
        return reactionService.hasLiked(newsNo, memberNo);
    }

    @GetMapping("/hate/status")
    public boolean hasHated(@RequestParam("newsNo") Long newsNo, @RequestParam("memberNo") Long memberNo) {
        return reactionService.hasHated(newsNo, memberNo);
    }

    @PostMapping("/bookmark")
    public String bookmark(@RequestBody Map<String, Long> body) {
        reactionService.toggleBookmark(body.get("newsNo"), body.get("memberNo"));
        return "bookmarked or unbookmarked";
    }
    
    @GetMapping("/bookmark/status")
    public boolean hasBookmarked(@RequestParam("newsNo") Long newsNo, @RequestParam("memberNo") Long memberNo) {
    	System.out.println("회원 번호가 왜 문자열이야" + memberNo.TYPE);
        return reactionService.hasBookmarked(newsNo, memberNo);
    }

}