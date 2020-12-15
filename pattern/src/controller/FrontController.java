package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.DeleteAction;
import action.InsertAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do") // select.do, insert.do, modify.do ~~
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/pattern/update.do
		//http://localhost:8080/pattern/select.do
		//http://localhost:8080/pattern/delete.do
		//http://localhost:8080/pattern/insert.do
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI(); //    pattern/update.do
		String contextPath = request.getContextPath();//   /pattern
		String cmd = requestURI.substring(contextPath.length());   //  /update.do
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.print("requestURI "+requestURI+"<br>");
//		out.print("contextPath "+contextPath+"<br>");
//		out.print("cmd "+cmd+"<br>");
		
		if(cmd.equals("/insert.do")) {
			InsertAction action = new InsertAction();
			action.insert(request, response);
		}else if(cmd.equals("/delete.do")) {
			DeleteAction action = new DeleteAction();
			action.delete(request,response);
		}else if(cmd.equals("/update.do")) {
			
		}else if(cmd.equals("/select.do")) {
			
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
