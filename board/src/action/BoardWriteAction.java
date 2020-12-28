package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImple;
import util.FileUploadUtil;

@AllArgsConstructor
public class BoardWriteAction implements Action {
	
	private String path;	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter()		
		FileUploadUtil utils = new FileUploadUtil();
		Map<String, String> map = utils.uploadFile(request);
		
		//map 에 들어있는 폼 요소들을 VO에 옮겨주기		
		BoardVO board = new BoardVO();
		board.setName(map.get("name"));
		board.setPassword(map.get("password"));
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		if(map.containsKey("attach"))	// 있으면 보드에 저장 없으면 넘어감	
			board.setAttach(map.get("attach"));

		//서비스 호출
		BoardService service = new BoardServiceImple();
		boolean flag = service.insertArticle(board);
		
		if(!flag) {
			path ="view/qna_board_write.jsp";
		}else {
			//성공하면 qList.do로 가는데 그대로 가면 안되지 null 남 그래서 주소 잡아서 더해
			path += "?page=1&criteria=&keyword=";
		}
		
		return new ActionForward(path, true);
		//return null;  //여기서 null 이 넘어가기 때문이거든요 그래서 메세제 상에
		//at controller.BoardFrontController.doGet(BoardFrontController.java:44) 를 가리키는 건데요
		//아까 테스트할 때 디버깅시엔 저도 null 이었어요
		//그래서 멈춰서 테스트를 진행한거에요
	}

}
