package com.zhj.common.Bean;

import java.util.List;

public class PageBean<T> {
	
	/**
	 * 数据列表
	 */
	private List<T> datas;
	/**
	 * 当前页数
	 */
	private Integer pageNum;
	
	/**
	 * 每页显示条数
	 */
	private Integer pageSize;
	/**
	 * 总条数
	 */
	private int totalSize;
	
	public PageBean() {
		super();
	}
	
	public PageBean(List<T> datas, Integer pageNum, Integer pageSize, int totalSize) {
		this.datas = datas;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
