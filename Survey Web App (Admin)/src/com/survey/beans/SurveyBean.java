package com.survey.beans;

  public class SurveyBean {
	private String surveyId;
    private String surveyName;
    private String surveyStart;
    private String surveyEnd;
  

	  public String getSurveyId() {
		  return surveyId;
	  }
	
	  public void setSurveyId(String surveyId) {
		  this.surveyId = surveyId;
	  }
	
	  public String getSurveyName() {
		  return surveyName;
	  }
	  
	  public void setSurveyName(String name) {
		  surveyName = name;
	  }
	  
	  public String getSurveyStart() {
		  return surveyStart;
	  }
	  
	  public void setSurveyStart(String startDate) {
		  surveyStart = startDate;
	  }
	  
	  public String getSurveyEnd() {
		  return surveyEnd;
	  }
	  
	  public void setSurveyEnd(String endDate) {
		  surveyEnd = endDate;
	  }
  }
