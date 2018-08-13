package kr.green.springwebproject.pagenation;

public class Criteria 
{
	private int page;
	private int perPageNum;
	
	public Criteria()
	{
		page =1;
		perPageNum = 10;    //디폴트 생성자
	}
	public Criteria(int page,int perPageNum)
	{
		this.page = page;
		this.perPageNum = perPageNum; //생성자오버로딩
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getPageStart()
	{
		return (page-1)*perPageNum; //(n-1)*10
	}
	
	
}
