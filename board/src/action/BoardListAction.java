package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.PageVO;
import domain.SearchVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardListAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//qList.do?page=3&criteria=title&keyword=All

		//페이지 나누기 추가
		int page=1;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		int amount = 10; //한 페이지당 보여줄 게시물 수
		
		//검색기능 추가 + 페이지 나누기 값
		String criteria = request.getParameter("criteria");
		String keyword = request.getParameter("keyword");
		
		SearchVO searchVO = new SearchVO(); //VO에서 noargs 만들었더니 에러 사라짐
		searchVO.setCriteria(criteria);
		searchVO.setKeyword(keyword);
		searchVO.setPage(page);//vo 추가하고 페이지 나누는거 추가하면서 추가한거야		
		searchVO.setAmount(amount);
		
		//서비스 요청
		BoardService service = new BoardServiceImple();
		// 페이지 번호에 맞는 리스트 가져오기
		List<BoardVO> list = service.getList(searchVO);
		
		//전체 행 수 가져오기
		service = new BoardServiceImple();
		int totalRow = service.getRows(criteria,keyword);
		
		PageVO info = new PageVO(totalRow, searchVO);
	
		//요청 후 받은 결과 담기 -- 담는것은 보여주는 거야. 담는 것은 보여주는 것!!!!! 그냥 저장만 하면 담을 필요 없음
		request.setAttribute("list", list);		
		//검색어/검색기준
		//request.setAttribute("search", searchVO);	//아가 pageVO 만들 때 해서 이제 검색어 담는거 필요 없고 그냥 info 쓰면 될듯
		request.setAttribute("info", info);	
		
		//이동방식 결정
		return new ActionForward(path, false);
	}

}
