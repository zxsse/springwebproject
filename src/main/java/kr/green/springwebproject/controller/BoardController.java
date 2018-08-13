package kr.green.springwebproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.green.springwebproject.dao.Board;

import kr.green.springwebproject.dao.User;

import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.pagenation.PageMaker;
import kr.green.springwebproject.service.BoardService;
import kr.green.springwebproject.service.UserService;


@Controller

public class BoardController
{
	@Resource
	private String uploadPath;
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	   public String boardGet(HttpServletRequest request, Model model,Criteria cri,String search,Integer type)
	   {
		
		
		
		
		if(cri == null)
			cri = new Criteria();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		
		cri.setPerPageNum(100);
		
		ArrayList<Board> list = null;
		int totalCount=0;
		
		
		totalCount = boardService.getCountByBoardlist(type, cri, search);
		list=boardService.getlistBoard(type, cri, search);
		
		
		//Criteria cri = new Criteria(1,5); 
		
		System.out.println(cri);
		pageMaker.setTotalCount(totalCount);
		HttpSession session=request.getSession();
		User user = (User)session.getAttribute("user");
		
		boolean admin = userService.iaAdmin(user);
		
		if(list != null)
		model.addAttribute("board", list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("search", search);
		model.addAttribute("type", type);
		
	
		
		
		System.out.println(admin);
		model.addAttribute("admin", admin);
		
		
		
		return "board/list";
	   }
	@RequestMapping(value="/board/detail", method = RequestMethod.GET)
	public String boardDitail(HttpServletRequest request,Model model,int number)
	{	
		Board board = boardService.boardDetail(number);
		
		HttpSession session=request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		
		boolean isAuthor = boardService.isAuthor(user, board);
		
		
		
		
		String filepath = board.getFilepath();
		//filepath : /년/월/일/uuid_파일명
		if(filepath != null)
		{
		String fileName = filepath.substring(filepath.indexOf("_")+1);
		
		 model.addAttribute("fileName", fileName);
		}
		model.addAttribute("filepath", filepath);
		model.addAttribute("isAuthor", isAuthor);
		model.addAttribute("board", board); //jsp로보낸다.
		
		
		
		return "/board/detail";
		
	}
	@RequestMapping(value="board/modify", method = RequestMethod.GET)
	public String boardModifyGet(HttpServletRequest request,Model model,MultipartFile file,Integer del,Integer number) throws Exception
	{
		
		Board board = boardService.boardDetail(number);
		
		if(del != null && del ==1)
		{
			board.setFilepath(null);
		}
		//DB에서 불러온 게시판의 정보에서 업로드 파일경로를 지움
		//DB에서는 지우지않음
		
		
		model.addAttribute("board", board);
		
		
		
		
		
		String filepath = board.getFilepath();
		//filepath : /년/월/일/uuid_파일명
		if(filepath != null)
		{
		String fileName = filepath.substring(filepath.indexOf("_")+1);
		
		 model.addAttribute("fileName", fileName);
		 System.out.println(board.getFilepath()+"1");
		}
		model.addAttribute("filepath", filepath);
		
		
		return "/board/modify";
	}
	@RequestMapping(value="board/modify", method = RequestMethod.POST)
	public String boardModifyPost(HttpServletRequest request,Model model, Board board,MultipartFile file,Integer del,Integer number) throws Exception
	{
		boardService.boardModify(board, file, uploadPath, del);
		
		return "redirect:/board/list";
	}
	@RequestMapping(value="board/write", method = RequestMethod.GET)
	public String boardWriteGet(HttpServletRequest request,Model model)
	{
		return "/board/write";
	}
	@RequestMapping(value="board/write", method = RequestMethod.POST)
	public String boardWritePost(Board board,HttpServletRequest request,MultipartFile file) throws Exception
	{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		boardService.writeBoard(board, user, file, uploadPath);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="board/search", method = RequestMethod.GET)
	public String SearchId(Board board,HttpServletRequest request,Model model,Criteria cri)
	{
			
			
			//내가쓴글
			
			if(cri == null)
				cri = new Criteria();
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(cri);
			
			
			HttpSession session =request.getSession();
			User user=(User)session.getAttribute("user");
			ArrayList<Board> list = null;
			int totalCount=0;
			String author = user.getId();
			
			
			totalCount= boardService.getCountByBoardlist(2, cri, user.getId());
			list = boardService.getlistBoard(2, cri,user.getId());
					
					
			
			
			
			
			
			
			
			//Criteria cri = new Criteria(1,5); 
			
			System.out.println(cri);
			pageMaker.setTotalCount(totalCount);
			
			if(list != null)
			model.addAttribute("board", list);
			model.addAttribute("pageMaker", pageMaker);
		
			
			boolean admin = userService.iaAdmin(user);
			
			model.addAttribute("admin", admin);
			
			
			return "board/search";
		   }
	
	private String uploadFile(String name, byte[] data) //wirte.jsp --> boardController -> wirte 메소드 
			throws Exception{
		    /* 고유한 파일명을 위해 UUID를 이용 */
			UUID uid = UUID.randomUUID();
			String savaName = uid.toString() + "_" + name;
			File target = new File(uploadPath, savaName);
			FileCopyUtils.copy(data, target);
			return savaName;
		}
	@ResponseBody
	@RequestMapping("board/download")
	public ResponseEntity<byte[]> downloadFile(String fileName)throws Exception{
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    try{
	        String FormatName = fileName.substring(fileName.lastIndexOf(".")+1);
	        HttpHeaders headers = new HttpHeaders();
	        in = new FileInputStream(uploadPath+fileName);
	        
	        fileName = fileName.substring(fileName.indexOf("_")+1);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.add("Content-Disposition",  "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
	    }catch(Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	    }finally {
	        in.close();
	    }
	    return entity;
	}
	

	@RequestMapping(value="board/delete", method = RequestMethod.GET)
	public String Deleteboard(HttpServletRequest request,Model model,Integer number)
	{
	
		boardService.deleteBoard(number);
		
		
		return "redirect:/board/list";
	}
}
