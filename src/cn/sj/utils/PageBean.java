package cn.sj.utils;

import java.util.List;

//封装分页数据
public class PageBean<T> {

	//列表数据
	private List<T> list;
	//总记录数
	private Integer totalCount;
	//每页显示条数
	private Integer pageSize;
	//总页数
	private Integer totalPage;
	//当前页数
	private Integer currentPage;
	public PageBean(Integer totalCount, Integer pageSize, Integer currentPage) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		
		//校验每页显示条数
		if(this.pageSize == null){
			this.pageSize = 3;
		}
		//校验当前页数
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		//计算总页数
		
		this.totalPage = (this.totalCount+this.pageSize-1)/this.pageSize;
		
		//校验当前页数的值是否在正确范围
		
		if(this.currentPage < 1){ //当前页数小于1,就让当前页值为1
			
			this.currentPage = 1;
		}
		
		if(this.currentPage > this.totalPage){//当前页数超过最大页数.当前页数等于最大页数
			
			this.currentPage = this.totalPage;
		}
		
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getStartIndex() {
		return (this.currentPage-1)*this.pageSize;
	}
	
	
	
	
}
