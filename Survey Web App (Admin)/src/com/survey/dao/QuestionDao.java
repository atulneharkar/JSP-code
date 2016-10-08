package com.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.survey.beans.QuestionBean;
import com.survey.beans.SurveyBean;

public class QuestionDao {
	public String addQuestion(QuestionBean questionBean) throws Exception {
		String idTest = (questionBean.getQuestionId());
		String questionId = questionBean.getQuestionId();
		String questionType = questionBean.getQuestionType();
		String question = questionBean.getQuestion();
		String categoryId = questionBean.getCategoryId();
		String surveyId = questionBean.getSurveyId();
		String importance = questionBean.getImportance();
		String resultset = "";

		if (idTest.length() != 0) {
			int quesId = Integer.valueOf(questionBean.getQuestionId().trim());
			DBManager dm = new DBManager();
			Connection conn = dm.getConnection();
			if (conn != null) {
				String query = "UPDATE survey_questionMaster set CategoryId = '"
						+ categoryId
						+ "', Type = '"
						+ questionType
						+ "', Question = '"
						+ question
						+ "', surveyId = '"
						+ surveyId
						+ "', importance = '"
						+ importance
						+ "' where ID = " + quesId + "";
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

				stmt = conn
						.prepareCall("{ CALL proc_surveycreatequestions(?,?,?,?,?)}");

				stmt.setString(1, questionBean.getSurveyId());
				stmt.setString(2, questionBean.getCategoryId());
				stmt.setString(3, questionBean.getQuestionType());
				stmt.setString(4, questionBean.getQuestion());
				stmt.setString(5, questionBean.getImportance());

				stmt.executeUpdate();

				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultset;
	}

	public String deleteQuestion(QuestionBean questionBean) throws Exception {
		String resultset = "";
		int quesId = Integer.valueOf(questionBean.getQuestionId().trim());

		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "UPDATE survey_questionMaster set active = '0' where ID = "
					+ quesId + "";
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

	public ArrayList<QuestionBean> getQuestionList() throws Exception {
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();

		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "SELECT * FROM survey_questionMaster where active = 1";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					QuestionBean question = new QuestionBean();
					question.setQuestionId(rs.getString("ID"));
					question.setCategoryId(rs.getString("CATEGORYID"));
					question.setCategoryName(getCategoryName(conn,
							rs.getString("CATEGORYID")));
					question.setSurveyId(rs.getString("SURVEYID"));
					question.setSurveyName(getSurveyName(conn,
							rs.getString("SURVEYID")));
					question.setQuestionType(rs.getString("TYPE"));
					question.setQuestion(rs.getString("QUESTION"));
					question.setImportance(rs.getString("IMPORTANCE"));

					questionList.add(question);
				}
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return questionList;
	}

	// get survey name from survey id
	public String getSurveyName(Connection con, String surveyid) {
		String value = null;
		String query = "SELECT NAME FROM survey_surveymaster where ID = "
				+ surveyid;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				value = rs.getString("NAME");
				// System.out.println("Survey Name :" + rs.getString("NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	// get category name from category id
	public String getCategoryName(Connection con, String categoryid) {
		String catvalue = null;
		String query = "SELECT NAME FROM survey_categorymaster where ID = "
				+ categoryid;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				catvalue = rs.getString("NAME");
				// System.out.println("Survey Name :" + rs.getString("NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catvalue;
	}

}
