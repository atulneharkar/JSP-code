package com.survey.business;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.survey.beans.ReportBean;
import com.survey.dao.ReportDao;

public class ReportBusiness {
	
	public JsonArray getReportList() throws Exception {

		ReportDao reportDao = new ReportDao();
		JsonArray reportList = new JsonArray();
		reportList = reportDao.getReportList();

		return reportList;
	}

}
