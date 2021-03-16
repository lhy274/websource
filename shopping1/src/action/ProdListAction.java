package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import service.ProdService;
import service.ProdServiceImpl;

public class ProdListAction implements Action {
	
	private String path;

	public ProdListAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProdService service = new ProdServiceImpl();
		List<ProductVO> list = service.getProbList();
		
		request.setAttribute("list", list);
		
		return new ActionForward(path, false);
	}

}
