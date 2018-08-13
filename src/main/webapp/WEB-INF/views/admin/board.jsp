<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>동준 리스트 연습</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container " >
			<div class="col-12">
	
	
	  			
	  		<div class ="float-right">
	  			<a href="/logout">로그아웃</a>
	  				<span style="coler:white"> |	</span>
 	  			<a href="/hostmodify">회원정보 수정</a>
 	  			
 	  			<a href="/board/list">게시판페이지</a>
 	  			
 	  		</div>
 	  	</div>
 	
 		</div>
 	</nav>
<div class="container">
  <h2>게시판 관리 </h2> 
  <c:if test="${superadmin}">
  <a href="/admin/user">유저 관리</a>
  </c:if>
  <p>This is test-jsp</p>            
  <table class="table table-hover">
    <thead>
   		<tr>
		       	<th class="a">number</th>
		       	<th class="b">title</th>
		       	<th class="c">author</th>
		       	<th class="d">del</th>
		       	<th class="e">realdel</th>
	  	</tr>
    	<tbody>
    	<c:forEach items="${list}" var="list">
		    	<tr>
	    			<td>${list.number}</td>
		        	<td><a href="/board/detail?number=${list.number}">${list.title}</a></td> 
		        	<td>${list.author}</td>
		        	<td>
		        	
		        		<c:if test="${list.disable.compareTo('TRUE')==0}">
		        		 	<a href="/admin/board/disable?number=${list.number}&disable=FALSE&page=${pageMaker.criteria.page}"><button>복구</button></a>
		        		</c:if>
		        		<c:if test="${list.disable.compareTo('FALSE')==0}">
		   	     		 	<a href="/admin/board/disable?number=${list.number}&disable=TRUE&page=${pageMaker.criteria.page}"><button>삭제</button></a>
		        		</c:if>
		        	
		        	</td>
		        	<td>
		        		 <c:if test="${list.disable.compareTo('TRUE')==0}"> 
		   	     		 
		   	     		 	<button onclick="deleteBoard(${list.number},${pageMaker.criteria.page})">진짜삭제</button>
		   	     		 </c:if> 
		        	</td>	
		        	
		        </tr>
	   	</c:forEach>	
    	</tbody>
  		</table>
  		
  		<ul class="pagination" style="justify-content: center;">
  			<c:if test="${pageMaker.prev}">
	  			<li class="page-item"><a class="page-link" href="/admin/board?page=${pageMaker.startPage-1}">Prev</a></li>
	  		</c:if>
	  	<c:forEach var="page" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
	  		<li class="page-item"><a class="page-link" href="/admin/board?page=${page}">${page}</a></li>
	  	</c:forEach>
	  	
	  	<c:if test="${pageMaker.next}">
	  			<li class="page-item"><a class="page-link" href="/admin/board?page=${pageMaker.endPage+1}">Next</a></li>
	  		</c:if>
		</ul>
  		
  		
	</div>
	<script type="text/javascript">
	 function deleteBoard(number, page)
	 {
		 var conf = confirm("삭제하시겠습니까?");
		 if(conf)
			 {
			 	var link="/admin/board/delete?number="+number+"&page="+page;
			 	location.href=link;
			 }
	 }
	</script>
	
</body>
</html>