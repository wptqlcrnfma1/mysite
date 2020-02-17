package com.douzone.mysite.action.factory;

import com.douzone.mysite.action.board.BoardDeleteAction;
import com.douzone.mysite.action.board.BoardFormView;
import com.douzone.mysite.action.board.BoardModifyUpdate;
import com.douzone.mysite.action.board.BoardModifyView;
import com.douzone.mysite.action.board.BoardReplyAction;
import com.douzone.mysite.action.board.BoardReplyView;
import com.douzone.mysite.action.board.BoardSearchAction;
import com.douzone.mysite.action.board.BoardViewAction;
import com.douzone.mysite.action.board.BoardWriteAction;
import com.douzone.mysite.action.board.BoardWriteView;
import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactroy extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "list":
			return new BoardFormView();
		case "write":
			return new BoardWriteView();
		case "view":
			return new BoardViewAction();
		case "modify":
			return new BoardModifyView();
		case "modifyupdate":
			return new BoardModifyUpdate();
		case "writeAction":
			return new BoardWriteAction();
		case "deleteform":
			return new BoardDeleteAction();
		case "reply":
			return new BoardReplyView();
		case "replyAction":
			return new BoardReplyAction();
		case "search":
			return new BoardSearchAction();
		default:
			return new MainAction();
		}
	}

}
