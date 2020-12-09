<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//쿠키 가져오기
	Cookie[] cookies=request.getCookies();

%>
<%!
	
	private String getCookieValue(Cookie[] cookies, String name) { // 어거 get/set메소드구나 지금 알았어. 리턴이 있네
		if(cookies == null) {
			return null;
		}
		for(Cookie c:cookies) { //쿠키 타입으로 cookies 것들을 내보낸다.
			if(c.getName().equals(name)){ //쿠키의 이름을 가져와서 비교
				return c.getValue();
			}
		}
		return null;
}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>이름 : <%=getCookieValue(cookies,"name") %></p>
<p>성별 : <%=getCookieValue(cookies,"gender") %></p>
<p>나이 : <%=getCookieValue(cookies,"age") %></p>
</body>
</html>