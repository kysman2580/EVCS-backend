package com.example.evcs.driveRoute.model.dto;

import java.sql.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Valid
public class DRCommentDTO {

	private Long commentNo;
	private Long boardNo;
	private Long commentWriter;
	private String memberNickname;
	@NotBlank(message = "댓글을 작성해주세요.")
    @Size(max = 200, min = 5, message = "댓글은 5자 이상, 150자 이하로 작성해주세요.")
	private String commentContent;
	private Date createDate;
	private String status;
	

}
