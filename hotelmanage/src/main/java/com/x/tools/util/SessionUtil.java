package com.x.tools.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.x.hotels.context.ErrorCode;
import com.x.hotels.context.HandleException;

public class SessionUtil {

	public static void setUserId(HttpServletRequest request, String id) {
		HttpSession session = request.getSession();
		session.setAttribute("USER_ID", id);
		session.setMaxInactiveInterval(14400);
	}
	
	public static Integer getUserId(HttpServletRequest request)  throws HandleException{
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("USER_ID");
		if(id==null){
			throw new HandleException(ErrorCode.SESSION_ERROR, "登录已过期,请重新登录");
		}
		return id;
	}
	
}
