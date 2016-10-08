package com.survey.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.survey.dao.SurveyDao;

/**
 * Servlet implementation class SurveyController
 */
@WebServlet("/submitSurvey")
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SurveyDao surveyDao = new SurveyDao();
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        JSONObject result = new JSONObject();
        
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONArray jObj;
		try {
			jObj = new JSONArray(sb.toString());
			result = surveyDao.surveySubmit(jObj);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
