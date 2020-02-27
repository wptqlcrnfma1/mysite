package com.douzone.mysite.vo;

public class BoardVo {
	
	private Long no;
	private String title;
	private String contents;
	private int hit;
	private String regDate;
	private Integer orderNo;
	private Integer groupNo;
	private Integer depth;
	private Long userNo;
	private String userName;
	
	



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





	public String getContents() {
		return contents;
	}





	public void setContents(String contents) {
		this.contents = contents;
	}





	public int getHit() {
		return hit;
	}





	public void setHit(int hit) {
		this.hit = hit;
	}





	public String getRegDate() {
		return regDate;
	}





	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}





	public Integer getOrderNo() {
		return orderNo;
	}





	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}





	public Integer getGroupNo() {
		return groupNo;
	}





	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}





	public Integer getDepth() {
		return depth;
	}





	public void setDepth(Integer depth) {
		this.depth = depth;
	}





	public Long getUserNo() {
		return userNo;
	}





	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}





	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
				+ regDate + ", orderNo=" + orderNo + ", groupNo=" + groupNo + ", depthNo=" + depth + ", userNo="
				+ userNo + ", userName=" + userName + "]";
	}
}
