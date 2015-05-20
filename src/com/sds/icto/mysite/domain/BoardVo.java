package com.sds.icto.mysite.domain;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private Long memberno;
	private String membername;
	private Long viewcnt;
	private String date;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getMemberno() {
		return memberno;
	}
	public void setMemberno(Long memberno) {
		this.memberno = memberno;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public Long getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(Long viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	
		
}
