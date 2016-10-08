package com.survey.business;

import java.util.ArrayList;

import com.survey.beans.QuestionBean;
import com.survey.dao.QuestionDao;


public class QuestionBusiness {

	public String addQuestion(QuestionBean questionBean) throws Exception {
		String result = null;
		QuestionDao questionDao = new QuestionDao();
		result = questionDao.addQuestion(questionBean);
		questionDao.getQuestionList();
		return result;
	}
	
	public String deleteQuestion(QuestionBean questionBean) throws Exception {
		String result = null;
		QuestionDao questionDao = new QuestionDao();
		result = questionDao.deleteQuestion(questionBean);
		questionDao.getQuestionList();
		return result;
	}

	public ArrayList<QuestionBean> getQuestionList() throws Exception {

		QuestionDao questionDao = new QuestionDao();
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		questionList = questionDao.getQuestionList();

		return questionList;
	}
}
