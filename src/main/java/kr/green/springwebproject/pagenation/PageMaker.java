package kr.green.springwebproject.pagenation;

public class PageMaker
{
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum=5;
	Criteria criteria;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calc(); //totalcount가있으면 endpage를 설정가능 그러면 prev랑 next 설정가능하기때문에
	}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", criteria=" + criteria + "]";
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		if(startPage <=0 )
			this.startPage=1;
		else
			
			this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		if(displayPageNum <= 5)
			this.displayPageNum = 5;
		else
			this.displayPageNum = displayPageNum;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	void calc() //값을초기화 어떻게할지 정해주는 메소드
	//현제 페이지 =1 한페이지보이는게시글  =10 페이지메이커가 보여주는 페이지수 10
	/*	endPage = (int) (Math.ceil(올림(0.1/10.0)*10);
	 *  0.1 --> 1 * 10 
	 */
	{
		endPage = (int) (Math.ceil(criteria.getPage()/(double)displayPageNum)*displayPageNum);
		startPage= (endPage - displayPageNum) +1;
		
		int tempEndPage= (int)(Math.ceil(totalCount/(double)criteria.getPerPageNum())); //총페이지수가 EndPage보다작을때
		if(endPage> tempEndPage)
			endPage = tempEndPage;
		
		//prev = true 
		//next = false  삼항연산자
		prev = startPage ==1 ? false :true;
		
		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
	}
}
