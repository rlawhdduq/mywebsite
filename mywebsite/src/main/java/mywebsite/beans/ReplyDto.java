package mywebsite.beans;

import java.sql.Date;

public class ReplyDto {
	private int replyNo;
	private String replyContent;
	private String memberId;
	private int boardNo;
	private int rating;
	private int replyLike;
	private Date replyUpload;
	public Date getReplyUpload() {
		return replyUpload;
	}
	public void setReplyUpload(Date replyUpload) {
		this.replyUpload = replyUpload;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	private int replyUnLike;
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getReplyLike() {
		return replyLike;
	}
	public void setReplyLike(int replyLike) {
		this.replyLike = replyLike;
	}
	public int getReplyUnLike() {
		return replyUnLike;
	}
	public void setReplyUnLike(int replyUnLike) {
		this.replyUnLike = replyUnLike;
	}
	public ReplyDto() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
}
