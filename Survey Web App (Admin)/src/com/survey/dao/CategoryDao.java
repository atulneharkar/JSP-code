package com.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.survey.beans.CategoryBean;
import com.survey.beans.SurveyBean;

public class CategoryDao {

	public String addCategory(CategoryBean categoryBean) throws Exception {
		String idTest = (categoryBean.getCategoryId());
		String categoryId = categoryBean.getCategoryId();
		String surveyId = categoryBean.getSurveyId();
		String categoryName = categoryBean.getCategoryName();
		String resultset = "";
		
		if(idTest.length() != 0) {
			int catId = Integer.valueOf(categoryBean.getCategoryId().trim());
			DBManager dm = new DBManager();
			Connection conn = dm.getConnection();
			if (conn != null) {
				String query = "UPDATE survey_categoryMaster set NAME = '"+ categoryName +"', SURVEYID = '"+ surveyId +"' where ID = " + catId + "";
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

				stmt = conn.prepareCall("{ CALL proc_surveycreatecategory(?,?)}");

				stmt.setString(1, categoryBean.getSurveyId());
				stmt.setString(2, categoryBean.getCategoryName());

				stmt.executeUpdate();

				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultset;
	}
	
	public String deleteCategory(CategoryBean categoryBean) throws Exception {
		String resultset = "";
		int catId = Integer.valueOf(categoryBean.getCategoryId().trim());
		
		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "UPDATE survey_categoryMaster set active = '0' where ID = " + catId + "";
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
	
	public ArrayList<CategoryBean> getCategoryList() throws Exception {
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		
		DBManager dm = new DBManager();
		Connection conn = dm.getConnection();
		if (conn != null) {
			String query = "SELECT * FROM survey_categoryMaster where active = 1";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					CategoryBean category = new CategoryBean();
					category.setCategoryId(rs.getString("ID"));
					category.setCategoryName(rs.getString("NAME"));
					category.setSurveyId(rs.getString("SURVEYID"));

					categoryList.add(category);
				}
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return categoryList;
	}
}
