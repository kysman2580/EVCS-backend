package com.example.evcs.event.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDTO {

	private Long eventNo;
	
	@NotNull(message = "작성자 번호가 없습니다.")
	private Long memberNo;
	
	@NotBlank(message = "이벤트 제목이 없습니다.")
	private String eventName;
	
	@NotNull(message = "이벤트 시작일자가 없습니다.")
	private Date startDate;
	
	@NotNull(message = "이벤트 마감일자가 없습니다.")
	private Date endDate;
	
	@NotBlank(message = "이벤트 내용이 없습니다.")
	private String eventContent;
	
	private Date enrollDate;
	private String status;
	private String memberNickname;
	private String filePath;
	private String statusName;
	
}
