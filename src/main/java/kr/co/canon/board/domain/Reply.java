package kr.co.canon.board.domain;

import java.sql.Date;

public class Reply {
	private int replyNo;
	private int refBoardNo; //foreignkey -> boardNo참조
	private String replyContent;
	private String replyWriter;
	private Date rCreateDate;
	private Date rUpdateDate;
	private char updateYn;
	private char rStatus;
	private int refLike; 
	private int refUnLike;
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getRefBoardNo() {
		return refBoardNo;
	}
	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public Date getrCreateDate() {
		return rCreateDate;
	}
	public void setrCreateDate(Date rCreateDate) {
		this.rCreateDate = rCreateDate;
	}
	public Date getrUpdateDate() {
		return rUpdateDate;
	}
	public void setrUpdateDate(Date rUpdateDate) {
		this.rUpdateDate = rUpdateDate;
	}
	public char getUpdateYn() {
		return updateYn;
	}
	public void setUpdateYn(char updateYn) {
		this.updateYn = updateYn;
	}
	public char getrStatus() {
		return rStatus;
	}
	public void setrStatus(char rStatus) {
		this.rStatus = rStatus;
	}
	
	public int getRefLike() {
		return refLike;
	}
	public void setRefLike(int refLike) {
		this.refLike = refLike;
	}
	
	public int getRefUnLike() {
		return refUnLike;
	}
	public void setRefUnLike(int refUnLike) {
		this.refUnLike = refUnLike;
	}
	
	@Override
	public String toString() {
		return "댓글 [댓글번호=" + replyNo + ", 게시글번호=" + refBoardNo + ", 내용=" + replyContent
				+ ", 작성자=" + replyWriter + ", 작성일=" + rCreateDate + ", 수정일=" + rUpdateDate
				+ ", 수정여부=" + updateYn + ", 사용여부=" + rStatus + ", 좋아요=" + refLike 
				+ " , 싫어요=" + refUnLike + " ]";
	}
	
	
	
}
