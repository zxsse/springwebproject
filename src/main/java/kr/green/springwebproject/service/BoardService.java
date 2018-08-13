package kr.green.springwebproject.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.utils.UploadFileUtils;

@Service
public class BoardService 
{
	@Autowired
	private BoardMapper boardMapper;
	
	public ArrayList<Board> getlistBoard(Integer type,Criteria cri,String search)
	{
		ArrayList<Board> list = null;
		if(type == null)
			type = 0;
		if(type ==0)
		{
			list =  (ArrayList)boardMapper.getListPage(cri);
		}
		else if(type ==1)
		{
			list = (ArrayList)boardMapper.getListPageByTitle(cri,"%" + search +"%");
		}
		else if(type ==2)
		{
			list = (ArrayList)boardMapper.getListPageByAuthor(cri,"%" + search +"%");
		}
		else if(type ==3)
		{
			list = (ArrayList)boardMapper.getListPageByContents(cri,"%" + search +"%");
		}
		
		
		
		//Criteria cri = new Criteria(1,5); 
		
		

		
		
		
		return list; 
	}
	public int getCountByBoardlist(Integer type,Criteria cri,String search)
	{
		
		int totalCount =0;
		if(type == null)
			type = 0;
		if(type ==0)
		{
			totalCount = boardMapper.getCountBoard();
			
		}
		else if(type ==1)
		{
			totalCount = boardMapper.getCountBoardByTitle("%" + search + "%");
		
		}
		else if(type ==2)
		{
			totalCount = boardMapper.getCountBoardByAuthor("%" + search + "%");
		
		}
		else if(type ==3)
		{
			totalCount = boardMapper.getCountBoardByContents("%" + search + "%");
			
		}
		
		
			return totalCount;
		}
	
	public Board boardDetail(int number)
	{
			
		return  boardMapper.getBoardId(number);
	}
	
	public boolean boardModify(Board board,MultipartFile file,String uploadPath,Integer del) throws Exception
	{
		Date created_date= new Date();
		board.setCreated_date(created_date);	
		Board tmp = boardMapper.getBoardId(board.getNumber());
		
	
		
		if(file != null && file.getOriginalFilename().length() != 0)
		{
			String filePath = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
			board.setFilepath(filePath);
			
		}
		else if (del != null && tmp.getFilepath() != null) 
		{
			new File(uploadPath + tmp.getFilepath().replace('/', File.separatorChar)).delete();
			board.setFilepath(null);
		}
		else 
		{
			board.setFilepath(tmp.getFilepath());
		}
		boardMapper.modifyBoard(board);
	
		return false;
	}
	
	public boolean isAuthor(User user,Board board)
	{
		if(user != null)
		{
			if(user.getId().compareTo(board.getAuthor()) == 0)
				return true;
			else
				return false;
		}
		
		return true;
	}
	public boolean writeBoard(Board board,User user,MultipartFile file,String uploadPath) throws Exception
	{
		board.setAuthor(user.getId());
		
		if(file != null)
		{
			String filePath = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
			board.setFilepath(filePath);
			
		}
		
		
		boardMapper.writeBoard(board);
		return true;
	}
	public boolean deleteBoard(int number)
	{
		Board board = boardMapper.getBoardId(number);
		board.setDisable("TRUE");
		boardMapper.modifyBoardByDisable(board);

	
		return true;
	}
}

