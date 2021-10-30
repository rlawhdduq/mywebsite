package mywebsite.beans;

import java.sql.Date;

//게시판 정보를 담을 Dto
public class BoardDto {
	private int boardNo;
	private String memberId;
	private String memberNick;
	private String boardTitle;
	private String boardContent;
	private Date boardUploadTime;
	private int boardHit;
	private int boardLike;
	private int boardUnlike;
	private int boardSuperno;
	private int boardGroupno;
	private int boardReply;
	private int boardDepth;
	
	public int getBoardSuperno() {
		return boardSuperno;
	}
	public void setBoardSuperno(int boardSuperno) {
		this.boardSuperno = boardSuperno;
	}
	public int getBoardGroupno() {
		return boardGroupno;
	}
	public void setBoardGroupno(int boardGroupno) {
		this.boardGroupno = boardGroupno;
	}
	public int getBoardReply() {
		return boardReply;
	}
	public void setBoardReply(int boardReply) {
		this.boardReply = boardReply;
	}
	public int getBoardDepth() {
		return boardDepth;
	}
	public void setBoardDepth(int boardDepth) {
		this.boardDepth = boardDepth;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardUploadTime() {
		return boardUploadTime;
	}
	public void setBoardUploadTime(Date boardUploadTime) {
		this.boardUploadTime = boardUploadTime;
	}
	public int getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	public int getBoardUnlike() {
		return boardUnlike;
	}
	public void setBoardUnlike(int boardUnlike) {
		this.boardUnlike = boardUnlike;
	}
	public BoardDto() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	
	
}
