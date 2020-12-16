package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.UserVO;
import service.UserService;
import service.UserServiceImpl;

public class UserListAction implements Action {
	
	private String path;

	public UserListAction(String path) {
		super();
		this.path = path;//list.jsp
	}


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter -> x 겟파라미터 없으니까 서비스에게 일만시키면됨
		//서비스한테 일 시키기
		UserService service = new UserServiceImpl();
		List<UserVO> list = service.getUserList();
		
		//이동방식 결정한 후 이동하기 ActionFroward 객체 생성
		request.setAttribute("list", list);
	
		return new ActionForward(path, false);
	}

}
