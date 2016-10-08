package com.survey.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.survey.beans.CategoryBean;
import com.survey.beans.ReportBean;
import com.survey.beans.SurveyBean;
import com.survey.business.CategoryBusiness;
import com.survey.business.ReportBusiness;
import com.survey.business.SurveyBusiness;

/**
 * Servlet implementation class ReportController
 */
@WebServlet("/ReportController")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportController() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<SurveyBean> surveyList = new ArrayList<SurveyBean>();
		SurveyBusiness surveyBusiness = new SurveyBusiness();
		
		try {
			surveyList = surveyBusiness.getSurveyList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("surveyList", surveyList);
		
		 JsonArray reportList = new JsonArray();
			
			ReportBusiness reportBusiness = new ReportBusiness();
			
			try {
				reportList = reportBusiness.getReportList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("reportList", reportList);
			request.getRequestDispatcher("/Report.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
