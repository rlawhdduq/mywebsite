package mywebsite.beans;

import java.text.DecimalFormat;

public class PointDto {
	private int pointNo;
	private String pointName;
	private int pointAmount;
	public int getPointNo() {
		return pointNo;
	}
	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public int getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}
	public PointDto() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	//데시멀 포맷을 적용시킨 pointAmount
	public String getDecimalFormatPointAmount() {
		DecimalFormat d = new DecimalFormat("#,##0");
		return d.format(this.pointAmount);
	}
}
