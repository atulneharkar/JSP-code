(function() { 
	
  // to keep the menu link selected
  var url = window.location.href;
  if(url.indexOf('/survey') > -1) {
    $('.survey-navigation .survey').addClass('selected');
  } else if(url.indexOf('/category') > -1) {
    $('.survey-navigation .category').addClass('selected');
  } else if(url.indexOf('/question') > -1) {
	$('.survey-navigation .question').addClass('selected');
  } else if(url.indexOf('/report') > -1) {
	$('.survey-navigation .report').addClass('selected');
  } else if(url.indexOf('/assign-survey') > -1) {
	$('.survey-navigation .assign-survey').addClass('selected');
  }
	
  // to display popup
  $(document).on('click', '.survey-main-page .add-survey span, .survey-main-page .add-survey-popup-overlay, .add-survey-popup .close-add-survey-popup' ,function() {
	$('.survey-main-page .submit-btn-wrapper button').text('Add');
	$('.survey-main-page #survey-id').val('');
	$('.survey-main-page #survey-name').val('');
	$('.survey-main-page #survey-start').val('');
	$('.survey-main-page #survey-end').val('');
	$('.survey-main-page .add-survey-popup').fadeToggle(); 
	$('.survey-main-page .add-survey-popup-overlay').fadeToggle(); 
  });
  
  $(document).on('click', '.category-main-page .add-category span, .category-main-page .add-category-popup-overlay, .add-category-popup .close-add-category-popup' ,function() {
    $('.category-main-page .add-category-popup').fadeToggle(); 
	$('.category-main-page .add-category-popup-overlay').fadeToggle(); 
  });
  
  $(document).on('click', '.question-main-page .add-question span, .question-main-page .add-question-popup-overlay, .add-question-popup .close-add-question-popup' ,function() {
    $('.question-main-page .add-question-popup').fadeToggle(); 
    $('.question-main-page .add-question-popup-overlay').fadeToggle(); 
  });
  
  $(document).on('click', '.assign-survey-main-page .assign-survey-btn span, .assign-survey-main-page .assign-survey-popup-overlay, .assign-survey-popup .close-assign-survey-popup' ,function() {
    $('.assign-survey-main-page .assign-survey-popup').fadeToggle(); 
	$('.assign-survey-main-page .assign-survey-popup-overlay').fadeToggle(); 
  });
  
  // to check if all fields are filled for add survey
  $('.survey-main-page .submit-btn-wrapper button').on('click', function(e) {
	  e.preventDefault();
	  var surveyName  = $('#survey-name').val();
	  var surveyStart = $('#survey-start').val();
	  var surveyEnd   = $('#survey-end').val();
	  
	  if(surveyStart) {
		  surveyStart = surveyStart.split('/');
		  surveyStart = surveyStart[2] + '/' + surveyStart[1] + '/' + surveyStart[0];
	  }
	  
	  if(surveyEnd) {
		  surveyEnd = surveyEnd.split('/');
		  surveyEnd = surveyEnd[2] + '/' + surveyEnd[1] + '/' + surveyEnd[0];
	  }
	  
	  if(surveyName && surveyStart && surveyEnd) {
		  if(surveyStart < surveyEnd) {
			  $(this).parents('form').submit(); 
		  } else {
			  alert("End date should be greater than Start date");
		  }
	  } else {
		  alert('All fields are mandatory.');
	  }
  });
  
  // show popup for editing survey
  $('.survey-main-page .edit span').on('click', function() {
	  $('body').scrollTop(0);
	  var _this = $(this);
	  
	  var surveyId = _this.parents('tr').children('.survey-id').text().trim(),
	      surveyName = _this.parents('tr').children('.survey-name').text().trim(),
	      surveyStart = _this.parents('tr').children('.survey-start').text().trim(),
	      surveyEnd = _this.parents('tr').children('.survey-end').text().trim();
	  
	  $('.survey-main-page #survey-id').val(surveyId);
	  $('.survey-main-page #survey-name').val(surveyName);
	  $('.survey-main-page #survey-start').val(surveyStart);
	  $('.survey-main-page #survey-end').val(surveyEnd);
	  
	  $('.survey-main-page .submit-btn-wrapper button').text('Update');
	  
	  $('.survey-main-page .add-survey-popup').fadeToggle(); 
	  $('.survey-main-page .add-survey-popup-overlay').fadeToggle();
  });
  
  //category page
  $('#survey-name-dropdown').on('change', function() {
	  $('#survey-id').val($(this).val());
  });
  
  //to check if all fields are filled for add category
  $('.category-main-page .submit-btn-wrapper button').on('click', function(e) {
	  e.preventDefault();
	  
	  var surveyId     = $('#survey-id').val(),
	      categoryName = $('#category-name').val(); 
	  
	  if(surveyId && categoryName) {
		  $(this).parents('form').submit();
	  } else {
		  alert('All fields are mandatory.');
	  }
  });
  
//show popup for editing category
  $('.category-main-page .edit span').on('click', function() {
	  $('body').scrollTop(0);
	  var _this = $(this);
	  
	  var surveyId     = parseInt(_this.parents('tr').children('.survey-id').text().trim(), 10),
	      categoryName = _this.parents('tr').children('.category-name').text().trim(),
	      categoryId   = parseInt(_this.parents('tr').children('.category-id').text().trim(), 10);
	  
	  $('.category-main-page #survey-id').val(surveyId);
	  $('.category-main-page #category-name').val(categoryName);
	  $('.category-main-page #category-id').val(categoryId);
	  
	  $('.category-main-page #survey-name-dropdown option').each(function() {
		  if($(this).val() == surveyId) {
			  $(this).attr('selected', 'selected');
		  }
	  });
	  
	  $('.category-main-page .submit-btn-wrapper button').text('Update');
	  
	  $('.category-main-page .add-category-popup').fadeToggle();
	  $('.category-main-page .add-category-popup-overlay').fadeToggle();
  });
  
  //question page
  $('.question-main-page #survey-name').on('change', function() {
	  $('#survey-id').val($(this).val());
  });
  
  $('.question-main-page #category-name').on('change', function() {
	  $('#category-id').val($(this).val());
  });
  
  $('.question-main-page #question-type').on('change', function() {
	  $('#question-type-value').val($(this).val());
  });
  
  $('.question-main-page #question-importance').on('change', function() {
	  $('#question-importance-value').val($(this).val());
  });
  
  //to check if all fields are filled for add question
  $('.question-main-page .submit-btn-wrapper button').on('click', function(e) {
	  e.preventDefault();
	  
	  var surveyId     = $('#survey-id').val(),
	      categoryName = $('#category-name').val(),
	      questionType = $('#question-type-value').val(),
	      question     = $('#question').val(); 
	      importance   = $('#question-importance-value').val(); 
	  
	  if(surveyId && categoryName && questionType && question && importance) {
		  $(this).parents('form').submit();
	  } else {
		  alert('All fields are mandatory.');
	  }
  });
  
  //show popup for editing questions
  $('.question-main-page .edit span').on('click', function() {
	  $('body').scrollTop(0);
	  var _this = $(this);
	  
	  var surveyId     = parseInt(_this.parents('tr').children('.survey-id').text().trim(), 10),
	      categoryId   = parseInt(_this.parents('tr').children('.category-id').text().trim(), 10),
	      questionId   = _this.parents('tr').children('.question-id').text().trim(),
	      questionType = _this.parents('tr').children('.question-type').text().trim(),
	      importance   = _this.parents('tr').children('.question-importance').text().trim(),
	      question     = _this.parents('tr').children('.question').text().trim();
	  
	  $('.question-main-page #survey-id').val(surveyId);
	  $('.question-main-page #category-id').val(categoryId);
	  $('.question-main-page #question-id').val(questionId);
	  $('.question-main-page #question-type-value').val(questionType);
	  $('.question-main-page #question').val(question);
	  $('.question-main-page #question-importance-value').val(importance);
	  
	  $('.question-main-page #survey-name option').each(function() {
		  if($(this).val() == surveyId) {
			  $(this).attr('selected', 'selected');
		  }
	  });
	  
	  $('.question-main-page #category-name option').each(function() {
		  if($(this).val() == categoryId) {
			  $(this).attr('selected', 'selected');
		  }
	  });
	  
	  $('.question-main-page #question-type option').each(function() {
		  if($(this).val() == questionType) {
			  $(this).attr('selected', 'selected');
		  }
	  });
	  
	  $('.question-main-page #question-importance option').each(function() {
		  if($(this).val() == importance) {
			  $(this).attr('selected', 'selected');
		  }
	  });
	  
	  $('.question-main-page .submit-btn-wrapper button').text('Update');
	  
	  $('.question-main-page .add-question-popup').fadeToggle();
	  $('.question-main-page .add-question-popup-overlay').fadeToggle();
  });
  
  //toggle tabs in reports page
  $('#myTabs a').click(function (e) {
	e.preventDefault()
	$(this).tab('show')
  });
  
  //graphs
  
  var surveyArray = [];
  var graphColors = ['#FF0000', '#F1F10F', '#0EA9EF', '#15EC15', '#FFA500', '#EC40D1', '#4047EC'];
  
  $('.report-main-page .survey-dropdown-wrapper select option').each(function() {
	  surveyArray.push(($(this).val()).trim());
  });
  
  $('.report-value-page + #category-name option').each(function() {
	  categoryIdArray.push(($(this).val()).trim());
	  categoryNameArray.push(($(this).text()).trim());
  });
  
  generateGraph();
  
  $('.report-main-page #survey-name').on('change', function() {
	  localStorage.setItem('graph-survey-id', $(this).val());
	  window.location.reload();
  });
  
  var selectedSurveyId = localStorage.getItem('graph-survey-id');
  if(selectedSurveyId != 'undefined') {
	  $('.report-main-page #survey-name option').each(function() {
		  if($(this).val() == selectedSurveyId) {
			  $(this).prop('selected', true);
		  }
	  });
  }
  
  function generateGraph() {
	  //for(var surveyCount = 0; surveyCount < surveyArray.length; surveyCount++) {
	      var survey = surveyArray[0];
	      var graphSurveyId = localStorage.getItem('graph-survey-id');
	      if(graphSurveyId != 'undefined' && graphSurveyId != null) {
	    	  survey = graphSurveyId;
	      } 
		  var categoryList = [];
		  var count = 1;
		  $.each(graphData, function(i, el) {
			  if(parseInt(el.surveyId, 10) == survey) { 
				  var categoryStatus = 'false';
				  $.each(categoryList, function(k, elem) {
					    if(elem.categoryId == el.categoryId) {
					    	categoryStatus = 'true';
					    }
				  });
				  if(categoryStatus == 'false') {
			  		if(el.answer == '1' || el.answer == '2' || el.answer == '3' || el.answer == '4' || el.answer == '5') {
	                  var categoryObj = {
	                    'categoryId': el.categoryId,
	                    'categoryCount': 1,
	                    'categoryValue': el.answer,
	                    'categoryName': el.categoryName
	                  }
	                  
	                  categoryList.push(categoryObj);
		  			}
	              } else {
	            	  $.each(categoryList, function(j, element) {
	            		  if(element.categoryId == el.categoryId) {
			  				if(el.answer == '1' || el.answer == '2' || el.answer == '3' || el.answer == '4' || el.answer == '5') {
	            			  element.categoryCount = parseInt(element.categoryCount, 10) + 1;
	            			  element.categoryValue = parseInt(element.categoryValue, 10) + parseInt(el.answer, 10);
		  					}
	            		  }
	            	  });
	              }
			  }
		  });
		  
		  var reportData = [];
		  var legendText = '';
		  $.each(categoryList, function(l, e) {
			  var obj = {
	                  'value': Math.round(e.categoryValue/e.categoryCount),
	                  'label': e.categoryName,
	                  'color': graphColors[l]
	                }
			  reportData.push(obj);
			  legendText = "<li><span class='pie-legend-icon'></span><p class='pie-legend-text'>" + e.categoryName + "<span class='bold'>(" + obj.value + ")</span></p></li>";
			  $('.report-main-page .overall-pie-legend').append(legendText);
		  });
		  
		  var ctx = new Chart(document.getElementById("chart-area").getContext("2d")).PolarArea(reportData);
		  
		  
	  //}
  }
  
})();