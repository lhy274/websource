package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BookVO;
import service.BookService;
import service.BookServiceImpl;

public class BookListAction implements Action {
	
	private String path;
	

	public BookListAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 리퀘스튼데 리스트는 넘어오는게 엇어 뿌리는거
		
		BookService service = new BookServiceImpl();
		List<BookVO> list =service.getList();
		
		//받아. 로그인 빼고 대부분 리퀘스트셋
		request.setAttribute("list", list);
		
		//가는곳
		return new ActionForward(path, false);
	}

}
