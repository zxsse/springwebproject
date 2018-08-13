
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
		.fileDrop{
			width : 100%;
			height : 200%;
			border : solid 1px blue; 
		}
	</style>
	<div class="fileDrop">
	</div>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"> 
	</script>
	<script type="text/javascript">
		$(".fileDrop").on("dragenter dragover",function(e){e.preventDefault();});
		$(".fileDrop").on("drop",function(e){e.preventDefault();});
		
	</script>
</body>
</html>