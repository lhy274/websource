package action;

import java.net.URLEncoder;

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
				
		//페이지 나누기 후 넘어오는 값
		String page = request.getParameter("page");
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "utf-8");
		
		
		//	서비스 요청 => 조회수 업데이트
		BoardService service = new BoardServiceImple();
		service.hitUpdate(bno);
		
		//이거 없으면 에러나네 -> bno를 ViewAction에게 넘겨주는구나, 아까는 List에서 직접 받았던거고
		//path += "?bno="+bno; //기존
		//이거는 true라서 reqeust.set 하고 딸려 보낼 수 없어 그래서 path에 쭉 써서 딸려보내고
		//request.set 할 수 있는 false 부분에는 request 담아서 보내고
		path += "?bno="+bno+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;
		
		return new ActionForward(path, true);
	}

}
