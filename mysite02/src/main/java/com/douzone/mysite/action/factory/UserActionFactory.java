package com.douzone.mysite.action.factory;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.mysite.action.user.UserJoinAction;
import com.douzone.mysite.action.user.UserJoinFormAction;
import com.douzone.mysite.action.user.UserJoinSuccessAction;
import com.douzone.mysite.action.user.UserLoginAction;
import com.douzone.mysite.action.user.UserLoginFormAction;
import com.douzone.mysite.action.user.UserLogoutAction;
import com.douzone.mysite.action.user.UserUpdateAction;
import com.douzone.mysite.action.user.UserUpdateFormAction;
import com.douzone.mysite.action.user.UserUpdateSuccessAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "joinform":
			return new UserJoinFormAction();
		case "join":
			return new UserJoinAction();
		case "joinsuccess":
			return new UserJoinSuccessAction();
		case "loginform":
			return new UserLoginFormAction();
		case "login":
			return new UserLoginAction();
		case "logout":
			return new UserLogoutAction();
		case "updateform":
			return new UserUpdateFormAction();	
		case "update":
			return new UserUpdateAction();
		case "updatesuccess":
			return new UserUpdateSuccessAction();
		default:
			return new MainAction();
		}
	}

}