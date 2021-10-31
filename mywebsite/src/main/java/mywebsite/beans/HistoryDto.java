package mywebsite.beans;

import java.sql.Date;

public class HistoryDto {
	private int historyNo;
	private String memberId;
	private Date historyTime;
	private String historyMemo;
	private int historyAmount;
	public int getHistoryNo() {
		return historyNo;
	}
	public void setHistoryNo(int historyNo) {
		this.historyNo = historyNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getHistoryTime() {
		return historyTime;
	}
	public void setHistoryTime(Date historyTime) {
		this.historyTime = historyTime;
	}
	public String getHistoryMemo() {
		return historyMemo;
	}
	public void setHistoryMemo(String historyMemo) {
		this.historyMemo = historyMemo;
	}
	public int getHistoryAmount() {
		return historyAmount;
	}
	public void setHistoryAmount(int historyAmount) {
		this.historyAmount = historyAmount;
	}
	public HistoryDto() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	
}
