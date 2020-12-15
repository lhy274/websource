package action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeleteService;

public class DeleteAction {
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		//request.getParameter
		String name = request.getParameter("name");
		System.out.println("delete action : "+name);
		
		DeleteService service = new DeleteService();
		service.deleteMember(name);
	}
}
