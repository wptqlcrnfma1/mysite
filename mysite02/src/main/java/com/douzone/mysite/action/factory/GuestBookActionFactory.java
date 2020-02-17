package com.douzone.mysite.action.factory;

import com.douzone.mysite.action.gusetbook.GeustBookDeleteAction;
import com.douzone.mysite.action.gusetbook.GuestBookDeleteFrom;
import com.douzone.mysite.action.gusetbook.GuestBookListView;
import com.douzone.mysite.action.gusetbook.GuestBookWrite;
import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory{
	
	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "list":
			return new GuestBookListView();
		case "add":
			return new GuestBookWrite();
		case "delete":
			return new GeustBookDeleteAction();
		case "deleteform":
			return new GuestBookDeleteFrom();
		default:
			return new MainAction();
		}
	}

}
