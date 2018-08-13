package kr.green.springwebproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.springwebproject.pagenation.Criteria;

public interface UserMapper 
{
	public User login(@Param("id") String id,@Param("pw") String pw);
	
	//public void signup(@Param("id") String id,@Param("pw") String pw,@Param("email") String email);

	public void signup(@Param("user")User user);
	
	public User loginById(@Param("id") String id);
	
	public void hostModify(@Param("user") User user);
	

	public void updateUser(@Param("user") User user);
	

	
	public List<User> userListExcptLoginUser(@Param("user")User user,@Param("cri")Criteria cri);
	
	
	
	
	public int getCountUserByAdmin(@Param("user")User user);
	
	
	public void deleteUser(@Param("user")User user);
	
	
}
