package kr.green.springwebproject.dao;


/* DB에서 게시판 정보를 가져와 저장할 클래스*/
public class Board 
{  //멤버 :DB에 테이블(Board)에있는 속성과 이름을 일치시켜야한다.
	private Integer number;
	private String title;
	private String author;


	
	
	
	public Integer getNumber() {
		return number;
	}
	@Override
	public String toString() {
		return "Board [number=" + number + ", title=" + title + ", author=" + author + ", contents=" + contents + "]";
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	private String contents;
	
}
