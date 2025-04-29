package com.example.evcs.news.controller;

import com.example.evcs.news.model.dto.CommentDTO;
import com.example.evcs.news.model.service.NewsCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news/comment")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class NewsCommentController {

    private final NewsCommentService newsCommentService;
    
    // 댓글 목록 조회 추가 (GET 요청 처리)
    @GetMapping("/list")
    public List<CommentDTO> getCommentList(@RequestParam("newsNo") Long newsNo, @RequestParam("memberNo") Long memberNo) {
        return newsCommentService.findCommentsByNews(newsNo, memberNo);
    }


    // 댓글 작성 (단일 저장 후 전체 리스트 반환)
    @PostMapping
    public List<CommentDTO> comment(@RequestBody Map<String, Object> body) {
        System.out.println("요청 데이터: " + body);

        if (body.get("newsNo") == null || body.get("memberNo") == null || body.get("content") == null) {
            throw new IllegalArgumentException("newsNo, memberNo, content는 필수입니다.");
        }

        Long newsNo = Long.valueOf(body.get("newsNo").toString());
        Long memberNo = Long.valueOf(body.get("memberNo").toString());
        String content = body.get("content").toString();
        Long parentId = (body.get("parentId") != null) ? Long.valueOf(body.get("parentId").toString()) : null;

        newsCommentService.writeComment(newsNo, memberNo, content, parentId);

        return newsCommentService.findCommentsByNews(newsNo, memberNo);
    }

    // 댓글 수정
    @PutMapping
    public String updateComment(@RequestBody Map<String, Object> body) {
        if (body.get("commentId") == null || body.get("content") == null) {
            throw new IllegalArgumentException("commentId, content는 필수입니다.");
        }

        newsCommentService.updateComment(
            Long.valueOf(body.get("commentId").toString()),
            body.get("content").toString()
        );
        return "updated";
    }

    // 댓글 소프트 삭제
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
    	System.out.println("commentID 옴? : " + commentId);
        newsCommentService.softDeleteComment(commentId);
        return "deleted";
    }
}
