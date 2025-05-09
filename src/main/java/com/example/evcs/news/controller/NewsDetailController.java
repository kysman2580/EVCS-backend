package com.example.evcs.news.controller;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.exception.NewsDisabledException;
import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.dto.NewsDetailResponse;
import com.example.evcs.news.model.service.NewsDetailService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsDetailController {

    private final NewsDetailService newsDetailService;

    @PostMapping("/detail")
    public ResponseEntity<?> getNewsDetail(
            @RequestBody NewsDTO news,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long memberNo = (user != null) ? user.getMemberNo() : null;

        try {
            // 서비스 호출은 한 번만!
            NewsDetailResponse detail =
                newsDetailService.getOrInsertNewsByTitleAndUrl(news, memberNo);

            Map<String, Object> resp = new HashMap<>();
            resp.put("exists", true);
            resp.put("news", detail.getNews());
            resp.put("comments", detail.getComments());
            resp.put("likeCount", detail.getLikeCount());
            resp.put("hateCount", detail.getHateCount());
            resp.put("bookmarked", detail.isBookmarked());

            return ResponseEntity.ok(resp);

        } catch (NewsDisabledException ex) {
            // 이미 비활성화된 게시판은 복제 없이 410으로 응답
            return ResponseEntity
                    .status(HttpStatus.GONE)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> postNewsDetail(@RequestBody NewsDTO newsDto,
                                            @AuthenticationPrincipal CustomUserDetails user) {
        Long memberNo = (user != null) ? user.getMemberNo() : null;
        NewsDetailResponse newsDetail = newsDetailService.insertAndReturn(newsDto, memberNo);

        Map<String, Object> response = new HashMap<>();
        response.put("news", newsDetail.getNews());
        response.put("comments", newsDetail.getComments());
        response.put("likeCount", newsDetail.getLikeCount());
        response.put("hateCount", newsDetail.getHateCount());
        response.put("bookmarked", newsDetail.isBookmarked());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/detail/by-id")
    public ResponseEntity<?> getNewsDetailById(@RequestBody Map<String, Object> body,
                                               @AuthenticationPrincipal CustomUserDetails user) {
        Long memberNo = (user != null) ? user.getMemberNo() : null;
        Long newsNo = Long.valueOf(body.get("newsNo").toString());
        NewsDetailResponse detail = newsDetailService.getByNewsNo(newsNo, memberNo);
        return ResponseEntity.ok(detail);
    }

    @DeleteMapping("/detail/delete")
    public ResponseEntity<String> deleteNews(@RequestParam("newsNo") Long newsNo) {
        newsDetailService.deleteNews(newsNo);
        return ResponseEntity.ok("deleted");
    }
}
