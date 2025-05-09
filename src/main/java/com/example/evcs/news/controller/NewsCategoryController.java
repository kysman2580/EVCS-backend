package com.example.evcs.news.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.news.model.dto.NewsCategoryDTO;
import com.example.evcs.news.model.service.NewsAdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final NewsAdminService newsAdminService;

    @GetMapping("/categories")
    public List<NewsCategoryDTO> findAllCategories() {
        return newsAdminService.findAll();
    }
}
