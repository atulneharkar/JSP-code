package com.survey.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.survey.beans.CategoryBean;
import com.survey.beans.SurveyBean;
import com.survey.business.CategoryBusiness;
import com.survey.business.SurveyBusiness;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		CategoryBusiness categoryBusiness = new CategoryBusiness();
		
		try {
			categoryList = categoryBusiness.getCategoryList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("categoryList", categoryList);
		
		
		ArrayList<SurveyBean> list = new ArrayList<SurveyBean>();
		SurveyBusiness surveyBusiness = new SurveyBusiness();
		
		try {
			list = surveyBusiness.getSurveyList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("surveyList", list);
		request.getRequestDispatcher("/Category.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String deleteCategoryId = null;
		
		deleteCategoryId = request.getParameter("delete-category");
		
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		
		String surveyId = request.getParameter("survey-id");
		String categoryId = request.getParameter("category-id");
		String categoryName = request.getParameter("category-name");
		String result = null;
		
		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setSurveyId(surveyId);
		categoryBean.setCategoryName(categoryName);
		
		if(deleteCategoryId != null) {
			categoryBean.setCategoryId(deleteCategoryId);
		} else {
			categoryBean.setCategoryId(categoryId);
		}
		
		CategoryBusiness categoryBusiness = new CategoryBusiness();
		
		try {
			if(deleteCategoryId != null) {
				result = categoryBusiness.deleteCategory(categoryBean);
			} else {
				result = categoryBusiness.addCategory(categoryBean);
			}
		    
		    categoryList = categoryBusiness.getCategoryList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categoryList", categoryList);
		
		ArrayList<SurveyBean> list = new ArrayList<SurveyBean>();
		SurveyBusiness surveyBusiness = new SurveyBusiness();
		
		try {
			list = surveyBusiness.getSurveyList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("surveyList", list);
		
		request.getRequestDispatcher("/Category.jsp").forward(request, response);
	}

}
