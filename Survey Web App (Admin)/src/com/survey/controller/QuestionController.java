package com.survey.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.survey.beans.CategoryBean;
import com.survey.beans.QuestionBean;
import com.survey.beans.SurveyBean;
import com.survey.business.CategoryBusiness;
import com.survey.business.QuestionBusiness;
import com.survey.business.SurveyBusiness;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		QuestionBusiness questionBusiness = new QuestionBusiness();
		
		try {
			questionList = questionBusiness.getQuestionList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("questionList", questionList);
		
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		CategoryBusiness categoryBusiness = new CategoryBusiness();
		
		try {
			categoryList = categoryBusiness.getCategoryList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("categoryList", categoryList);
		
		
		ArrayList<SurveyBean> list = new ArrayList<SurveyBean>();
		SurveyBusiness surveyBusiness = new SurveyBusiness();
		
		try {
			list = surveyBusiness.getSurveyList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("surveyList", list);
		
		request.getRequestDispatcher("/Question.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String deleteQuestionId = null;
		
		deleteQuestionId = request.getParameter("delete-question");
		
		ArrayList<QuestionBean> list = new ArrayList<QuestionBean>();
		
		String surveyId = request.getParameter("survey-id");
		String categoryId = request.getParameter("category-id");
		String questionType = request.getParameter("question-type");
		String questionId = request.getParameter("question-id");
		String question = request.getParameter("question");
		String importance = request.getParameter("question-importance");
		String result = null;
		
		QuestionBean questionBean = new QuestionBean();
		questionBean.setSurveyId(surveyId);
		questionBean.setCategoryId(categoryId);
		questionBean.setQuestionType(questionType);
		questionBean.setQuestionId(questionId);
		questionBean.setQuestion(question);
		questionBean.setImportance(importance);
		
		if(deleteQuestionId != null) {
			questionBean.setQuestionId(deleteQuestionId);
		} else {
			questionBean.setQuestionId(questionId);
		}
		
		QuestionBusiness questionBusiness = new QuestionBusiness();
		
		try {
			if(deleteQuestionId != null) {
				result = questionBusiness.deleteQuestion(questionBean);
			} else {
				result = questionBusiness.addQuestion(questionBean);
			}
		    
			list = questionBusiness.getQuestionList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("questionList", list);
		
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		CategoryBusiness categoryBusiness = new CategoryBusiness();
		
		try {
			categoryList = categoryBusiness.getCategoryList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("categoryList", categoryList);
		
		
		ArrayList<SurveyBean> surveyList = new ArrayList<SurveyBean>();
		SurveyBusiness surveyBusiness = new SurveyBusiness();
		
		try {
			surveyList = surveyBusiness.getSurveyList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("surveyList", surveyList);
				
		request.getRequestDispatcher("/Question.jsp").forward(request, response);
	}

}
