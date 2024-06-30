package com.bunnies.infra.member;

import java.util.Date;

public class MemberDto {
	
	private String memberSeq;
	private Integer managerNY;
	private Integer foreignerNY;
	private String memberID;
	private String memberPWD;
	private String memberFirstName;
	private String memberLastName;
	private String memberNickName;
	private Integer memberGradeCD;
	private Integer memberGenderCD;
	private String memberEmail;
	private String memberPhone;
	private Date memberRegDate;
	private Date memberModDate;
	private Integer memberDelNY;
	
	//getter, setter
	public String getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(String memberSeq) {
		this.memberSeq = memberSeq;
	}
	public Integer getManagerNY() {
		return managerNY;
	}
	public void setManagerNY(Integer managerNY) {
		this.managerNY = managerNY;
	}
	public Integer getForeignerNY() {
		return foreignerNY;
	}
	public void setForeignerNY(Integer foreignerNY) {
		this.foreignerNY = foreignerNY;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberPWD() {
		return memberPWD;
	}
	public void setMemberPWD(String memberPWD) {
		this.memberPWD = memberPWD;
	}
	public String getMemberFirstName() {
		return memberFirstName;
	}
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}
	public String getMemberLastName() {
		return memberLastName;
	}
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public Integer getMemberGradeCD() {
		return memberGradeCD;
	}
	public void setMemberGradeCD(Integer memberGradeCD) {
		this.memberGradeCD = memberGradeCD;
	}
	public Integer getMemberGenderCD() {
		return memberGenderCD;
	}
	public void setMemberGenderCD(Integer memberGenderCD) {
		this.memberGenderCD = memberGenderCD;
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
	public Date getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public Date getMemberModDate() {
		return memberModDate;
	}
	public void setMemberModDate(Date memberModDate) {
		this.memberModDate = memberModDate;
	}
	public Integer getMemberDelNY() {
		return memberDelNY;
	}
	public void setMemberDelNY(Integer memberDelNY) {
		this.memberDelNY = memberDelNY;
	}
	
	//toString()
	@Override
	public String toString() {
		return "MemberDto [memberSeq=" + memberSeq + ", managerNY=" + managerNY + ", foreignerNY=" + foreignerNY
				+ ", memberID=" + memberID + ", memberPWD=" + memberPWD + ", memberFirstName=" + memberFirstName
				+ ", memberLastName=" + memberLastName + ", memberNickName=" + memberNickName + ", memberGradeCD="
				+ memberGradeCD + ", memberGenderCD=" + memberGenderCD + ", memberEmail=" + memberEmail
				+ ", memberPhone=" + memberPhone + ", memberRegDate=" + memberRegDate + ", memberModDate="
				+ memberModDate + ", memberDelNY=" + memberDelNY + "]";
	}
	
}
