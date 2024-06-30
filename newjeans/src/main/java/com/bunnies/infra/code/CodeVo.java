package com.bunnies.infra.code;

public class CodeVo {
	
	private String codeSeq;
	private String codegroupSeqF;
	private String codeName;
	
	//getter, setter method
	public String getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getCodegroupSeqF() {
		return codegroupSeqF;
	}
	public void setCodegroupSeqF(String codegroupSeqF) {
		this.codegroupSeqF = codegroupSeqF;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	//toString()
	@Override
	public String toString() {
		return "CodeVo [codeSeq=" + codeSeq + ", codegroupSeqF=" + codegroupSeqF + ", codeName=" + codeName + "]";
	}
	
}
