<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String tab = request.getParameter("tab");
	//첫화면을 insert로 잡는걸 해본거야
	if (tab == null) {
		tab="insert";
	} 
%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<script>
$(function () {
	var tab = '<%=tab %>';
	//console.log(tab); // 확인했으니까
	
	//부트스트랩에 있는거 쓴거야 - 원래는 다 구현해야하는데 부스트스랩에서 만들어 놓은거지 #이라는 키워드로
	$('#myMenu a[href="#'+tab+'"]').tab('show');
			
	$('a[href="#all"]').click(function() {
		location.href="view/selectPro.jsp";
	});
});
</script>


</body>
</html>