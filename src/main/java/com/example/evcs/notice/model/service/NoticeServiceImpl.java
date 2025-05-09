package com.example.evcs.notice.model.service;

import com.example.evcs.notice.model.dto.NoticeDto;
import com.example.evcs.notice.model.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<NoticeDto> getAllNotices() {
        return noticeMapper.getAllNotices();
    }

    @Override
    public Optional<NoticeDto> getNoticeById(Long id) {
        return Optional.ofNullable(noticeMapper.getNoticeById(id));
    }

    @Override
    public NoticeDto createNotice(NoticeDto dto) {
        noticeMapper.createNotice(dto);
        return dto;
    }

    @Override
    public void deleteNotice(Long id) {
        noticeMapper.deleteNotice(id);
    }

    @Override
    public NoticeDto updateNotice(Long id, NoticeDto dto) {
        dto.setId(id); // id 설정
        noticeMapper.updateNotice(dto);
        return dto;
    }
}

