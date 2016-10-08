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
  <div class="survey-main-page">
  	<jsp:include page="templates/Header.jsp" />
  	
  	<div class="content">
  	  <div class="wrapper">
  	    <jsp:include page="templates/Menu.jsp" />
  	    <h2 class="view-title">
  	      <span class="title">Survey List</span>
  	      <p class="add-survey clearfix"><span>Add Survey</span></p>
  	    </h2>
		<%
		  ArrayList<SurveyBean> list = (ArrayList<SurveyBean>)request.getAttribute("list");
		%>
  	    <table class="table table-bordered">
  	      <thead>
  	        <tr>
  	          <th>Survey ID</th> 
  	          <th>Survey Name</th> 
  	          <th>Start Date</th> 
  	          <th>End Date</th>
  	          <th>Edit</th> 
  	          <th>Delete</th> 
  	        </tr> 
  	      </thead>
  	      <tbody>
	  	      <%
		  	    if(list != null && !list.isEmpty()) {
		  	      for(int i = 0; i < list.size(); i++) {
		  	  %>
		  	  <tr>
		  	    <td class="survey-id"><% out.println((list).get(i).getSurveyId()); %></td>
		  	    <td class="survey-name"><% out.println((list).get(i).getSurveyName()); %></td>
	  	        <td class="survey-start"><% out.println((list).get(i).getSurveyStart()); %></td>
	  	        <td class="survey-end"><% out.println((list).get(i).getSurveyEnd()); %></td>
	  	        <td class="edit"><span></span></td>
	  	        <td class="delete">
	  	          <form method="POST" action="survey" class="form-horizontal">
	  	            <input type="hidden" value="<% out.println((list).get(i).getSurveyId()); %>" name="delete-survey">
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
  	    <div class="add-survey-popup">
  	      <span class="close-add-survey-popup">x</span>
  	      <form method="POST" action="survey" class="form-horizontal">
  	        <div class="form-group">
		      <label for="survey-name" class="col-sm-3 control-label">Survey Name</label>
		      <div class="col-sm-8">
		        <input type="text" class="form-control" id="survey-name" name="survey-name" placeholder="Survey Name">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="survey-start" class="col-sm-3 control-label">Survey Start</label>
		      <div class="col-sm-8">
		        <input type="text" class="form-control" id="survey-start" name="survey-start" placeholder="Survey Start">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="survey-end" class="col-sm-3 control-label">Survey End</label>
		      <div class="col-sm-8">
		        <input type="text" class="form-control" id="survey-end" name="survey-end" placeholder="Survey End">
		      </div>
		    </div>
		    <input type="hidden" class="form-control" id="survey-id" name="survey-id" value="">
		    <div class="submit-btn-wrapper">
		      <button type="submit" class="btn btn-default">Add</button>
		    </div>
  	      </form>
  	    </div>
  	    <div class="add-survey-popup-overlay"></div>
  	  </div>
  	</div>
  	
  	<jsp:include page="templates/Footer.jsp" />
  </div>
  
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap-datepicker.min.js"></script>
  <script src="js/script.js"></script>
  <script>
	  $('.add-survey-popup #survey-start, .add-survey-popup #survey-end').datepicker({
		  format: 'dd/mm/yyyy'
	  });
  </script>
</body>
</html>