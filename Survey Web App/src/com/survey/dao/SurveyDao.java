package com.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class SurveyDao {

	public JSONObject getSurveyList(String userName) throws Exception {

		ResultSet surveyMaster = null, categoryMaster = null, questionMaster = null;

		CallableStatement stmt = null;

		JSONObject mainObject = new JSONObject();
		JSONArray surveyArray = new JSONArray();
		JSONArray categoryArray = new JSONArray();
		JSONArray questionArray = new JSONArray();

		try {
			DBManager dm = new DBManager();
			Connection conn = dm.getConnection();

			stmt = conn.prepareCall("{ CALL proc_getSurveydetails(?,?,?,?)}");
			stmt.setString(1, userName);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);
			stmt.executeUpdate();

			surveyMaster = (ResultSet) stmt.getObject(2);
			categoryMaster = (ResultSet) stmt.getObject(3);
			questionMaster = (ResultSet) stmt.getObject(4);

			while (surveyMaster.next()) {
				JSONObject surveySet = new JSONObject();
				surveySet.put("surveyId", surveyMaster.getString("id"));
				surveySet.put("surveyName", surveyMaster.getString("name"));
				surveySet.put("surveyStart",
						surveyMaster.getString("startdate"));
				surveySet.put("surveyEnd", surveyMaster.getString("enddate"));
				surveyArray.put(surveySet);
			}

			while (categoryMaster.next()) {
				JSONObject categorySet = new JSONObject();
				categorySet.put("categoryId", categoryMaster.getString("id"));
				categorySet.put("surveyid",
						categoryMaster.getString("surveyid"));
				categorySet.put("categoryName",
						categoryMaster.getString("name"));
				categoryArray.put(categorySet);
			}

			while (questionMaster.next()) {
				JSONObject questionSet = new JSONObject();
				questionSet.put("surveyid",
						questionMaster.getString("surveyid"));
				questionSet.put("categoryid",
						questionMaster.getString("categoryid"));
				questionSet.put("questionId", questionMaster.getString("id"));
				questionSet.put("type", questionMaster.getString("type"));
				questionSet.put("question",
						questionMaster.getString("question"));
				questionSet.put("importance",
						questionMaster.getString("importance"));
				questionArray.put(questionSet);
			}

			mainObject.put("message", "Success");

			JSONObject subobject = new JSONObject();
			subobject.put("Survey", surveyArray);
			subobject.put("Category", categoryArray);
			subobject.put("Question", questionArray);
			mainObject.put("surveyData", subobject);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mainObject;

	}

	public JSONObject surveySubmit(JSONArray data) throws Exception {

		JSONArray userarray;
		String surveyId = "";
		JSONObject sucess = new JSONObject();
		JSONObject result = null;

		try {

			userarray = data;
			if (userarray.length() > 0) {
				Object[][] senddate = new Object[userarray.length()][8];
				for (int i = 0; i < userarray.length(); i++) {
					JSONObject jsonObject = new JSONObject();
					jsonObject = userarray.getJSONObject(i);
					senddate[i][0] = jsonObject.getString("SurveyId");

					// System.out.println("senddate[i][0] ==="+senddate[i][0] );
					surveyId = surveyId + jsonObject.getString("SurveyId")
							+ ",";
					senddate[i][1] = jsonObject.getString("QuestionId");
					// System.out.println("senddate[i][1] ==="+senddate[i][1] );
					senddate[i][2] = jsonObject.getString("Answer");
					// System.out.println("senddate[i][2] ==="+senddate[i][2] );
					senddate[i][3] = jsonObject.getString("Date");
					// System.out.println("senddate[i][3] ==="+senddate[i][3] );
					senddate[i][4] = jsonObject.getString("UserId");
					// System.out.println("senddate[i][4] ==="+senddate[i][4] );
					senddate[i][5] = jsonObject.getString("CategoryId");
					// System.out.println("senddate[i][5] ==="+senddate[i][5] );
					senddate[i][6] = jsonObject.getString("Remarks");
					// System.out.println("senddate[i][6] ==="+senddate[i][6] );
					senddate[i][7] = jsonObject.getString("DataFrom");
					// System.out.println("senddate[i][7] ==="+senddate[i][7] );
				}

				CallableStatement stmt = null;
				DBManager dm = new DBManager();
				Connection conn = dm.getConnection();
				stmt = conn.prepareCall("{ CALL PROC_SUBMITSURVEYANSWER(?,?)}");
				ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor(
						"ARRAY_INSERT_SERVEYBULK", conn);
				ARRAY array_to_pass = new ARRAY(descriptor, conn, senddate);
				stmt.setArray(1, array_to_pass);
				stmt.registerOutParameter(2, OracleTypes.VARCHAR);
				stmt.executeUpdate();

				String value = stmt.getString(2);
				System.out.println("Status :" + value);
				surveyId = surveyId.substring(0, surveyId.length() - 1);
				result = new JSONObject();
				result.put("message", value);
				result.put("SurveyId", surveyId);
			} else {
				result = new JSONObject();
				result.put("message", "No Data to upload");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
