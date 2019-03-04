package com.luoluohao.answer_system.util;


import java.io.Serializable;

/**
 * 分页
 * @Filename: com.septinary.common.util.Page.java of the project [com.septinary.common]
 *     @Type: Page
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016-5-27 18:11:06
 * 
 */
@SuppressWarnings("serial")
public class Page implements Serializable {

	//数据总数
	private Integer total = null;
	
	//页码总数
	private Integer count = null;
	
	//每页行数 (*不可缺少)
	private Integer size = 0;
	
	//当前页码 (*不可缺少)
	private Integer page = 0;
	
	//起始标识
	private String from = null;
	
	//起始记录 (*不可缺少) ---->相当于“起始行号”
	private Integer row = 0;

	public Page(){}
	
	public Page(int page, int size){
		this.setSize(size);
		this.setPage(page);
	}
	
	public Page(int page, int size, String from){
		this.setSize(size);
		this.setPage(page);
		this.setFrom(from);
	}
	
	public Page(int page, int size, Object from){
		this.setSize(size);
		this.setPage(page);
		this.setFrom(from);
	}

	public Page(Page page){
		if(null==page) return;

		this.total = page.total;
		this.count = page.count;
		this.size = page.size;
		this.page = page.page;
		this.from = page.from;
		this.row = page.row;
	}
	
	public Integer getTotal() {
		return total;
	}

	public Page setTotal(Integer total) {
		this.total = total;
		
		//如果已经设置了每页size，那么可以计算出总页数count
		if(null!=this.size && 0<this.size) {
			this.setCount(total/this.size);
		}
		
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public Page setCount(Integer count) {
		this.count = count;
		return this;
	}

	public Integer getSize() {
		return size;
	}

	public Page setSize(Integer size) {
		this.size = size;
		return this;
	}

	public Integer getPage() {
		return page;
	}

	public Page setPage(Integer page) {
		this.page = page;
		
		//如果已经设置了每页size，那么可以计算出当前所处的行数row
		if(null!=this.size && 0<this.size) {
			this.setRow((page-1)*this.size);
		}
		
		return this;
	}

	public Integer getRow() {
		return row;
	}

	public Page setRow(Integer row) {
		this.row = row;
		return this;
	}

	public String getFrom() {
		return from;
	}

	public Page setFrom(String from) {
		this.from = from;
		
		//如果要设置起始分界线主键值，那么，所设置的页码page将失效，即自动从第1页开始计算
		if(null!=from) {
			this.setPage(1);
		}
		
		return this;
	}
	
	public Page setFrom(Object from) {
		this.setFrom( null==from ? null: from.toString() );
		return this;
	}

	@Override
	public String toString() {
		return this.total +","+this.count +","+this.size +","+this.page +","+this.from +","+this.row;
	}
}
