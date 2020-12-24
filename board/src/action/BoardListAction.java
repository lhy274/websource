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
public class BoardListAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//페이지 나누기 추가
		int page=1;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		int amount = 10; //페이지당
		
		//검색기능 추가
		SearchVO searchVO = new SearchVO(); //VO에서 noargs 만들었더니 에러 사라짐
		searchVO.setCriteria(request.getParameter("criteria"));
		searchVO.setKeyword(request.getParameter("keyword"));
		searchVO.setPage(page);//vo 추가하고 페이지 나누는거 추가하면서 추가한거야
		
		searchVO.setAmount(amount);
		
		//서비스 요청
		BoardService service = new BoardServiceImple();
		//List<BoardVO> list = service.getList();
		List<BoardVO> list = service.getList(searchVO);
	
		//요청 후 받느 결과 담기 -- 담는것은 보여주는 거야. 담는 것은 보여주는 것!!!!! 그냥 저장만 하면 담을 필요 없음
		request.setAttribute("list", list);		
		//검색어/검색기준
		request.setAttribute("search", searchVO);	
		
		//이동방식 결정
		return new ActionForward(path, false);
	}

}
