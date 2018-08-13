package kr.green.springwebproject.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public boolean signUp(User user)
	{//회원가입 페이지에서 입력받은 유저정보를 입력받아 회원가입이 진행됬으면 true를 반환하고 ,진행하지못햇으면 (유저정보가있으면)false

		  User userSearch = userMapper.login(user.getId(), user.getPw());
		  if(userSearch != null)
			  return false;
		  else 
		  {
	  	   String encPw = passwordEncoder.encode(user.getPw());
	  	   user.setPw(encPw);
	  	   user.setAdmin("USER");
	  	   userMapper.signup(user);
		   return true;
		  }
	
	}
	public User login(String id,String pw)
	{
		User user = userMapper.loginById(id);
		if(user != null && passwordEncoder.matches(pw, user.getPw())) {
			
			return user;
	}
	else 
			return null; 
	}
	
	public User hostModify(User nowUser,User newUser)
	{
		try
		
		{
		   //user의 정보를 이용해서 UserMapper에 있는 xxx 메소드를 이용하여db정보를 수정
		   
		   newUser.setId(nowUser.getId());
		   newUser.setAdmin(nowUser.getAdmin());
		   if(newUser.getPw() == null || newUser.getPw().length() == 0)
		   {
			   newUser.setPw(nowUser.getPw());
		   }
		   else
		   {
			   
			   String encPw = passwordEncoder.encode(newUser.getPw());
			   newUser.setPw(encPw);
		   }
		   userMapper.hostModify(newUser);
		 return newUser;
		}
		catch(Exception e)
		{
			System.out.println("회원정보수정에서 예외처리 발생!!");
			e.printStackTrace();//예외가발생한 정보출력
			return null;
		}
	}
	public boolean withdrawalUser(User user)
	{
		try 
		{
			 userMapper.deleteUser(user);
			 return true;
		}
		catch(Exception e)
		{
			System.out.println("회원탈퇴에서 예외처리 발생");
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean iaAdmin(User user)
	{
		if(user.getAdmin().compareTo("USER") !=0)
			return true;
		return false;
	}
	
	
}
