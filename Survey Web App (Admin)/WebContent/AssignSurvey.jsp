<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
  <div class="assign-survey-main-page">
  	<jsp:include page="templates/Header.jsp" />
  	
  	<div class="content">
  	  <div class="wrapper">
  	    <jsp:include page="templates/Menu.jsp" />
  	    
  	    <h2 class="view-title">
<!--   	      <span class="title">Assign Survey</span> -->
				<span class="title">Under Construction</span>
<!--   	      <p class="assign-survey-btn clearfix"><span>Assign Survey</span></p> -->
  	    </h2>
<!--   	    <table class="table table-bordered"> -->
<!--   	      <thead> -->
<!--   	        <tr> -->
<!--   	          <th>User Id</th>  -->
<!--   	          <th>User Name</th> -->
<!--   	          <th>Survey ID</th>  -->
<!--   	          <th>Survey Name</th>  -->
<!--   	          <th>Edit</th>  -->
<!--   	          <th>Delete</th>  -->
<!--   	        </tr>  -->
<!--   	      </thead> -->
<!--   	      <tbody> -->
<!--   	        <tr> -->
<!--   	          <td></td> -->
<!--   	          <td></td> -->
<!--   	          <td></td> -->
<!--   	          <td></td> -->
<!--   	          <td class="edit"></td> -->
<!--   	          <td class="delete"></td> -->
<!--   	        </tr> <tr> -->
<!--   	      </tbody> -->
<!--   	    </table> -->
  	    <div class="assign-survey-popup">
  	      <span class="close-assign-survey-popup">x</span>
  	      <form method="POST" action="assign-survey" class="form-horizontal">
  	        <div class="form-group">
		      <label for="user-name" class="col-sm-3 control-label">User Name</label>
		      <div class="col-sm-8">
		      	<select class="form-control" id="user-name" name="user-name" multiple>
		      	  <option value="">Please Select</option>
				  <option value="1">User 1</option>
				  <option value="2">User 2</option>
				  <option value="3">User 3</option>
				  <option value="4">User 4</option>
				  <option value="5">User 5</option>
				</select>
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="survey-name" class="col-sm-3 control-label">Survey Name</label>
		      <div class="col-sm-8">
		      	<select class="form-control" id="survey-name" name="survey-name" multiple>
		      	  <option value="">Please Select</option>
				  <option value="1">Survey 1</option>
				  <option value="2">Survey 2</option>
				  <option value="3">Survey 3</option>
				  <option value="4">Survey 4</option>
				  <option value="5">Survey 5</option>
				</select>
		      </div>
		    </div>
		    <div class="submit-btn-wrapper">
		      <button type="submit" class="btn btn-default">Add</button>
		    </div>
  	      </form>
  	    </div>
  	    <div class="assign-survey-popup-overlay"></div>
  	  </div>
  	</div>
  	
  	<jsp:include page="templates/Footer.jsp" />
  </div>
  
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
</body>
</html>