<%@page import="book1.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int code = Integer.parseInt(request.getParameter("code"));

	BookDAO dao = new BookDAO();
	int result = dao.bookDelect(code);

	if(result==0) {
		response.sendRedirect("../index.jsp?tab=delete");
	}else{ //성공시 select 로 이동
		response.sendRedirect("selectPro.jsp");
	}
%>