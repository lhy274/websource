<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String[] dogs = request.getParameterValues("dog");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신이 선택한 강아지 종류</h3>
	<ul>
	<%
		for(String s:dogs) { 
			out.print("<li>"+s+"</li>");
		}
	%>
	</ul>
</body>
</html>