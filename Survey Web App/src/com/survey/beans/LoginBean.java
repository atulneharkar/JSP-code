package com.survey.beans;

public class LoginBean {
	
	private String userName;
	private String password;
	private String bdmName;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setBdmName(String bdmName) {
		this.bdmName = bdmName;
	}
	
	public String getBdmName() {
		return bdmName;
	}

}
