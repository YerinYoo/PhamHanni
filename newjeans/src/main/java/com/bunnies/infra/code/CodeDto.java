package com.bunnies.infra.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CodeDto {
	
	private String codeSeq;
	private String codeName;
	private Integer codeDelNY;
	private Date codeRegDate;
	private Date codeModDate;

	private String codegroupSeqF;
	private String CodeGroupName;
	
	//다중 셀렉 시 사용하게 될 배열 객체
	private String[] checkboxSeqArray  = null;
	
	//공통 코드를 캐시화하여 사용하기 위한 list 생성
	public static List<CodeDto> cachedCodeArrayList = new ArrayList<CodeDto>();

	//getter, setter method
	public String getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Integer getCodeDelNY() {
		return codeDelNY;
	}

	public void setCodeDelNY(Integer codeDelNY) {
		this.codeDelNY = codeDelNY;
	}

	public Date getCodeRegDate() {
		return codeRegDate;
	}

	public void setCodeRegDate(Date codeRegDate) {
		this.codeRegDate = codeRegDate;
	}

	public Date getCodeModDate() {
		return codeModDate;
	}

	public void setCodeModDate(Date codeModDate) {
		this.codeModDate = codeModDate;
	}

	public String getCodegroupSeqF() {
		return codegroupSeqF;
	}

	public void setCodegroupSeqF(String codegroupSeqF) {
		this.codegroupSeqF = codegroupSeqF;
	}

	public String getCodeGroupName() {
		return CodeGroupName;
	}

	public void setCodeGroupName(String codeGroupName) {
		CodeGroupName = codeGroupName;
	}

	public String[] getCheckboxSeqArray() {
		return checkboxSeqArray;
	}

	public void setCheckboxSeqArray(String[] checkboxSeqArray) {
		this.checkboxSeqArray = checkboxSeqArray;
	}

	public static List<CodeDto> getCachedCodeArrayList() {
		return cachedCodeArrayList;
	}

	public static void setCachedCodeArrayList(List<CodeDto> cachedCodeArrayList) {
		CodeDto.cachedCodeArrayList = cachedCodeArrayList;
	}

	//toString()
	@Override
	public String toString() {
		return "CodeDto [codeSeq=" + codeSeq + ", codeName=" + codeName + ", codeDelNY=" + codeDelNY + ", codeRegDate="
				+ codeRegDate + ", codeModDate=" + codeModDate + ", codegroupSeqF=" + codegroupSeqF + ", CodeGroupName="
				+ CodeGroupName + ", checkboxSeqArray=" + Arrays.toString(checkboxSeqArray) + "]";
	}
	
}
