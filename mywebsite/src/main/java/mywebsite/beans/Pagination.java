package mywebsite.beans;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Pagination을 위한 도구 클래스
 * 
 *  - 입력(준비물)
 *  	- 페이지번호(p)
 *  	- 검색어(keyword)
 *  	- 검색분류(column)
 *  	- 데이터 개수(count)
 *  - 처리
 *  - 출력(결과물)
 */
public class Pagination {
	//필수 데이터
	private int p;
	private int count;
	
	//선택 데이터
	private String column;
	private String keyword;
	
	//생성자를 이용하여 필수 데이터를 설정하도록 구현
	public Pagination(HttpServletRequest req) {
		try {
			//그냥 받아올 경우 오류가나면 답이 없으므로 try~catch를 이용해서 오류를 모두 1로 설정한다.
			this.p = Integer.parseInt(req.getParameter("p"));
			if(this.p <= 0) throw new Exception();
		}catch(Exception e) {
			this.p = 1;
		}
		this.column = req.getParameter("column");
		this.keyword = req.getParameter("keyword");
	}
	
	//1. 계산 메소드(설정은 페이지크기(출력할 게시글) 10, 블록 크기(페이지네이션 버튼 수) 10으로 설정한다)
	private int pageSize = 10;
	private int blockSize = 10;
	private int begin, end;
	private int startBlock, finishBlock, lastBlock;
	private List<BoardDto> list;
	
	public void calculate() throws Exception{
		//count 계산
		BoardDao boardDao = new BoardDao();
		if(isMode()) {
			this.count = boardDao.countBlock(column, keyword);
		}else {
			this.count = boardDao.countBlock();
		}
		
		//rownum 계산
		this.end = this.p * this.pageSize;
		this.begin = this.end - (this.pageSize - 1);
		
		//block 계산
		this.lastBlock = (this.count - 1) / this.pageSize +1;
		this.startBlock = (this.p - 1) / this.blockSize * this.blockSize + 1;
		this.finishBlock = this.startBlock + (this.blockSize - 1);
		
		//list 계산
		if(this.isMode()) {
			this.list = boardDao.listByTreeSortSearch(begin, end, column, keyword);//계층형 정렬 - 검색
		} else {
			this.list = boardDao.listByTreeSort(begin, end);//계층형 정렬 - 전체
		}
		
	}
	
	//검색모드인지 판별하는 메소드
	public boolean isMode() {
		return this.column != null && !this.column.equals("") &&
					this.keyword != null && !this.keyword.equals("");
 	}
	//이전이 존재하는 판별하는 메소드
	public boolean isPreviousAvailable() {
		return this.startBlock > 1;
	}
	//이전을 누르면 나오는 블록 번호
	public int getPreviousPageNumber() {
		return this.startBlock - 1;
	}
	//다음이 존재하는 판별하는 메소드
	public boolean isNextAvailable() {
		return this.finishBlock < this.lastBlock;
	}
	//다음을 누르면 나오는 블록 번호
	public int getNextPageNumber() {
		return this.finishBlock + 1;
	}
	//진짜 마지막 블록 번호를 반환하는 메소드
	public int getRealLastBlock() {
		return Math.min(this.finishBlock, this.lastBlock);
	}
	//컬럼이 특정 값인지 검사(아마 존재하는 필드명인지 판별해서 걸러내는 느낌인듯?)
	public boolean columnIs(String column) {
		return this.column != null && this.column.equals(column);
	}
	//null을 제거한 keyword 반환 메서드
	public String getKeywordString() {
		if(this.keyword == null) {
			return "";
		}else {
			return this.keyword;
		}
	}
	
	public int getP() {
		return p;
	}

	public String getColumn() {
		return column;
	}

	public String getKeyword() {
		return keyword;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public int getFinishBlock() {
		return finishBlock;
	}

	public int getLastBlock() {
		return lastBlock;
	}

	public List<BoardDto> getList() {
		return list;
	}

	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	
}
