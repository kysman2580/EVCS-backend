package com.example.evcs.admin.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

	String findAdmin(String role);


}
