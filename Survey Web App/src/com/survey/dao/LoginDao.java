package com.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import com.survey.beans.LoginBean;

public class LoginDao {

	public String authenticateUser(LoginBean loginBean) throws Exception {
		String result = null;
		String userName = loginBean.getUserName();
		String password = loginBean.getPassword();

		CallableStatement stmt = null;
		PreparedStatement pstmt = null;

		try {
			DBManagerASF dm = new DBManagerASF();
			Connection conn = dm.getConnection();

			stmt = conn.prepareCall("{ CALL PROC_LOGIN_CHECK(?,?,?) }");

			stmt.setString(1, userName);
			stmt.setString(2, password);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);

			stmt.executeUpdate();

			result = stmt.getString(3);
			System.out.println(result);
			conn.commit();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
