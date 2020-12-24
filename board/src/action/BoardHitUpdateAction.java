package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;

@AllArgsConstructor
public class BoardHitUpdateAction implements Action {
	
	private String path; //qView.do
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// view 페이지에서 넘어오는 값 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
				
		//	서비스 요청 => 조회수 업데이트
		BoardService service = new BoardServiceImple();
		service.hitUpdate(bno);
		
		//이거 없으면 에러나네 -> bno를 ViewAction에게 넘겨주는구나, 아까는 List에서 직접 받았던거고
		path += "?bno="+bno;
		
		return new ActionForward(path, true);
	}

}
