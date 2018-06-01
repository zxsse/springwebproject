package kr.green.springwebproject.dao;

import java.util.List;

/*  DB에 있는 게시판에 대한 정보를 가져오기 위한 클래스 
  *  가지고 있는 인터페이스 해당 내용은 BoardMapper.xml에  쿼리문으로 작성되어 있음
  */
public interface BoardMapper 
{ 
	public List<Board> getBoard();
	
	
}
