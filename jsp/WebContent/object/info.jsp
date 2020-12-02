<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String password2 = request.getParameter("password2");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보</title>
</head>
<body>
	<h2>개인 정보 확인</h2>
	<h4>아이디 : <%=id%></h4>
	<h4>비밀번호 : <%=password%></h4>
	<h4>비밀번호 확인 : <%=password2%></h4>
	<h4>이름 : <%=name%></h4>
	<h4>성별 : <%=gender%></h4>
	<h4>이메일 : <%=email%></h4>
</body>
</html>