<%@page import="domain.UserVO"%>
<%@page import="persistence.UserDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	UserVO vo = (UserVO)request.getAttribute("vo");

%>
<%@include file="header.jsp" %>
<div class="container">
<form action="updatePro.jsp" method="post">
	 <div class="form-group">
    <label for="no">번호</label>
    <input type="text" class="form-control" id="no" name="no" readonly value="<%=vo.getNo() %>" >
  </div>
  <div class="form-group">
    <label for="username">이름</label>
    <input type="text" class="form-control" id="username" name="username" readonly value="<%=vo.getUsername() %>">
  </div>
  <div class="form-group">
    <label for="birthyear">태어난 해</label>
    <input type="text" class="form-control" id="birthyear" name="birthyear" readonly value="<%=vo.getBirthyear() %>">
  </div>
  <div class="form-group">
    <label for="addr">주소</label>
    <input type="text" class="form-control" id="addr" name="addr" placeholder="변경 주소를 입력하세요" autofocus>
  </div>
  <div class="form-group">
    <label for="mobile">모바일</label>
    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="변경 핸드폰 번호를 입력하세요">
  </div>
  <div class="form-group">
    <button type="submit" class="btn btn-primary">수정</button>
	<button type="reset" class="btn btn-secondary">취소</button>
  </div>
</form>
</div>
<%


%>
</body>
</html>