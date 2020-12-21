package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import lombok.AllArgsConstructor;
import service.MemberService;
import service.MemberServiceImpl;

@AllArgsConstructor
public class MemberModifyAction implements Action {

	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String current_password=request.getParameter("current_password");
		String new_password=request.getParameter("new_password");
		String confirm_password=request.getParameter("confirm_password");
		
		MemberService service = new MemberServiceImpl();
		
		HttpSession session = request.getSession();
		MemberVO vo=(MemberVO)session.getAttribute("login");
		
		//1. 현재 비밀번호가 일치하는지 확인 => isLogin(userid, password)
		MemberVO info =service.login(vo.getUserid(), current_password);
		
		///2. 일치한다면 변경
		if(info!=null) {
			//로그인할 때 커넥션이 닫혀서 다시 열었어
			MemberService service1 = new MemberServiceImpl();
			boolean result = service1.updateMember(vo.getUserid(), new_password);
			if(result) {
				session.invalidate();
			}
		}else {
			path = "view/modifyForm.jsp";
		}

		return new ActionForward(path, true);
	}

}
