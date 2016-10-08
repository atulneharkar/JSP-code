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
  <div class="question-main-page">
  	<jsp:include page="templates/Header.jsp" />
  	
  	<%
	  ArrayList<SurveyBean> surveyList = (ArrayList<SurveyBean>)request.getAttribute("surveyList");
  	
  	  ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
  	  
  	  ArrayList<QuestionBean> questionList = (ArrayList<QuestionBean>)request.getAttribute("questionList");
	%>
  	
  	<div class="content">
  	  <div class="wrapper">
  	    <jsp:include page="templates/Menu.jsp" />
  	    
  	    <h2 class="view-title">
  	      <span class="title">Question List</span>
  	      <p class="add-question clearfix"><span>Add Question</span></p>
  	    </h2>
  	    
  	    <table class="table table-bordered">
  	      <thead>
  	        <tr>
  	          <th style="width: 85px;">Question Id</th>
  	          <th>Survey Name</th> 
  	          <th>Category Name</th> 
  	          <th style="width: 100px;">Question Type</th>
  	          <th>Importance</th>
  	          <th>Question</th>
  	          <th>Edit</th> 
  	          <th>Delete</th> 
  	        </tr> 
  	      </thead>
  	      <tbody>
  	        <tr>
  	          <%
	  	       if(questionList != null && !questionList.isEmpty()) {
	  		      for(int i = 0; i < questionList.size(); i++) {
		  	  %>
		  	    <td class="question-id"><% out.println((questionList).get(i).getQuestionId()); %></td>
		  	    <td class="survey-id"><% out.println((questionList).get(i).getSurveyName()); %></td>
	  	        <td class="category-id"><% out.println((questionList).get(i).getCategoryName()); %></td>	  	        
	  	        <td class="question-type"><% out.println((questionList).get(i).getQuestionType()); %></td>
	  	        <td class="question-importance"><% out.println((questionList).get(i).getImportance()); %></td>
	  	        <td class="question"><% out.println((questionList).get(i).getQuestion()); %></td>
	  	        <td class="edit"><span></span></td>
	  	        <td class="delete">
	  	          <form method="POST" action="question" class="form-horizontal">
	  	            <input type="hidden" value="<% out.println((questionList).get(i).getQuestionId()); %>" name="delete-question">
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
  	    <div class="add-question-popup">
  	      <span class="close-add-question-popup">x</span>
  	      <form method="POST" action="question" class="form-horizontal">
  	        <div class="form-group">
		      <label for="survey-name" class="col-sm-3 control-label">Survey Name</label>
		      <div class="col-sm-8">
		        <select class="form-control" id="survey-name" name="survey-name">
		          <option value="">Please Select</option>
		      	  <%
			      	if(surveyList != null && !surveyList.isEmpty()) {
			  	      for(int i = 0; i < surveyList.size(); i++) {
			  	  %>
			  	        <option value="<% out.println((surveyList).get(i).getSurveyId()); %>"><% out.println((surveyList).get(i).getSurveyName()); %></option>
			  	  <%
			  	      }
			  	    }
		      	  %>
				</select>
				<input type="hidden" name="survey-id" id="survey-id" value="">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="category-name" class="col-sm-3 control-label">Category Name</label>
		      <div class="col-sm-8">
		        <select class="form-control" id="category-name" name="category-name">
		          <option value="">Please Select</option>
		      	  <%
			      	if(categoryList != null && !categoryList.isEmpty()) {
			  	      for(int i = 0; i < categoryList.size(); i++) {
			  	  %>
			  	        <option value="<% out.println((categoryList).get(i).getCategoryId()); %>"><% out.println((categoryList).get(i).getCategoryName()); %></option>
			  	  <%
			  	      }
			  	    }
		      	  %>
				</select>
				<input type="hidden" name="category-id" id="category-id" value="">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="question-type" class="col-sm-3 control-label">Type</label>
		      <div class="col-sm-8">
		        <select class="form-control" id="question-type">
		          <option value="">Please Select</option>
				  <option value="Radio">Radio</option>
				  <option value="Free Text">Free Text</option>
				</select>
				<input type="hidden" name="question-type" id="question-type-value" value="">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="question-importance" class="col-sm-3 control-label">Importance</label>
		      <div class="col-sm-8">
		        <select class="form-control" id="question-importance">
		          <option value="">Please Select</option>
				  <option value="Low">Low</option>
				  <option value="Medium">Medium</option>
				  <option value="High">High</option>
				</select>
				<input type="hidden" name="question-importance" id="question-importance-value" value="">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="question" class="col-sm-3 control-label">Question</label>
		      <div class="col-sm-8">
		        <textarea class="form-control" id="question" name="question" placeholder="Question"></textarea>
		      </div>
		    </div>
		    <input type="hidden" name="question-id" id="question-id" value="">
		    <div class="submit-btn-wrapper">
		      <button type="submit" class="btn btn-default">Add</button>
		    </div>
  	      </form>
  	    </div>
  	    <div class="add-question-popup-overlay"></div>
  	  </div>
  	</div>
  	
  	<jsp:include page="templates/Footer.jsp" />
  </div>
  
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
</body>
</html>