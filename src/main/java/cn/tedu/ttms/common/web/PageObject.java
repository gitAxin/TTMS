package cn.tedu.ttms.common.web;

import java.io.Serializable;

/**
 * 借助此对象封装分页信息
 * 1)当前页
 * 2)记录数
 * 3)页数(总页数)
 * 4)... 
 * @author 天大java
 *
 */
public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;
	
	/**当前页*/
	private int pageCurrent=1;
	/**记录数(表中有多少条记录)*/
	private int rowCount;
	/**总页数*/
	private int pageCount;
	/**每页要显示的记录条数*/
	private int pageSize=2;
	/**取下页数据的起始记录*/
	private int startIndex;
	
		/**
		 * 当前页是2,现在yao求取第三页数据,请问StartIndex是多少,应该怎么计算	
		 */
	/**在此方法中查询*/
	public int getStartIndex(){
		return (pageCurrent-1)*pageSize;
	}
	 
	
	/**方法的返回值为总页数*/
	public int getPageCount(){
		//在此方法中通过rowCount,pageSize计算总页数
		int pages =rowCount/pageSize;
		if(rowCount%pageSize != 0){
			pages += 1;
		}
		return pages;
	}


	public int getPageCurrent() {
		return pageCurrent;
	}


	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}


	public int getRowCount() {
		return rowCount;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	@Override
	public String toString() {
		return "PageObject [pageCurrent=" + pageCurrent + ", rowCount=" + rowCount + ", pageCount=" + pageCount
				+ ", pageSize=" + pageSize + ", startIndex=" + startIndex + "]";
	}


	
	



	
	
}
