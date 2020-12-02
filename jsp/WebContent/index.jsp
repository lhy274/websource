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
	<!-- <h1>JSP 테스트</h1> -->
	<%-- <h1>JSP 테스트 : <%=session.getAttribute("userid") %></h1> --%>
	<h1>JSP 테스트 : <%=login.getUserid() %></h1>
	<div>
		<a href="product.jsp">상품 페이지</a>
	</div>
</body>
</html>