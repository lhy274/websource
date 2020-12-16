package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

public class UserInsertAction implements Action {
	
	private String path;
	
	public UserInsertAction(String path) {
		this.path=path;//list.do
	}
	
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter()
		//add.jsp에서 사용자 입력값 가져오기
		
		String username = request.getParameter("username");
		String birthyear = request.getParameter("birthyear");
		String addr = request.getParameter("addr");
		String mobile = request.getParameter("mobile");
		
		//service에게 일 시키기
		UserService sevice = new UserServiceImpl();
		boolean insertFlag = sevice.insertUser(username, birthyear, addr, mobile);
		
		if(!insertFlag) {
			path = "add.jsp";
		}
		
		//작업결과에 따라 페이지 이동 방식 결정 - sendRedirect
		return new ActionForward(path, true);
	}

}
