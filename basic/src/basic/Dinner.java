package basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dinner
 */
@WebServlet("/Dinner")
public class Dinner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String[] dinner = request.getParameterValues("dinner");
		
		
		response.setContentType("text/http;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><head><title>메뉴</title></head>");
		out.print("<body><h3>오늘의 저녁 메뉴</h3>");
		out.print("<ul>");
		for(String s:dinner) {
			out.print("<li>"+s+"</li>");
		}
		out.print("</body></html>");
		
//		out.print("메뉴");
//		out.print("오늘의 저녁 메뉴");
//		//out.print("<ul>");
//		for(String s:dinner) {
//			out.print(s);
//		}
//		//out.print("</body></html>");
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
