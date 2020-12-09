<%@page import="jdbc.UserVO"%>
<%@page import="jdbc.UserDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//index.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String no=request.getParameter("no");
	//String userName=request.getParameter("username");
	//out.print(no);
	
	UserDAO dao = new UserDAO();
	UserVO vo = dao.getUser(no);
	
	/* //DB 연결 후 사용자의 no에 해당하는 정보 가져오기
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		//드라이버 로드
		Class.forName("oracle.jdbc.OracleDriver");
		//연결문자열 생성
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "javaDB";
		String password = "12345";
		//커넥션
		con = DriverManager.getConnection(url, user, password);
		if(con!=null) {
			String sql = "select * from userTBL where no=?";
			//String sql = "select no, username,addr,mobile from userTBL where no=?"; // 이렇게 순서로 써주면 써주는 순서대로 나옴
			//sql 구문 전송
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(no));
			//sql 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no1 = rs.getInt("no");
				String userName = rs.getString("username"); */
				//int no1 = rs.getInt(1); // 필드명을 안쓰면 어려움. no가 몇번째였지 하고 일일이 세야함.
				//String userName = rs.getString(2);
			
				
				/* }//if //아래쪽으로 찢어냈음
		}//if con!=null
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try{
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	} */
%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<style>
	.container{
		margin-top: 30px;
	}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">JDBC</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">User 조회 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="add.jsp">User 추가</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="update.jsp">User 수정</a>
      </li>z
      <li class="nav-item">
        <a class="nav-link" href="delete.jsp">User 삭제</a>
      </li>
    </ul>
  </div>
</nav>nav 종료 -->
<%@include file="header.jsp" %>
<div class="container">
<form action="" method="post">
	 <div class="form-group">
    <label for="username">번호</label>
    <input type="text" class="form-control" id="no" name="no" autofocus readonly value="<%=vo.getNo()%>">
  </div>
  <div class="form-group">
    <label for="username">이름</label>
    <input type="text" class="form-control" id="username" name="username" readonly value="<%=vo.getUsername()%>">
  </div>
  <div class="form-group">
    <button type="button" class="btn btn-primary">수정</button>
	<button type="button" class="btn btn-danger">삭제</button>
  </div>
</form>
</div>
<%
			/* }//if
		}//if con!=null
	}catch(Exception e) {
	e.printStackTrace();
	}finally {
	try{
		rs.close();
		pstmt.close();
		con.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
} */

%>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
$(function() {
	
	let form =$("form");
	//수정버튼을 클릭하면 update.jsp로 이동 //form에 ""있어도/없어도 되는구나
	$(".btn-primary").click(function () {
		$("form").attr("action","update.jsp");
		$("form").submit();
	})
	
	//삭제버튼을 클릭하면 deletePro.jsp
	$(".btn-danger").click(function () {
		$(form).attr("action","deletePro.jsp");
		$(form).submit();
	})
})


</script>
</body>
</html>