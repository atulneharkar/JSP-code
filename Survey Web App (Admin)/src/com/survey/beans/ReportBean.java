package com.survey.beans;

public class ReportBean {
	
	  private String userId;
	  private String surveyId;
	  private String questionId;
	  private String answer;
	  private String remarks;
	  private String importance;
	  
	  public String getUserId() {
		  return userId;
	  }
	
	  public void setUserId(String userId) {
		  this.userId = userId;
	  }

	  public String getSurveyId() {
		  return surveyId;
	  }
	
	  public void setSurveyId(String surveyId) {
		  this.surveyId = surveyId;
	  }
	  
	  public String getQuestionId() {
		  return questionId;
	  }
	
	  public void setQuestionId(String questionId) {
		  this.questionId = questionId;
	  }
	  
	  public String getAnswer() {
		  return answer;
	  }
	
	  public void setAnswer(String answer) {
		  this.answer = answer;
	  }
	  
	  public String getRemarks() {
		  return remarks;
	  }
	
	  public void setRemarks(String remarks) {
		  this.remarks = remarks;
	  }
	  
	  public String getImportance() {
		  return importance;
	  }
	
	  public void setImportance(String importance) {
		  this.importance = importance;
	  }

}
