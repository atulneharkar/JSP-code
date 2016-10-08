<%@page import="com.documentum.fc.client.distributed.replica.impl.refresh.operation.SysObjectCopyOperation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import ="java.util.*" %>
		<%@ page import ="com.survey.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Survey</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css" />
</head>
<body>
  <div class="category-main-page">
  	<jsp:include page="templates/Header.jsp" />
  	<%
	  ArrayList<SurveyBean> surveyList = (ArrayList<SurveyBean>)request.getAttribute("surveyList");
  	
  	  ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
	%>
  	<div class="content">
  	  <div class="wrapper">
  	    <jsp:include page="templates/Menu.jsp" />
  	    
  	    <h2 class="view-title">
  	      <span class="title">Category List</span>
  	      <p class="add-category clearfix"><span>Add Category</span></p>
  	    </h2>
  	    
  	    <table class="table table-bordered">
  	      <thead>
  	        <tr>
  	          <th>Category Id</th> 
<!--   	          <th>Survey ID</th>  -->
  	          <th>Category Name</th>
  	          <th>Edit</th>
  	          <th>Delete</th> 
  	        </tr> 
  	      </thead>
  	      <tbody>
  	         <%
	  	       if(categoryList != null && !categoryList.isEmpty()) {
	  		      for(int i = 0; i < categoryList.size(); i++) {
		  	  %>
		  	  <tr>
		  	    <td class="category-id"><% out.println((categoryList).get(i).getCategoryId()); %></td>
<%-- 		  	    <td class="survey-id"><% out.println((categoryList).get(i).getSurveyId()); %></td>    --%>
	  	        <td class="category-name"><% out.println((categoryList).get(i).getCategoryName()); %></td>
	  	        <td class="edit"><span></span></td>
	  	        <td class="delete">
	  	          <form method="POST" action="category" class="form-horizontal">
	  	            <input type="hidden" value="<% out.println((categoryList).get(i).getCategoryId()); %>" name="delete-category">
	  	            <button type="submit"></button>
	  	          </form>
	  	        </td>
	  	      </tr>
		  	  <%
		  	      }
		  	    } 
	  	      %>
  	      </tbody>
  	    </table>
  	    <div class="add-category-popup">
  	      <span class="close-add-category-popup">x</span>
  	      <form method="POST" action="category" class="form-horizontal">
  	        <input type="hidden" name="survey-id" id="survey-id" value="0">
		    <div class="form-group">
		      <label for="category-name" class="col-sm-3 control-label">Category Name</label>
		      <div class="col-sm-8">
		        <input type="text" class="form-control" id="category-name" name="category-name" placeholder="Category Name">
		      </div>
		    </div>
		    <input type="hidden" name="category-id" id="category-id" value="">
		    <div class="submit-btn-wrapper">
		      <button type="submit" class="btn btn-default">Add</button>
		    </div>
  	      </form>
  	    </div>
  	    <div class="add-category-popup-overlay"></div>
  	  </div>
  	</div>
  	
  	<jsp:include page="templates/Footer.jsp" />
  </div>
  
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
</body>
</html>