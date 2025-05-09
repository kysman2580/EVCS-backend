package com.example.evcs.notice.model.service;

import com.example.evcs.notice.model.dto.NoticeDto;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    List<NoticeDto> getAllNotices();

    Optional<NoticeDto> getNoticeById(Long id);

    NoticeDto createNotice(NoticeDto dto);

    void deleteNotice(Long id);

    NoticeDto updateNotice(Long id, NoticeDto dto);
}
