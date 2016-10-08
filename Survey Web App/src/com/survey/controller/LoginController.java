package com.survey.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonArray;
import com.survey.beans.LoginBean;
import com.survey.business.LoginBusiness;
import com.survey.business.SurveyBusiness;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
            super();
    }

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	
    	String result = null;
    	String userName = null;
    	String password = null;
    	
    	StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj;
		try {
			jObj = new JSONObject(sb.toString());
			userName = jObj.getString("userName");
		    password = jObj.getString("password");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(userName != null && password != null) {
			LoginBean loginBean = new LoginBean();
			loginBean.setUserName(userName);
			loginBean.setPassword(password);
			
			LoginBusiness loginBusiness = new LoginBusiness();
			
			try {
				result = loginBusiness.authenticateUser(loginBean);
				result = result.toString();
				
				if(result != null && result.equals("User")) {
					loginBean.setBdmName("");
					SurveyBusiness surveyBusiness = new SurveyBusiness();
					JSONObject surveyList = new JSONObject();
					surveyList = surveyBusiness.getSurveyList(userName);
					
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
			        response.getWriter().write(surveyList.toString());
					
				} else {
					
					response.setContentType("text/plain");
			        response.setCharacterEncoding("UTF-8");
			        response.getWriter().write("error");
					
				}
	            
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
 
    }
}
