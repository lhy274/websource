<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스크립틀릿 : 자바코드 사용, 메소드 안에 선언하는 방식과 동일하게 사용
	int total = 0;
	for(int i=1;i<101;i++) {
		total+=i;
	}
	//out.print("1~100 합 : "+total);
%>
<%=total %>