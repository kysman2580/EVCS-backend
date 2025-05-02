package com.example.evcs.common.board;

public class Pagination {

	public static PageInfo getPageInfo(int count,
			   int currentPage,
			   int boardLimit,
			   int pageLimit) {

		int maxPage = (int)Math.ceil((double)count / boardLimit) -1;
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit -1;
		
		if(endPage > maxPage) { endPage = maxPage; };
		
		return PageInfo.builder()
		.boardLimit(boardLimit)
		.count(count)
		.currentPage(currentPage + 1)
		.startPage(startPage)
		.endPage(endPage)
		.maxPage(maxPage)
		.pageLimit(pageLimit).build();
	}
}
