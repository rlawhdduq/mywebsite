package mywebsite.beans;

public class CancelDto {
	private int historyNo;
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getHistoryNo() {
		return historyNo;
	}

	public void setHistoryNo(int historyNo) {
		this.historyNo = historyNo;
	}

	public CancelDto() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	
}
