package com.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.survey.dao.DBManager;
import com.survey.beans.SurveyBean;

public class SurveyDao {

	public String addSurvey(SurveyBean surveyBean) throws Exception {
		String idTest=(surveyBean.getSurveyId());
		String surveyName = surveyBean.getSurveyName();
		String surveyStart = surveyBean.getSurveyStart();
		String surveyEnd = surveyBean.getSurveyEnd();
		String resultset = "";
		if(idTest.length() != 0) {
			int surveyId = Integer.valueOf(surveyBean.getSurveyId().trim());
			DBManager dm = new DBManager();
			Connection conn = dm.getConnection();
			if (conn != null) {
				String query = "UPDATE survey_surveyMaster set NAME = '"+ surveyName +"', STARTDATE = '"+ surveyStart +"', ENDDATE = '"+ surveyEnd +"' where ID = " + surveyId + "";
				try {
					PreparedStatement pstmt = conn.prepareStatement(query);
					ResultSet rs = pstmt.executeQuery();
					conn.commit();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		} else {
			CallableStatement stmt = null;
			PreparedStatement pstmt = null;
			try {
				DBManager dm = new DBManager();
				Connection conn = dm.getConnection();

				stmt = conn.prepareCall("{ CALL proc_serveyCreate(?,?,?)}");

				stmt.setString(1, surveyBean.getSurveyName());
				stmt.setString(2, surveyBean.getSurveyStart());
				stmt.setString(3, surveyBean.getSurveyEnd());

				stmt.executeUpdate();

				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultset;
	}
	
	public String deleteSurvey(SurveyBean surveyBean) throws Exception {
		String resultset = "";
		int surveyId = Integer.valueOf(surveyBean.getSurveyId().trim());
		
		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "UPDATE survey_surveyMaster set active = '0' where ID = " + surveyId + "";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultset;
	}

	public ArrayList<SurveyBean> getSurveyList() throws Exception {

		ArrayList<SurveyBean> surveyList = new ArrayList<SurveyBean>();

		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "SELECT * FROM survey_surveyMaster where active=1";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					SurveyBean survey = new SurveyBean();
					survey.setSurveyId(rs.getString("ID"));
					survey.setSurveyName(rs.getString("NAME"));
					survey.setSurveyStart(rs.getString("STARTDATE"));
					survey.setSurveyEnd(rs.getString("ENDDATE"));

					surveyList.add(survey);
				}
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return surveyList;
	}
}
