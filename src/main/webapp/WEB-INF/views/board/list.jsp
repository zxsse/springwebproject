<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>list</h2>
  <p>This is test-jsp</p>            
  <table class="table table-hover">
    <thead>
   		<tr>
		       	<th class="a">number</th>
		       	<th class="b">title</th>
		       	<th class="c">author</th>
	  	</tr>
    <tbody>
    	<c:forEach items="${board}" var="list" varStatus="status">
		    	<tr>
	    			<td>${list.number}</td>
		        	<td><a href="/board/detail?number=${list.number}">${list.title}</a></td> 
		        	<td>${list.author}</td>
		        </tr>
	   </c:forEach>	
    </tbody>
  </table>
</div>

</body>
</html>