<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//세션에 담아놓은 장바구니 리스트를 가져오기 

	//세션에 담아놓은 장바구니 리스트를 가져오기
	//String product = (String)session.getAttribute("product");
	ArrayList<String> proList = (ArrayList<String>)session.getAttribute("proList");
	
	//상품이 연속적으로 담길 수 있게
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>장바구니 목록</h3>
<%-- 장바구니 리스트 보여주기 --%>
<%-- <%=product 이제 이렇게는 못 보여줌%> --%>
<%
	out.print("<ul>");
	for(String product : proList) {
		out.print("<li>"+product+"</li>");
	}
	out.print("</ul>");
%>
<div>
	<a href="cart.html">상품선택 페이지</a>
	<a href="cartRemove.jsp">장바구니 비우기</a>
</div>
</body>
</html>