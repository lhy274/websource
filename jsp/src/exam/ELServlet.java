package exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ELServlet
 */
@WebServlet("*.do")
public class ELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		session.setAttribute("age", 25);//3
		
		List<loginDTO> list = new ArrayList<loginDTO>();
		list.add(new loginDTO("kang123","kang123@"));
		list.add(new loginDTO("kang124","kang124@"));
		list.add(new loginDTO("kang125","kang125@"));
		list.add(new loginDTO("kang126","kang126@"));
		request.setAttribute("list", list);//4
		

		loginDTO login = new loginDTO("hong123","hong1234@");
		request.setAttribute("login",login);//불러다 써서 값 넣은거고 -2

		
		request.setAttribute("name", request.getParameter("name")); //test1 -1 //3을 보니까 페이지에서 가져와서 저장하는 거랑 그냥 여기서 저장하는 거랑 차이가 없구나.
		RequestDispatcher rd = request.getRequestDispatcher("el/test2.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
