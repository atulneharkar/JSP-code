package com.survey.business;

import org.codehaus.jettison.json.JSONObject;

import com.survey.dao.SurveyDao;

public class SurveyBusiness {
	
	public JSONObject getSurveyList(String userName) throws Exception {
		SurveyDao surveyDao = new SurveyDao();
		JSONObject surveyList = new JSONObject();
		surveyList = surveyDao.getSurveyList(userName);

		return surveyList;
	}

}
