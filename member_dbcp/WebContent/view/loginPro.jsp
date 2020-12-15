<%@page import="member.MemberVO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//loginForm.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	String password = request.getParameter("current_password");
	
	
	//DB작업하기
	//loginForm.jsp 로 이동
	MemberDAO dao = new MemberDAO();
	MemberVO member = dao.isLogin(userid, password);
	
	if(member!=null) {//로그인 작업 => 현재 정보를 session에 담기 // key 이름은 마음대로 쓰는거야.
		session.setAttribute("login", member);
	}else {
		out.print("<script>alert('아이디와 비밀번호를 확인해 주세요');</script>");
	}
	//response.sendRedirect("loginForm.jsp");
	//보내는 똑같은 작업이 script에도 있어.
	out.print("<script>");
	out.print("location.href='loginForm.jsp';");
	out.print("</script>");
	
%>