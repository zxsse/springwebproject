package kr.green.springwebproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;

@Controller

public class BoardController
{
	@Autowired
	BoardMapper boardMapper;
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	   public String boardGet(HttpServletRequest request, Model model)
	   {
		System.out.println("테스트");
		
		
		
		List<Board> board = boardMapper.getBoard();
		if(board != null)
		model.addAttribute("board", board);
		System.out.println(board);
		
		return "board/list";
	   }
	@RequestMapping(value="/board/detail", method = RequestMethod.GET)
	public String boardDitail(HttpServletRequest request,Model model)
	{	
		int number = Integer.parseInt(request.getParameter("number"));
		Board board = boardMapper.getBoardId(number);
		
		
		model.addAttribute("board", board); //jsp로보낸다.
		
		return "/board/detail";
		
	}
	@RequestMapping(value="board/modify", method = RequestMethod.GET)
	public String boardModifyGet(HttpServletRequest request,Model model)
	{
		int number = Integer.parseInt(request.getParameter("number"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String author = request.getParameter("author");
		Board board = boardMapper.getBoardId(number);
		
		model.addAttribute("board", board);
		return "/board/modify";
	}
	@RequestMapping(value="board/modify", method = RequestMethod.POST)
	public String boardModifyPost(HttpServletRequest request,Model model, Board board)
	{
		
		boardMapper.modifyBoard(board);
		
		return "redirect:/board/list";
	}
	@RequestMapping(value="board/write", method = RequestMethod.GET)
	public String boardWriteGet(HttpServletRequest request,Model model)
	{
		return "/board/write";
	}
	@RequestMapping(value="board/write", method = RequestMethod.POST)
	public String boardWritePost(Board board,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		board.setAuthor(user.getId());
		
		boardMapper.writeBoard(board);
		
		
		return "redirect:/board/list";
	}
}
