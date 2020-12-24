package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardReplyViewAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService service = new BoardServiceImple();
		BoardVO vo=service.getRow(bno);
		request.setAttribute("vo", vo);
		
		return new ActionForward(path, false);
	}

}
