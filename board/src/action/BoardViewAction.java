package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardViewAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// view 페이지에서 넘어오는 값 가져오기--이제 아니야
		//BoardHitUpdateAction 에서 넘어온 값
		int bno = Integer.parseInt(request.getParameter("bno"));

		//1.같이 두면 서블릿 타고 들어와서 계속 업데이트 되니까. action을 따로 찢었어.
		//0.서비스 요청 => 조회수 업데이트(새로고침 할 때마다 업데이트)
		//BoardService service = new BoardServiceImple();
		//service.hitUpdate(bno);
		
		
		//페이지 나누기 후 넘어오는 값
		SearchVO searchVO = new SearchVO();
		searchVO.setPage(Integer.parseInt(request.getParameter("page")));
		searchVO.setCriteria(request.getParameter("criteria"));
		searchVO.setKeyword(request.getParameter("keyword"));

		
		// 서비스 요청 -> bno 에 해당하는 정보 가져오기
		BoardService service = new BoardServiceImple();
		BoardVO vo = service.getRow(bno);
		
		
		request.setAttribute("vo", vo);
		request.setAttribute("searchVO", searchVO);
		
		
		
		//결과에 따라 이동	
		return new ActionForward(path, false);
	}

}
