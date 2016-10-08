package com.survey.business;

import java.util.ArrayList;

import com.survey.beans.CategoryBean;
import com.survey.dao.CategoryDao;


public class CategoryBusiness {

	public String addCategory(CategoryBean categoryBean) throws Exception {
		String result = null;
		CategoryDao categoryDao = new CategoryDao();
		result = categoryDao.addCategory(categoryBean);
		categoryDao.getCategoryList();
		return result;
	}

	public ArrayList<CategoryBean> getCategoryList() throws Exception {

		CategoryDao categoryDao = new CategoryDao();
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		categoryList = categoryDao.getCategoryList();

		return categoryList;
	}
	
	public String deleteCategory(CategoryBean categoryBean) throws Exception {
		String result = null;
		CategoryDao categoryDao = new CategoryDao();
		result = categoryDao.deleteCategory(categoryBean);
		categoryDao.getCategoryList();
		return result;
	}
}
