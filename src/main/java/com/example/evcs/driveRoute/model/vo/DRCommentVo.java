package com.example.evcs.driveRoute.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DRCommentVo {

	private Long commentNo;
	private Long boardNo;
	private Long commentWriter;
	private String memberNickName;
	private String commentContent;
	private Date createDate;
	private String status;
}
