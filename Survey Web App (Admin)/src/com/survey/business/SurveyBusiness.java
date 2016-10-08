package com.survey.business;

import java.util.ArrayList;

import com.survey.beans.SurveyBean;
import com.survey.dao.SurveyDao;

public class SurveyBusiness {

	public String addSurvey(SurveyBean surveyBean) throws Exception {
		String result = null;
		SurveyDao surveyDao = new SurveyDao();
		result = surveyDao.addSurvey(surveyBean);
		surveyDao.getSurveyList();
		return result;
	}

	public ArrayList<SurveyBean> getSurveyList() throws Exception {

		SurveyDao surveyDao = new SurveyDao();
		ArrayList<SurveyBean> surveyList = new ArrayList<SurveyBean>();
		surveyList = surveyDao.getSurveyList();

		return surveyList;
	}
	
	public String deleteSurvey(SurveyBean surveyBean) throws Exception {
		String result = null;
		SurveyDao surveyDao = new SurveyDao();
		result = surveyDao.deleteSurvey(surveyBean);
		surveyDao.getSurveyList();
		return result;
	}
}
