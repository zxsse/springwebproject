package kr.green.springwebproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.pagenation.PageMaker;
import kr.green.springwebproject.service.AdminService;  

@Controller
@RequestMapping(value="/admin/*", method = RequestMethod.GET)
public class AdminController
{
	@Autowired
	AdminService adminService;
	@RequestMapping(value="/board", method = RequestMethod.GET)
	public String adminGet(HttpServletRequest request,Model model,Criteria cri)
	{
		if(cri == null)
			cri = new Criteria();
		
		
		
		
		ArrayList<Board> list = null;
		int totalCount=0;
		
		
	
		totalCount = adminService.getCountBoardByAdmin(cri);
		list =  adminService.getBoardbyAdmin(cri);
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		pageMaker.setTotalCount(totalCount);
		
		if(list != null)
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		boolean superadmin = !adminService.isSuperAdmin(user);
		
		
		model.addAttribute("superadmin", superadmin);
		return "admin/board";
	   }
		
	
	@RequestMapping(value="/board/disable")
	public String boardDisable(HttpServletRequest request,Model model,Integer number,String disable,Integer page)
	{
		adminService.setBoardDisable(number, disable, page);
		
		
		if(page == null)
		
			page=1;
			model.addAttribute("page", page); 
		
		return "redirect:/admin/board";	
	}
	
	
	
	
	
	@RequestMapping(value="/user")
	public String userManage(HttpServletRequest request,Model model,Criteria cri)
	{
		HttpSession session=request.getSession();
		User nowUser=(User)session.getAttribute("user");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String admin = request.getParameter("admin");
		String superadmin = request.getParameter("superadmin");
		ArrayList<User> list =null;
		
		
		
		if(cri == null)
			cri = new Criteria();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		
		int totalCount =0;
		
		totalCount = adminService.getCountBoardByAdmin(cri);
		list = adminService.countUsersExcaptLoginUser(nowUser, cri);
				

		
		System.out.println(list.size());
		
		
		
	
		pageMaker.setTotalCount(totalCount);
		
		
		
		
		
		
		
		
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		model.addAttribute("email", email);
		model.addAttribute("admin", admin);
		model.addAttribute("user", nowUser);
		model.addAttribute("superadmin", superadmin);
		
		
		return "admin/user";
	}
	
	@RequestMapping(value="/user/modify")
	public String userModify(HttpServletRequest request,Model model,String id,String admin,Integer page)
	{	
		if(page == null)
		
			page=1;
			model.addAttribute("page", page);
			adminService.userModify(id, admin);
			
		return "redirect:/admin/user";	
	}
	
	@RequestMapping(value="/board/delete")
	public String delete(Integer number,Integer page,Model model)
	{
	
		if(page == null)
			
			page=1;
			model.addAttribute("page", page);
		
		adminService.boardDelete(number);
		return "redirect:/admin/board";
	}
	
}
