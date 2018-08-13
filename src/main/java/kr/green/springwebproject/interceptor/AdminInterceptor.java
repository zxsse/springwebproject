package kr.green.springwebproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.green.springwebproject.dao.User;

public class AdminInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handle)throws Exception
	{
		HttpSession session=request.getSession();
		User user = (User)session.getAttribute("user");
	
		
		if(user == null ||user.getAdmin().compareTo("USER") == 0)
		{
			response.sendRedirect("/");
			return false;
		}
		
		return true;
		
	}
	
}
