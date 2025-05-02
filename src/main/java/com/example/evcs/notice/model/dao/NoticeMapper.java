package com.example.evcs.notice.model.dao;

import com.example.evcs.notice.model.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDto> getAllNotices();

    NoticeDto getNoticeById(Long id);

    int createNotice(NoticeDto dto);

    int deleteNotice(Long id);

    int updateNotice(NoticeDto dto);
}