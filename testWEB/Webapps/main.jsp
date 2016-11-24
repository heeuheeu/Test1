<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align='center'>
		<font color='blue'> getpost 방식으로 로그인 하기 </font>
	</div>
	<hr/>
	<div align='right'>
		<form action="Login.do" method="post">
			아이디 <input 		type="text" 		name="id">
			패스워드 <input 	type="password" 	name="pwd">
			<input type="submit" value="LOGIN">
		</form>
	</div>
</body>
</html>