package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.InsertService;

public class InsertAction {

	public void insert(HttpServletRequest request, HttpServletResponse response) {
		// ~Pro.jsp 에서 했던 작업
		// request.getParameter()
		String name = request.getParameter("name");
		System.out.println("insert_action name : "+name);
		
		
		//DB작업 service 부탁
		InsertService service = new InsertService();
		service.insertMember(name);

	}
	
	
}//insertAction