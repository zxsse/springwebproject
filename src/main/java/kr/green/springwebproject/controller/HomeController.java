package kr.green.springwebproject.controller;

import java.util.ArrayList;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  

   @Autowired
   private UserService userService;

   @Autowired
   private JavaMailSender mailSender;
   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(HttpServletRequest request, Model model) 
   {
	
      return "main/home";
      
      
   }
   @RequestMapping(value = "/", method = RequestMethod.POST)
   public String homePost(HttpServletRequest request, Model model) {
	 String email = "";
     String id = request.getParameter("id");
     String pw = request.getParameter("pw"); //home.jsp 에 name
     System.out.println("test1");
     User user;
     if((user = userService.login(id, pw)) != null) {
    	 model.addAttribute("user", user);
    	 System.out.println("test2");
         return "redirect:/board/list";
     }
     System.out.println("test3");
      return "redirect:/";
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
	 return "signup";
   }
   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signupPost(HttpServletRequest request, Model model, User user) 
   {
	 
	
	  if(!userService.signUp(user))
		  return "redirect:/signup";
	  else 
	  {
	   return "redirect:/";
	  }
	  
	  
	  
   }
   
   @RequestMapping(value="/hostmodify", method = RequestMethod.GET)
	public String hostModifyGet(HttpServletRequest request, Model model)
	{
	   
	   HttpSession session = request.getSession();
	   User nowUser = (User)session.getAttribute("user");
	   model.addAttribute("nowUser", nowUser);
	  
	   
	   return "hostmodify";
		
	}
   
   @RequestMapping(value="/hostmodify", method = RequestMethod.POST)
	public String hostModifyPost(HttpServletRequest request,User user, Model model)
	{
	   HttpSession session = request.getSession();
	   //user의 정보를 이용해서 UserMapper에 있는 xxx 메소드를 이용하여db정보를 수정
	   User nowUser = (User)session.getAttribute("user");
	   user = userService.hostModify(nowUser, user);
	   
	   if(user != null)
	   session.removeAttribute("user");
	   session.setAttribute("user", user);
	   return "redirect:/board/list";
	   
	   
		
	}

   @RequestMapping(value = "/mail/mailForm")
   public String mailForm() {

       return "mail";
   }  

   // mailSending 코드
   @RequestMapping(value = "/mail/mailSending")
   public String mailSending(HttpServletRequest request) {

       String setfrom = "zxsse1234@gamil.com";         
       String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
       String title   = request.getParameter("title");      // 제목
       String content = request.getParameter("content");    // 내용

       try {
           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper messageHelper 
               = new MimeMessageHelper(message, true, "UTF-8");

           messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
           messageHelper.setTo(tomail);     // 받는사람 이메일
           messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
           messageHelper.setText(content);  // 메일 내용

           mailSender.send(message);
       } catch(Exception e){
           System.out.println(e);
       }

       return "redirect:/mail/mailForm";
   }
	
   @RequestMapping(value="/member/withdrawal")
   public String withdrawalUser(HttpServletRequest request)
   {
	   HttpSession session=request.getSession();
	   User user = (User)session.getAttribute("user");
	   
	   
	   userService.withdrawalUser(user);
	   
	   return "redirect:/logout";
   }
   
   
}