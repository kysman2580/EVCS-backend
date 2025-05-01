package com.example.evcs.util.template;

import com.example.evcs.util.model.dto.PageInfo;

public class Pagination {
	
	public static PageInfo getPageInfo(int currentPage,int pageSize,int carNoPerPage ,int totalCarNo) {
		// 현재 페이지, 하단에 보여질 페이지 개수, 한 페이지에 보여질 게시글 개수, 전체 게시글 개수
		
		int totalPages = (totalCarNo+carNoPerPage-1)/carNoPerPage; // 전체 페이지 수
		int startPage = (currentPage/pageSize) *pageSize +1;	   // 하단 시작 페이지
		int endPage = startPage+pageSize-1;						   // 하단 마지막 페이지
		
		
		
		return PageInfo.builder()
						.currentPage(currentPage)
						.pageSize(pageSize)
						.carNoPerPage(carNoPerPage)
						.totalCarNo(totalCarNo)
						.totalPages(totalPages)
						.startPage(startPage)
						.endPage(endPage)
						.build();
	}
}
