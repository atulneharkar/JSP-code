package com.survey.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.survey.beans.SurveyBean;
import com.survey.business.SurveyBusiness;

/**
 * Servlet implementation class SurveyController
 */
@WebServlet("/SurveyController")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<SurveyBean> list = new ArrayList<SurveyBean>();
		
		SurveyBusiness surveyBusiness = new SurveyBusiness();
		
		try {
			list = surveyBusiness.getSurveyList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/Survey.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String deleteSurveyId = null;
		
		deleteSurveyId = request.getParameter("delete-survey");

			ArrayList<SurveyBean> list = new ArrayList<SurveyBean>();
			String surveyName = request.getParameter("survey-name");
			String surveyStart = request.getParameter("survey-start");
			String surveyEnd = request.getParameter("survey-end");
			String surveyId = request.getParameter("survey-id");
			String result = null;
			
			SurveyBean surveyBean = new SurveyBean();
			surveyBean.setSurveyName(surveyName);
			surveyBean.setSurveyStart(surveyStart);
			surveyBean.setSurveyEnd(surveyEnd);
			
			if(deleteSurveyId != null) {
				surveyBean.setSurveyId(deleteSurveyId);
			} else {
			    surveyBean.setSurveyId(surveyId);
			}
						
			SurveyBusiness surveyBusiness = new SurveyBusiness();
			
			try {
				if(deleteSurveyId != null) {
					result = surveyBusiness.deleteSurvey(surveyBean);
				} else {
					result = surveyBusiness.addSurvey(surveyBean);
				}
			    
				list = surveyBusiness.getSurveyList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);

		
		request.getRequestDispatcher("/Survey.jsp").forward(request, response);
	}

}
