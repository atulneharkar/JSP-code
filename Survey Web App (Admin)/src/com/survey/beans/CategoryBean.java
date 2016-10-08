package com.survey.beans;

public class CategoryBean {
	private String surveyId;
	private String categoryId;
	private String categoryName;
	
	public String getSurveyId() {
		return surveyId;
	}
	
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	  
	public void setCategoryName(String name) {
		categoryName = name;
	}

}
