package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import service.ProdService;
import service.ProdServiceImpl;

public class ProdAddAction implements Action {
	
	private String path;

	public ProdAddAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductVO vo = new ProductVO();
		vo.setGoods_category(request.getParameter("goods_category"));
		vo.setGoods_name(request.getParameter("goods_name"));
		vo.setGoods_content(request.getParameter("goods_content"));
		vo.setGoods_size(request.getParameter("goods_size"));
		vo.setGoods_color(request.getParameter("goods_color"));
		vo.setGoods_amount(Integer.parseInt(request.getParameter("goods_amount")));
		vo.setGoods_price(Integer.parseInt(request.getParameter("goods_price")));
		
		ProdService service = new ProdServiceImpl();
		boolean insertFlag = service.insertProb(vo);
		
		
		if(!insertFlag) {
			path = "product_write.jsp";
		}
		
		return new ActionForward(path, true);
	}

}
