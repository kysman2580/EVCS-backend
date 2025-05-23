package com.example.evcs.reporting.model.vo;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReComment {
    private Long reNo;
    private Long memberNo;
    private Long reMemberNo;
    private Date reEnrollDate;
    private String reContent;
    private String reStatus;
    private LocalDateTime reEnd;
    private Long commentGroupNo;
    private Integer commentDepth;
}
