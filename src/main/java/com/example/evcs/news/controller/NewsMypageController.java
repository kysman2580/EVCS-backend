package com.example.evcs.news.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.service.NewsMypageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/news/mypage")
@RequiredArgsConstructor
public class NewsMypageController {

    private final NewsMypageService newsMypageService;

    @GetMapping("/likes")
    public List<NewsDTO> getLikedNews(@AuthenticationPrincipal CustomUserDetails user) {
        return newsMypageService.getLikedNews(user.getMemberNo());
    }

    @GetMapping("/bookmarks")
    public List<NewsDTO> getBookmarkedNews(@AuthenticationPrincipal CustomUserDetails user) {
        return newsMypageService.getBookmarkedNews(user.getMemberNo());
    }
}