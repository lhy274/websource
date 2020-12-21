package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

public class MemberLoginAction implements Action {

	private String path;

	public MemberLoginAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userid = request.getParameter("userid");
		String password = request.getParameter("current_password");
		
		MemberService service = new MemberServiceImpl();
		MemberVO member = service.login(userid, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("login", member);

		return new ActionForward(path, true);
	}

}
