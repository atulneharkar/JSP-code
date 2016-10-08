package com.survey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.survey.beans.ReportBean;

public class ReportDao {

	//public ArrayList<ReportBean> getReportList() throws Exception {
	public JsonArray getReportList() throws Exception {

		// ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
		JsonArray reportList = new JsonArray();

		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "SELECT survey_answerMaster.USERID, survey_answerMaster.SURVEYID, survey_answerMaster.CATEGORYID, survey_answerMaster.QUESTIONID, survey_answerMaster.ANSWER, survey_answerMaster.REMARKS, survey_questionMaster.IMPORTANCE, survey_categoryMaster.NAME FROM survey_answerMaster INNER JOIN survey_questionMaster ON survey_answerMaster.questionid = survey_questionMaster.id INNER JOIN survey_categoryMaster ON survey_answerMaster.CATEGORYID = survey_categoryMaster.ID";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					JsonObject reportObject = new JsonObject();

					reportObject.addProperty("userId", rs.getString("USERID"));
					reportObject.addProperty("surveyId", rs.getString("SURVEYID"));
					reportObject.addProperty("categoryId", rs.getString("CATEGORYID"));
					reportObject.addProperty("questionId", rs.getString("QUESTIONID"));
					reportObject.addProperty("answer", rs.getString("ANSWER"));
					reportObject.addProperty("remarks", rs.getString("REMARKS"));
					reportObject.addProperty("importance", rs.getString("IMPORTANCE"));
					reportObject.addProperty("categoryName", rs.getString("NAME"));
					// ReportBean report = new ReportBean();
					// report.setUserId(rs.getString("USERID"));
					// report.setSurveyId(rs.getString("SURVEYID"));
					// report.setQuestionId(rs.getString("QUESTIONID"));
					// report.setAnswer(rs.getString("ANSWER"));
					// report.setRemarks(rs.getString("REMARKS"));
					// report.setImportance(rs.getString("IMPORTANCE"));

					reportList.add(reportObject);
				}
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		
		return reportList;
	}

}
