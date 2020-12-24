package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberVO;
import lombok.AllArgsConstructor;
import service.MemberService;
import service.MemberServiceImpl;

@AllArgsConstructor
public class MemberJoinAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//joinForm.jsp에서 넘긴 값 가져오기
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String confirm_password=request.getParameter("confirm_password");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");

		//개별 값들을 객체화하기
		MemberVO member = new MemberVO();
		member.setUserid(userid);
		member.setPassword(password);
		member.setName(name);
		member.setGender(gender);
		member.setEmail(email);
		
		//간단버전--이거 에러나는데--이거 변수명 위에랑 같아서 그랬음.
		MemberVO member1 = new MemberVO();
		member1.setUserid(request.getParameter("userid"));
		member1.setPassword(request.getParameter("password"));
		member1.setName(request.getParameter("name"));
		member1.setGender(request.getParameter("gender"));
		member1.setEmail(request.getParameter("email"));
		

		MemberService service = new MemberServiceImpl();
		boolean result = service.registerMember(member1);
		
		if(!result) {
			path = "view/joinForm.jsp";
		}
		
		return new ActionForward(path, true);
	}

}
