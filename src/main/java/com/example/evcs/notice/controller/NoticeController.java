package com.example.evcs.notice.controller;

import com.example.evcs.notice.exception.NoticeNotFoundException;
import com.example.evcs.notice.model.dto.NoticeDto;
import com.example.evcs.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public List<NoticeDto> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @GetMapping("/{id}")
    public NoticeDto getNoticeById(@PathVariable Long id) {
        return noticeService.getNoticeById(id)
                .orElseThrow(() -> new NoticeNotFoundException("Notice not found with ID: " + id));
    }

    @PostMapping
    public NoticeDto createNotice(@RequestBody NoticeDto dto) {
    	System.out.println("dto 오냐? " + dto.toString());
        return noticeService.createNotice(dto);
    }

    @PutMapping("/{id}")
    public NoticeDto updateNotice(@PathVariable Long id, @RequestBody NoticeDto dto) {
        return noticeService.updateNotice(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }
}

