<%@page import="com.google.gson.JsonArray"%>
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
  <div class="report-main-page">
  	<jsp:include page="templates/Header.jsp" />
  	
  	<div class="content">
  	  <div class="wrapper">
  	    <jsp:include page="templates/Menu.jsp" />
  	    
  	    <%
  	    
  	      ArrayList<SurveyBean> surveyList = (ArrayList<SurveyBean>)request.getAttribute("surveyList");
  	    
  	      ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
  	    
	      JsonArray reportList = (JsonArray)request.getAttribute("reportList");
  	      
  	    %>
  	    
  	    <script>
  	    localStorage.setItem('graphData', JSON.stringify(<% out.println(reportList); %>));
  	    var graphData = JSON.parse(localStorage.getItem('graphData'));
  	    </script>
  	    
  	    <h2 class="view-title">
  	      <span class="title">Reports</span>
  	    </h2>
  	    <div class="survey-dropdown-wrapper">
  	      <select class="form-control" id="survey-name" name="survey-name">
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
  	    </div>
  	    <div class="reports-main-wrapper">
<!--   	      <ul id="myTabs" class="nav nav-tabs" role="tablist">  -->
<!--   	        <li role="presentation" class="active"> -->
<!--   	          <a href="#overall" id="home-tab" role="tab" data-toggle="tab" aria-controls="overall" aria-expanded="true">Overall</a> -->
<!--   	        </li>  -->
<!--   	        <li role="presentation" class=""> -->
<!--   	          <a href="#individual" role="tab" id="profile-tab" data-toggle="tab" aria-controls="individual" aria-expanded="false">Individual</a> -->
<!--   	        </li>   -->
<!--   	      </ul>  -->
  	      
  	      <div id="myTabContent" class="tab-content"> 
  	        <div role="tabpanel" class="tab-pane fade active in" id="overall" aria-labelledby="overall-tab"> 
  	        	<ul class="overall-pie-legend">
                    
                </ul>
  	           <div id="canvas-holder">
                    <canvas id="chart-area" />
                </div>
  	        </div> 
  	        <div role="tabpanel" class="tab-pane fade" id="individual" aria-labelledby="individual-tab"> 
  	           individual
  	        </div> 
  	      </div> 
  	    </div>
  	  </div>
  	</div>
  	
  	<jsp:include page="templates/Footer.jsp" />
  </div>
  
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/Chart.js"></script>
  <script src="js/script.js"></script>
</body>
</html>