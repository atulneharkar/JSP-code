package com.survey.business;

import com.survey.beans.LoginBean;
import com.survey.dao.LoginDao;

public class LoginBusiness {
	
	public String authenticateUser(LoginBean loginBean) throws Exception {
		String result = null;
		
		LoginDao loginDao = new LoginDao();
		
		result = loginDao.authenticateUser(loginBean);
		return result;
	}

}
