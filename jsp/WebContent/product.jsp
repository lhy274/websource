<%@page import="exam.loginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	loginDTO login = (loginDTO)session.getAttribute("login");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <h2><%=session.getAttribute("userid") %>님 반갑습니다</h2> --%>
	<h2><%=login.getUserid() %>님 반갑습니다</h2>
	<div>
		<input type="button" value="로그아웃" onclick="location.href='logout.jsp'"/>
	</div>
</body>
</html>