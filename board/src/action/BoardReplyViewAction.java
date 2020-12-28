package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardReplyViewAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		//페이지 나누기 후 넘어오는 값
		SearchVO searchVO = new SearchVO();
		searchVO.setPage(Integer.parseInt(request.getParameter("page")));
		searchVO.setCriteria(request.getParameter("criteria"));
		searchVO.setKeyword(request.getParameter("keyword"));

		BoardService service = new BoardServiceImple();
		BoardVO vo=service.getRow(bno);
		request.setAttribute("vo", vo);
		//페이지 나누기 - 리스트 개선하면서 true에서는 path 에 주소줄로 딸려보내고
		// + false로 reqeust.setAttribute로 담아서 보낼 수 있는 이 BoardReplyViewAction 클래스는 
		// 담아서 보냄.
		request.setAttribute("searchVO", searchVO);
	
		return new ActionForward(path, false);
	}

}
