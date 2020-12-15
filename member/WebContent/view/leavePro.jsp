<%@page import="member.MemberVO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//leaveForm.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	String password = request.getParameter("current_password");
	//MemberVO vo = (MemberVO)session.getAttribute("login");
	
	//DB작업
	MemberDAO dao = new MemberDAO();
	//int result = dao.leaveMember(vo.getUserid(), current_password);
	int result = dao.leaveMember(userid, password);
	
	//비민번호가 맞아서 탈퇴가 되는 경우-세션해제, index 페이지로 이동
	if(result>0) {
		session.invalidate();
		response.sendRedirect("../index.jsp");
	}else {
		response.sendRedirect("leaveForm.jsp");
		
	}
	
	//비민번호가 틀리는 경우 - leaveForm.jsp로 이동 


%>