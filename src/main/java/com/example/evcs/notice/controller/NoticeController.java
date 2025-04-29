package com.example.evcs.notice.controller;

import com.example.evcs.notice.dto.NoticeDto;
import com.example.evcs.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public List<NoticeDto> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @GetMapping("/{id}")
    public NoticeDto getNoticeById(@PathVariable Long id) {
        return noticeService.getNoticeById(id);
    }
}
