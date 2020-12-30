package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import service.MemberService;
import service.MemberServiceImpl;

@AllArgsConstructor
public class MemberCheckIdAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		
		MemberService service = new MemberServiceImpl();
		boolean dupId = service.checkIdMember(userid);
		
		if(dupId) { //사용할 수 없음
			request.setAttribute("dupId", "false");			
		}else {
			request.setAttribute("dupId", "true");						
		}
		
		return new ActionForward(path, false);
	}

}
