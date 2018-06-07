package kr.green.springwebproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     User user = userMapper.login(id,pw);
     if(user != null)
     return "redirect:/board/list";
     
     System.out.println(user);
     
     
    System.out.println(email);
     }
     ArrayList<Board> list = (ArrayList)boardMapper.getBoard();
     System.out.println("게시판 리스트");
     for(Board tmp : list)
     {
    	 System.out.println(tmp);
     }
      return "home";
   }
   
}