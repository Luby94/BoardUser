<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<style>
   input:not(input[type=submit]) { width:100%; }
   input[type=submit] { width : 100px; }
   #goList  { width : 80px; }
   
   td { 
      padding:10px;
      width: 700px;
      text-align: center;
   }
   td:nth-of-type(1) {
      width : 200px;
   }
   
   td:not([colspan]):first-child {
      background: black;
      color : white;
      font-weight: bold;
   }
   
   input[readonly] {
   	  background: #D0D0D0;
   }

</style>
</head>
<body>
  <main>
	<h2>사용자 수정</h2>
	<form action="/Users/Write" method="POST">
	<table>
	 <tr>
	   <td>아이디</td>
	   <td><input type="text" name="userid" placeholder="${vo.userid}" /></td>
	 </tr>
	 <tr>
	   <td>비밀번호</td>
	   <td><input type="password" name="passwd" /></td>
	 </tr>
	 <tr>
	   <td>이름</td>
	   <td><input type="text" name="username" placeholder="${vo.username}" /></td>
	 </tr>
	 <tr>
	   <td>이메일</td>
	   <td><input type="text" name="email" placeholder="${vo.email}" /></td>
	 </tr>
	 <tr>
	   <td>포인트</td>
	   <td><input type="text" value="1000" readonly /></td>
	 </tr><!-- 포인트 가입일 : 날라가면 안되는 정보 , name 뺌 -->
	 <tr>
	   <td>가입일</td>
	   <td><input type="text" value="${ vo.indate }" readonly /></td>
	 </tr>
	 <tr>
	   <td colspan="2">
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/Users/UpdateForm?userid=${ vo.userid }">수정</a>
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/Users/List">회원목록</a>
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/">Home</a>
	   </td>
	 </tr>
	
	</table>	
	</form>   
	
  </main>
  
  <script>
  	const  goListEl  = document.getElementById('goList');
  	goListEl.addEventListener('click', function(e) {
  		location.href = '/Users/List';
  	})
  
  </script>
  
</body>
</html>





