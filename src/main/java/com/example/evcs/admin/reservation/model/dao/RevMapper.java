package com.example.evcs.admin.reservation.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.admin.reservation.model.vo.RevVo;

@Mapper
public interface RevMapper {

	// 1. insert
	void insertRev(RevVo rentCarNo);
	
	int checkRev(RevVo revData);
	int findByCarNo(String rentCarNo);



}
