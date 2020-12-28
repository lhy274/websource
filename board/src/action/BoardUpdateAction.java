package action;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;
import util.FileUploadUtil;

@AllArgsConstructor
public class BoardUpdateAction implements Action {
	
	private String path; //qView.do
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//
		
		FileUploadUtil utils = new FileUploadUtil(); // 무조건 갔다 와야해. 리케스트.갯파라미터로 못 가져오니까.
		Map<String, String> map = utils.uploadFile(request);
		
		//int bno = Integer.parseInt(request.getParameter("bno")); 에러
		//board.setBno(bno); - bno가 안되서 이렇게 할라 했는데 // 리퀘스트 자체가 안된다는 전제를 빼먹음.... + 안된건 타입 변환 때문임.
		
		BoardVO board = new BoardVO();
		board.setBno(Integer.parseInt(map.get("bno"))); // 이게 없네 ++//있네  - int.String 차이였어.
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		board.setPassword(map.get("password"));
		if(map.containsKey("attach"))	// 있으면 보드에 저장 없으면 넘어감	
			board.setAttach(map.get("attach"));
		
		//페이지 나누기 후 넘어오는 값
		String page = map.get("page");
		String criteria = map.get("criteria");
		String keyword = URLEncoder.encode(map.get("keyword"), "utf-8");
		
		BoardService service = new BoardServiceImple();
		boolean flag = service.updateArticle(board);
		
		if(!flag) {//수정실패
			path = "qModify.do?bno="+map.get("bno")+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;;
		}else {//수정성공
			path += "?bno="+map.get("bno")+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;;
		}
		
		return new ActionForward(path, true); // 가져가는 거 아무것도 없으니까
	}

}
