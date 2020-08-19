package com.shifthunter.regexpress;

import java.util.List;

public class RegExpress {
	
	private int qtdGenRandom;
	private String singleExpression;
	private String[] expressions;
	private List<String> expressionList;
	private List<String>[] expressionListArray;
	private String timestamp;
	
	public RegExpress() {}
	
	public RegExpress(int qtdGenRandom, String singleExpression, String[] expressions, List<String> expressionList, List<String>[] expressionListArray, String timestamp) {
		this.qtdGenRandom = qtdGenRandom;
		this.singleExpression = singleExpression;
		this.expressions = expressions;
		this.expressionList = expressionList;
		this.expressionListArray = expressionListArray;
		this.timestamp = timestamp;
	}
		
	public int getQtdGenRandom() {
		return qtdGenRandom;
	}

	public void setQtdGenRandom(int qtdGenRandom) {
		this.qtdGenRandom = qtdGenRandom;
	}

	public String getSingleExpression() {
		return singleExpression;
	}

	public void setSingleExpression(String singleExpression) {
		this.singleExpression = singleExpression;
	}
	
	public String[] getExpressions() {
		return expressions;
	}

	public void setExpressions(String[] expressions) {
		this.expressions = expressions;
	}

	public List<String> getExpressionList() {
		return expressionList;
	}

	public void setExpressionList(List<String> expressionList) {
		this.expressionList = expressionList;
	}

	public List<String>[] getExpressionListArray() {
		return expressionListArray;
	}

	public void setExpressionListArray(List<String>[] expressionListArray) {
		this.expressionListArray = expressionListArray;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}