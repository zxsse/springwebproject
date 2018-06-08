<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<label class="name" id="name"> 이름 :</label>
		<input type="text" name="id" id="textname">
		<label class="pw" id="pw"> 비밀번호 :</label>
		<input type="password" name="pw" id="textpw">
		<label class="email" id="email"> 이메일 :</label>
		<input type="text" name="email" id="textemail">
		<button type="submit" class="btn btn-primary">가입</button>
		<a href="/"><button type="button" class="btn btn-primary">취소</button></a>
	</form>
	
</body>
</html>