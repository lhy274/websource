package controller;

import action.Action;
import action.BoardHitUpdateAction;
import action.BoardListAction;
import action.BoardModifyAction;
import action.BoardRemoveAction;
import action.BoardReplyAction;
import action.BoardReplyViewAction;
import action.BoardUpdateAction;
import action.BoardViewAction;
import action.BoardWriteAction;

public class BoardActionFactory {
	private static BoardActionFactory baf;
	
	private BoardActionFactory() {}
	public static BoardActionFactory getInstance() {
		if(baf==null) {
			baf = new BoardActionFactory();
		}
		return baf;
	}
	
	public Action action(String cmd) {
		Action action=null;
		
		if(cmd.equals("/qWrite.do")) {
			action = new BoardWriteAction("qList.do");
		}else if(cmd.equals("/qList.do")) {
			// qna_board_list.jsp
			action = new BoardListAction("view/qna_board_list.jsp");
		}else if(cmd.equals("/qView.do")) {
			action = new BoardViewAction("view/qna_board_view.jsp");
		}else if(cmd.equals("/qHitUpdate.do")) {
			action = new BoardHitUpdateAction("qView.do");
		}else if(cmd.equals("/qRemove.do")) {
			action = new BoardRemoveAction("qList.do");
		}else if(cmd.equals("/qModify.do")) {
			action = new BoardModifyAction("view/qna_board_modify.jsp"); 
			//action = new BoardViewAction("view/qna_board_modify.jsp"); // 작업이 같으니까. 이렇게 써도 됨.
		}else if(cmd.equals("/qUpdate.do")) {
			action = new BoardUpdateAction("qView.do");
		}else if(cmd.equals("/qReplyView.do")) {
			action = new BoardReplyViewAction("view/qna_board_reply.jsp");
		}else if(cmd.equals("/qReply.do")) { //성공시 list 보여주기
			action = new BoardReplyAction("qList.do");
		}
//		}else if(cmd.equals("/qSearch.do")) {
//			action = new BoardSearchAction("view/qna_board_list.jsp");
//		}//서치액션 안쓸거라서 import에서 지워버렸어
		
		
		
		return action;
	}
	
	
	
	
	
	
	
	
}
