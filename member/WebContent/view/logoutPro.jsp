<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//세션해제
	session.invalidate();
	//session.removeAttribute(arg0);

	//loginForm.jsp 이동
	response.sendRedirect("loginForm.jsp");

%>