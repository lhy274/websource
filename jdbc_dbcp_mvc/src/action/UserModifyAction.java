package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

public class UserModifyAction implements Action {
	
	private String path;
	
	public UserModifyAction(String path) {
		super();
		this.path = path;
	}


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 리퀘스트.갯파라미터 -- pro에서 하던 거
		int no = Integer.parseInt(request.getParameter("no"));
		String addr = request.getParameter("addr");
		String mobile = request.getParameter("mobile");
		
		//서비스에게 부탁
		UserService service = new UserServiceImpl();
		boolean flag =service.updateUser(addr, mobile, no);
		
		if(!flag) { // 수정페이지 - 안됐다면
			path = "update.do";
		}
		
		return new ActionForward(path, true);
	}

}
