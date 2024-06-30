package com.bunnies.infra.codegroup;

import java.util.Arrays;
import java.util.Date;

public class CodeGroupDto {
	private String codegroupSeq;
	private String codegroupName;
	private Integer codegroupDelNY;
	private Date codegroupRegDate;
	private Date codegroupModDate;

	private Integer xcount;
	
	private String[] checkboxSeqArray  = null;

	//getter, setter
	public String getCodegroupSeq() {
		return codegroupSeq;
	}

	public void setCodegroupSeq(String codegroupSeq) {
		this.codegroupSeq = codegroupSeq;
	}

	public String getCodegroupName() {
		return codegroupName;
	}

	public void setCodegroupName(String codegroupName) {
		this.codegroupName = codegroupName;
	}

	public Integer getCodegroupDelNY() {
		return codegroupDelNY;
	}

	public void setCodegroupDelNY(Integer codegroupDelNY) {
		this.codegroupDelNY = codegroupDelNY;
	}

	public Date getCodegroupRegDate() {
		return codegroupRegDate;
	}

	public void setCodegroupRegDate(Date codegroupRegDate) {
		this.codegroupRegDate = codegroupRegDate;
	}

	public Date getCodegroupModDate() {
		return codegroupModDate;
	}

	public void setCodegroupModDate(Date codegroupModDate) {
		this.codegroupModDate = codegroupModDate;
	}

	public Integer getXcount() {
		return xcount;
	}

	public void setXcount(Integer xcount) {
		this.xcount = xcount;
	}

	public String[] getCheckboxSeqArray() {
		return checkboxSeqArray;
	}

	public void setCheckboxSeqArray(String[] checkboxSeqArray) {
		this.checkboxSeqArray = checkboxSeqArray;
	}

	@Override
	public String toString() {
		return "CodeGroupDto [codegroupSeq=" + codegroupSeq + ", codegroupName=" + codegroupName + ", codegroupDelNY="
				+ codegroupDelNY + ", codegroupRegDate=" + codegroupRegDate + ", codegroupModDate=" + codegroupModDate
				+ ", xcount=" + xcount + ", checkboxSeqArray=" + Arrays.toString(checkboxSeqArray) + "]";
	}

	
}