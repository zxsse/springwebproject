<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">









 <title>Insert title here</title>
 <!-- <form id="frm">
        <input type="text" name="id" id="id" placeholder="아이디"/><br/>
        <input type="password" name="pwd" id="pwd" placeholder="비밀번호"/><br/>
        <input type="password" name="repassword" id="repassword" placeholder="비밀번호 확인"/><br/>
        <input type="text" name="age" id="age" placeholder="나이"/><br/>
        <input type="text" name="name" id="name" placeholder="이름"/><br/>
        <input type="text" name="email" id="email" placeholder="이메일"/><br/>
        <input type="text" name="homepage" id="homepage" placeholder="홈페이지"/><br/>
        <input type="submit" value="회원가입" />
    </form>
    <script src="//code.jquery.com/jquery-3.3.1.js"></script>
  
    <script src="/resources/additional-methods.js"></script>
    <script src="/resources/jquery.validate.js"></script> -->
</head>
<body>
	<!-- <form method="post">
		<label class="name" id="name"> 이름 :</label>
		<input type="text" name="id" id="textname">
		
		<label class="nikname" id="nikname"> 아이디 :</label>
		<input type="text" name="nikname" id="nikname" onchange="validationNikname(this)">
		<div style="color:red; display:none;" id="inforNikname">
			아이디는 영문와숫자 혼합 5자이상 10이하
		</div>
		
		<label class="pw" id="pw"> 비밀번호 :</label>
		<input type="password" name="pw" id="textpw" onchange="validationPw(this)">
		
		<div style="color:red; display:none;" id="inforPw" >
			비밀번호는 영문자와 숫자가 1개이상 포함되어야하며 8~20자 입니다
		</div>
		
		<label class="email" id="email"> 이메일 :</label>
		<input type="text" name="email" id="textemail">
		<button type="submit" class="btn btn-primary">가입</button>
		<a href="/"><button type="button" class="btn btn-primary">취소</button></a>
	</form>
	<script type="text/javascript">
		function validationNikname(nikname)
		{
			var inforNikname = document.getElementById('inforNikname');
			var niknameText = nikname.value;
			var niknameRegex = /^\w{5,10}$/;
			inforNikname.display = 'none';
			if(niknameText != null && niknameRegex.test(niknameText))
				{
				
					return true;
				}
			else
				{
					inforNikname.style.display='block';
					return false;
				}
		}
		
		function validationPw(pw)
		{0
			var idforPw = document.getElementById('inforPw');
			var pwText = pw.value;,
			var pwRegex = /^(?=\w{$8,20})(\w*((\d[a-zA-Z])|([a-zA-Z\d))
			inforPw.display = 'none';
			if(idText != null && idRegex.test(idText))
				{
				
					return true;
				}
			else
				{
					inforId.display='block';
					return false;
				}
		}
		
		var formId = document.getElementById('formId');
		formId.onsubmit = function()
		{
			var id = document.getElementById('nikname');
			var pw = document.getElementById('pw');
			var isOk=true;//제출할건지 말건지결정
			if(!validationNikname(nikname))
				isOk = false;
			if(!validationPw(pw))
				isOk = false;
			if(!isOk)
				return false;
			return true;
		}
	</script> 
	 
	
<!-- 	
	<script>
	$(function(){
    $("form").validate({
        rules: {
            id: {
                required : true,
                minlength : 4
            },
            pwd: {
                required : true,
                minlength : 8,
                regex: /^(?=\w{8,20}$)\w*(\d[A-z]|[A-z]\d)\w*$/
            },
            repassword: {
                required : true,
                minlength : 8,
                equalTo : password
            },
            name: {
                required : true,
                minlength : 2
            },
            age: {
                digits : true
            },
            email: {
                required : true,
                minlength : 2,
                email : true
            },
            homepage: {
                url : true
            }
        },
        //규칙체크 실패시 출력될 메시지
        messages : {
            id: {
                required : "필수로입력하세요",
                minlength : "최소 {0}글자이상이어야 합니다"
            },
            password: {
                required : "필수로입력하세요",
                minlength : "최소 {0}글자이상이어야 합니다",
                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
            },
            repassword: {
                required : "필수로입력하세요",
                minlength : "최소 {0}글자이상이어야 합니다",
                equalTo : "비밀번호가 일치하지 않습니다."
            },
            name: {
                required : "필수로입력하세요",
                minlength : "최소 {0}글자이상이어야 합니다"
            },
            age: {
                digits : "숫자만입력하세요"
            },
            email: {
                required : "필수로입력하세요",
                minlength : "최소 {0}글자이상이어야 합니다",
                email : "메일규칙에 어긋납니다"
            },
            homepage: {
                url : "정상적인 URL이 아닙니다"
            }
        }
    });
})
$.validator.addMethod(
    "regex",
    function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
); -->
	</script>
</body>
</html>