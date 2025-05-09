package com.example.evcs.admin.hotdeal.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class IngHotdeal {
    private Long hotDealNo;
    private String rentCarNo;
}
