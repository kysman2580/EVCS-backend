package com.example.evcs.notice.service;

import com.example.evcs.notice.dto.NoticeDto;
import java.util.List;

public interface NoticeService {
    List<NoticeDto> getAllNotices();
    NoticeDto getNoticeById(Long id);
}
