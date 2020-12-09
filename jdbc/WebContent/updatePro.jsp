<%@page import="jdbc.UserDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//update.jsp에서 넘긴값 가져와서(no,addr,mobile) 수정 후
	// 성공하면 index.jsp 이동
	//update 테이블명 set 수정할컬럼 값1, // set addr=수정값, mobile=수정값
	//where no=12
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	String addr = request.getParameter("addr");
	String mobile = request.getParameter("mobile");
	
	
	UserDAO dao = new UserDAO();
	int result = dao.updateUser(addr, mobile, no);
	
	if(result>0) {
		response.sendRedirect("index.jsp");
	}
	
	
	/* Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		// 2. 데이터베이스 연결을 위한 문자열 생성
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "javaDB";
		String password="12345";
		//
		con = DriverManager.getConnection(url, user, password);
		if(con!=null) {
			String sql = "update userTBL set addr=?, mobile=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, addr);
			pstmt.setString(2, mobile);
			pstmt.setInt(3, no);
			int result  = pstmt.executeUpdate();
			if(result>0) {
				response.sendRedirect("index.jsp");
			}
		}
	
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try{
		con.close();
		pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	} */
	

%>