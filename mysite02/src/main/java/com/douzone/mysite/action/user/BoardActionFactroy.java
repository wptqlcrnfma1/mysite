package com.douzone.mysite.action.user;

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
		default:
			return new MainAction();
		}
	}

}
