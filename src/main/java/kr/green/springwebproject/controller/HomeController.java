package kr.green.springwebproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   @Autowired
   private UserMapper userMapper;
   @Autowired
   private BoardMapper boardMapper;
   @Autowired
   BCryptPasswordEncoder passwordEncoder;
   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(HttpServletRequest request, Model model) {
	 String email = "";
     String id = request.getParameter("id");
     String pw = request.getParameter("pw"); //home.jsp 에 name
     System.out.println(id);
     if(id != null) {
     User user = userMapper.loginById(id);
     
     
     if(user != null && passwordEncoder.matches(pw, user.getPw()))
     {
    	 
    	 model.addAttribute("user", user);
    	 
    	 
     }
     return "redirect:/board/list";
     
     
     
     
     }
     ArrayList<Board> list = (ArrayList)boardMapper.getBoard();
     System.out.println("게시판 리스트");
     for(Board tmp : list)
     {
    	 System.out.println(tmp);
     }
     
     User user = userMapper.login(id,pw);
     if(user != null && passwordEncoder.matches(pw, user.getPw())) {
         model.addAttribute("user", user);
         return "redirect:/board/list";
     }
     
      return "home";
      
      
   }
   
   
   
   
   
   
   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(HttpServletRequest request, Model model) 
   {
	HttpSession session = request.getSession();
	if(session.getAttribute("user") != null)
	{
		session.removeAttribute("user");
	}
	return "redirect:/";  
	   
   }
   
   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String signupGet(HttpServletRequest request, Model model,User user) 
   {
	
	 if(user != null)
	 {
		 HttpSession session=request.getSession();
		 session.getAttribute("user");
		 return "redirect:/board/list"; 
	 }
	 
	 return "signup";
   }
   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signupPost(HttpServletRequest request, Model model, User user) 
   {
	 
	  User userSearch = userMapper.login(user.getId(), user.getPw());
	  if(userSearch != null)
		  return "redirect:/signup";
	  else 
	  {
  	   String encPw = passwordEncoder.encode(user.getPw());
  	   user.setPw(encPw);
  	   userMapper.signup(user);
	   return "redirect:/";
	  }
	  
	  
	  
   }
   
   @RequestMapping(value="hostmodify", method = RequestMethod.GET)
	public String hostModifyGet(HttpServletRequest request, Model model)
	{
	   
	   String id = request.getParameter("id");
	   String pw = request.getParameter("pw");
	   String email = request.getParameter("email");
	   
	   return "hostmodify";
		
	}
   
   @RequestMapping(value="hostmodify", method = RequestMethod.POST)
	public String hostModifyPost(HttpServletRequest request,User user, Model model)
	{
	   
	  
	   userMapper.hostModify(user);
	   return "redirect:/board/list";
	   
	   
		
	}
   
   

   
}