package com.example.evcs.event.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Event {
	private Long memberNo;
	private String eventName;
	private Date startDate;
	private Date endDate;
	private String eventContent;
}
