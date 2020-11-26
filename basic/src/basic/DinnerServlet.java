package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DinnerServlet
 */
@WebServlet("/DinnerServlet")
public class DinnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 사용자의 선택값 가져오기
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
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
