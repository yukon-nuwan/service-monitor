<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--- Example CSS -->
  <style>
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.menu a.item')
        .on('click', function() {
          $(this)
            .addClass('active')
            .siblings()
            .removeClass('active')
          ;
        })
      ;
    })
  ;
  </script>	
	
	
<footer>
<div class="navbar navbar-dark bg-primary fixed-bottom font-weight-bold">
  <div class="right menu footerText">
    <div class="header item">Designed &amp; Developed by Yukon software</div>
  </div>
</div>
</footer>	

	
	
	
