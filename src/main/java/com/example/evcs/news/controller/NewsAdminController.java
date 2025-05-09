package com.example.evcs.news.controller;

import com.example.evcs.news.model.dto.NewsCategoryDTO;
import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.service.NewsAdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class NewsAdminController {

    private final NewsAdminService newsAdminService;

    // 전체 뉴스 목록 (관리자용)
    @GetMapping("/list")
    public List<NewsDTO> findAllNews() {
        return newsAdminService.findAllNews();
    }

    // 전체 카테고리 목록
    @GetMapping("/category/all")
    public List<NewsCategoryDTO> findAllCategories() {
        return newsAdminService.findAll();
    }

    // 카테고리 추가
    @PostMapping("/category")
    public void insertCategory(@RequestBody NewsCategoryDTO dto) {
    	newsAdminService.insert(dto.getNewsCategory());
    }

    // 카테고리 수정
    @PutMapping("/category/{newsCategoryNo}")
    public void updateCategory(
            @PathVariable("newsCategoryNo") String newsCategoryNo,
            @RequestBody NewsCategoryDTO dto
    ) {
    	newsAdminService.update(newsCategoryNo, dto.getNewsCategory());
    }

    // 카테고리 삭제
    @DeleteMapping("/category/{newsCategoryNo}")
    public void deleteCategory(@PathVariable("newsCategoryNo") String newsCategoryNo) {
    	newsAdminService.delete(newsCategoryNo);
    }
    
    /** 뉴스 활성화/비활성화 토글 */
    @PutMapping("/status")
    public ResponseEntity<Void> updateStatus(
            @RequestParam("newsNo") Long newsNo,
            @RequestParam("status") String status) {
        newsAdminService.updateStatus(newsNo, status);
        return ResponseEntity.ok().build();
    }
}
