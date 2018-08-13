<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<form id="formId" method="post">
		<input type="text" name="id"/>
		<input type="password" name="pw"/>
		<input type="submit"/>
		
	</form>
	<a href="/signup"> <button type="button">회원가입</button></a>
</body>
</html>
