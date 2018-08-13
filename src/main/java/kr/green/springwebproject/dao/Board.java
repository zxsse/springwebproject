package kr.green.springwebproject.dao;

import java.util.Date;

/* DB에서 게시판 정보를 가져와 저장할 클래스*/
public class Board 
{  //멤버 :DB에 테이블(Board)에있는 속성과 이름을 일치시켜야한다.
	private Integer number;
	private String title;
	private String author;
	private String disable="FALSE";
	private Date created_date;
	private String filepath;
		


	
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getDisable() {
		return disable;
	}
	public void setDisable(String disable) {
		this.disable = disable;
	}
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
