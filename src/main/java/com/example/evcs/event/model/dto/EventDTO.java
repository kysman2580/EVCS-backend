package com.example.evcs.event.model.dto;

import java.sql.Date;

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
	private Long meberNo;
	private String eventName;
	private Date startDate;
	private Date endDate;
	private String eventContent;
	private Date enrollDate;
	private String status;
	
}
