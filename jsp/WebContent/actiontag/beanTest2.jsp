<%@page import="exam.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- User user = new User(); --%>
<jsp:useBean id="user" class="exam.User"/>
<h1>
<%-- user.getName() --%>
	<jsp:setProperty property="name" name="user" value="홍길동"/>
	<jsp:getProperty property="name" name="user"/>
</h1>
</body>
</html>