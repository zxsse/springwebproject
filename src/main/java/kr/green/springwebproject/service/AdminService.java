package kr.green.springwebproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;
import kr.green.springwebproject.pagenation.Criteria;

@Service
public class AdminService 
{
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private UserMapper userMapper;
	
	public ArrayList<Board> getBoardbyAdmin(Criteria cri)
	{
	
		
		return (ArrayList<Board>) boardMapper.getListPageByAdmin(cri);
	}
	public int getCountBoardByAdmin(Criteria cri)
	{
		return boardMapper.getCountBoardByAdmin();
	}
	
	public boolean isSuperAdmin(User user)
	{
		
	
		if(user.getAdmin().compareTo("SUPERADMIN") == 0)
			return false;
		return true;
	}
	public void setBoardDisable(int number,String disable,Integer page)
	{
		
		Board board = boardMapper.getBoardId(number);
		board.setDisable(disable);
		boardMapper.modifyBoardByDisable(board);

	}
	
	public int countUserExceptLoginUser(User nowUser)
	{
		return userMapper.getCountUserByAdmin(nowUser);
	}
	public ArrayList<User> countUsersExcaptLoginUser(User nowUser,Criteria cri)
	
	{
		return (ArrayList)userMapper.userListExcptLoginUser(nowUser, cri);
	}
	
	public boolean userModify(String id,String admin)
	{
		User user = userMapper.loginById(id);
		if(user != null)
		{
			user.setAdmin(admin);
			userMapper.updateUser(user);
			return true;
		}
		return false;
	}
	public boolean boardDelete(Integer number)
	{
		if(number != null)
		{
			Board board = boardMapper.getBoardId(number);
			boardMapper.deleteBoardByDisalbe(board);
			return true;
		}
		return false;
	}
}
