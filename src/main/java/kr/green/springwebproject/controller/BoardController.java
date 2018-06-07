package kr.green.springwebproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;

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
		
		
		model.addAttribute("board", board);
		
		return "/board/detail";
		
	}
}
