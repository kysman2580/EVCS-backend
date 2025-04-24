package com.example.evcs.news.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.news.model.dto.CommentDTO;

@Mapper
public interface CommentMapper {

	List<CommentDTO> findByNews(String title, String originUrl);

}
