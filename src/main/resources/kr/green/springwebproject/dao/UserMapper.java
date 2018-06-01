package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

public interface UserMapper 
{
	public String getEmail(@Param("id") String id);
}
