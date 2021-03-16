package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

/**
 * Servlet implementation class UserFrontController
 */
@WebServlet("*.do")
public class ShopFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		
		//요청 분류하기
		//http://localhost:8080/jdbc_dbcp_mvc/insert.do
		String requestURI = request.getRequestURI(); //     /jdbc_dbcp_mvc/modify.do
		String contextPath = request.getContextPath(); //  /jdbc_dbcp_mvc
		String cmd = requestURI.substring(contextPath.length()); //   /modify.do
		
		//요청에 맞는 Action 생성하기 => UserActionFactory
		ShopActionFactory factory = ShopActionFactory.getInstance();
		Action action=factory.action(cmd);
		
		//생성된 action 일 시키기
		ActionForward af=null;
		try {
			af=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//http://localhost:8080/jdbc_dbcp_mvc/insert.do 출발
		//http://localhost:8080/jdbc_dbcp_mvc/list.do <<== UserActionFactory에서 여기로 가래
		//http://localhost:8080/jdbc_dbcp_mvc/list.do // ~~.do니까 다시 controller가 받아서 돌아 그리고
		//http://localhost:8080/jdbc_dbcp_mvc/list.jsp <<= UserActionFactory에서 여기로 가래
		
		//이동방식에 따라 페이지 이동하기
		if(af.isRedirect()) { // sendRedirect
			response.sendRedirect(af.getPath());
		}else { // forward
			RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
