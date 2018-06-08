package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

public interface UserMapper 
{
	public User login(@Param("id") String id,@Param("pw") String pw);
	
	public void signup(@Param("id") String id,@Param("pw") String pw,@Param("email") String email);

	public void signup(User user);
	
	public User loginById(@Param("id") String id);
	
	public void hostModify(@Param("user") User user);
	
	
	
	
	
}
