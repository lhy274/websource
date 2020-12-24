package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardModifyAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// bno 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 서비스 요청 : bno 에 해당하는 게시물 가져오기
		BoardService service = new BoardServiceImple();
		BoardVO vo = service.getRow(bno);
		
		// 결과에 따라 이동-실패가 없어
		request.setAttribute("vo", vo);
		
		return new ActionForward(path, false);
	}

}
