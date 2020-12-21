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
 * Servlet implementation class BookFrontController
 */
@WebServlet("*.do")
public class BookFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//요청 분류
		String requestURI = request.getRequestURI(); //     /book_dbcp_mvc/list.do
		String contextPath = request.getContextPath(); // 컨텍스트=프로젝트 /book_dbcp_mvc
		String cmd = requestURI.substring(contextPath.length()); //  /list.do
		
		//요청에 따른 액션 생성하기
		BookActionFactory factory = BookActionFactory.getInstance();
		Action action = factory.action(cmd);
		
		//생성된 액션에 일 시키기
		ActionForward af = null;
		
		try {
			af=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//설정된 이동방식과 경로에 따라 움직이기
		if(af.isRedirect()) {
			response.sendRedirect(af.getPath());
		}else {
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
