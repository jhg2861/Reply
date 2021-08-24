package scit.master.good.util;

import lombok.Getter;

@Getter
public class PageNavigator {
	private final int countPerPage = 10;	// 한 페이지 당 글개수
	private final int pagePerGroup = 5;     // 페이지 그룹
	private int currentPage;				// 현재 페이지
	private int totalRecordCount;			// 전체 글 개수
	private int totalPageCount;				// 총 페이지수
	private int currentGroup;				// 현재 그룹
	private int startPageGroup;				// 현재 그룹의 첫 페이지
	private int endPageGroup;				// 현재 그룹의 마지막 페이지
	private int startRecord;				// 전체 레코드 중 현재 페이지의 첫 글의 위치
	private int srow;
	private int erow;
	
	public PageNavigator(int currentPage, int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
		
		totalPageCount = (totalRecordCount % countPerPage > 0) 
                ? totalRecordCount / countPerPage + 1 : totalRecordCount / countPerPage;
		
		System.out.println("전체 페이지수 : " + totalPageCount);
		// 현재 페이지 요청 시 
		this.currentPage = currentPage;
		if(this.currentPage < 1) this.currentPage = 1;
		if(this.currentPage >=  totalPageCount) this.currentPage = totalPageCount;
		
		
		// 그룹 계산 (내가 4페이지를 보고있다면.. 4페이지는 몇번째 그룹인가? 0그룹 (4-1)/5 => 0그룹
		currentGroup = (this.currentPage - 1) / pagePerGroup; // 왜 -1을 하는가? 현재 페이지는 5페이지라면 1그룹이 되지 않도록 하기 위해서

		// 총페이지수(totalPageCount = 16)
		// 총그룹 (3그룹)
		// 0그룹 : 1 ~ 5
		// 1그룹 : 6 ~ 10 
		// 2그룹 : 11 ~ 15
		// 3그룹 : 16
		
		startPageGroup = currentGroup * pagePerGroup + 1;
		startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		
		endPageGroup = startPageGroup + pagePerGroup - 1;
		endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
		
		// 전체 레코드 중에서 현재 페이지의 첫글 ?
		startRecord = 1 + (this.currentPage - 1) * countPerPage;
		srow =        1 + (this.currentPage - 1) * countPerPage;
		erow =        countPerPage + (this.currentPage-1) * countPerPage;
	}
}
