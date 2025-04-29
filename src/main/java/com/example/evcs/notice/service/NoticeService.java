package com.example.evcs.notice.service;

import com.example.evcs.notice.dto.NoticeDto;
import com.example.evcs.notice.entity.Notice;
import com.example.evcs.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeDto> getAllNotices() {
        return noticeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public NoticeDto getNoticeById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
        return convertToDto(notice);
    }

    public NoticeDto createNotice(NoticeDto noticeDto) {
        Notice notice = convertToEntity(noticeDto);
        Notice saved = noticeRepository.save(notice);
        return convertToDto(saved);
    }

    public NoticeDto updateNotice(Long id, NoticeDto noticeDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setAuthor(noticeDto.getAuthor());
        notice.setDate(noticeDto.getDate());

        Notice updated = noticeRepository.save(notice);
        return convertToDto(updated);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    private NoticeDto convertToDto(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .author(notice.getAuthor())
                .date(notice.getDate())
                .build();
    }

    private Notice convertToEntity(NoticeDto noticeDto) {
        return Notice.builder()
                .id(noticeDto.getId())
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .author(noticeDto.getAuthor())
                .date(noticeDto.getDate())
                .build();
    }
}
