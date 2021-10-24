package mywebsite.beans;

import java.sql.Date;

public class MemberDto {
	private String memberId;
	private String memberPw;
	private String memberNick;
	private String memberEmail;
	private String memberPhone;
	private Date memberBirth;
	private Date memberJoin;
	private String memberGrade;
	private int memberPoint;
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public MemberDto() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public Date getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	public Date getMemberJoin() {
		return memberJoin;
	}
	public void setMemberJoin(Date memberJoin) {
		this.memberJoin = memberJoin;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	
	//null 값을 공백으로 처리하는 게터(이메일, 전화번호)
	public String getMemberEmailString() {
		if(this.memberEmail == null) {
			return this.memberEmail = "";
		} else {			
			return memberEmail;
		}
	}
	public String getMemberPhoneString() {
		if(this.memberPhone == null) {
			return this.memberPhone = "";
		} else {
			return memberPhone;
		}
	}
}
