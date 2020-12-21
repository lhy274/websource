package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

public class UserDeleteAction implements Action {
	
	private String path;

	public UserDeleteAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 리퀘스트.겟파라미터 하는애
		int no = Integer.parseInt(request.getParameter("no"));
		
		UserService service = new UserServiceImpl();
		boolean flag = service.deleteUser(no);
		
		if(!flag) {
			path = "select.do";
		}
		
		return new ActionForward(path, true);
	}

}
