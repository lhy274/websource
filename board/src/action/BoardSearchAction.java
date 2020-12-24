package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardSearchAction implements Action {

	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		//검색기능 추가
		//SearchVO searchVO = new SearchVO(); 
//		String criteria = request.getParameter("criteria");
//		String keyword = request.getParameter("keyword");
//		searchVO.setCriteria(request.getParameter("criteria"));
//		searchVO.setKeyword(request.getParameter("keyword"));
//		
//		//서비스 요청
//		BoardService service = new BoardServiceImple();
//		List<BoardVO> search = service.getList(searchVO);
//		
//		//검색어/검색기준
//		request.setAttribute("search", searchVO);	
//		//검색결과
//		request.setAttribute("list", search);	
		
		return new ActionForward(path, false);
	}

}
