package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

public interface UserMapper 
{
	public User login(@Param("id") String id,@Param("pw") String pw);
}
