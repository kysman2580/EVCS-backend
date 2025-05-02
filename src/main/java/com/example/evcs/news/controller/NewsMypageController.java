package com.example.evcs.news.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.service.NewsMypageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/news/mypage")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class NewsMypageController {

    private final NewsMypageService newsMypageService;

    @GetMapping("/likes")
    public List<NewsDTO> getLikedNews(@RequestParam("memberNo") Long memberNo) {
        return newsMypageService.getLikedNews(memberNo);
    }

    @GetMapping("/bookmarks")
    public List<NewsDTO> getBookmarkedNews(@RequestParam("memberNo") Long memberNo) {
        return newsMypageService.getBookmarkedNews(memberNo);
    }
}