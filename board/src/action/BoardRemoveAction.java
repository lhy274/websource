package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardRemoveAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// qna_board_pwdCheck.jsp에서 넘긴 값 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		String password = request.getParameter("password");
		
		//페이지 나누기 후 넘어오는 값
		String page = request.getParameter("page");
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "utf-8") ;

		// 서비스 요청
		BoardService service = new BoardServiceImple();
		boolean result =service.deleteArticle(bno, password);
		
		// 요청 결과에 따라 이동
		// 비밀번호가 틀리면 qna_board_pwdCheck.jsp
		if(!result) path = "view/qna_board_pwdCheck.jsp?page="+page+"&criteria="+criteria+"&keyword="+keyword;
		
		else
			path += "?page="+page+"&criteria="+criteria+"&keyword="+keyword;
		
		return new ActionForward(path, true);
	}

}
