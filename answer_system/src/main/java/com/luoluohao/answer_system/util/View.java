package com.luoluohao.answer_system.util;

/**
 * 视图类
 * @Filename: com.septinary.common.View.java of the project [com.septinary.common]
 *     @Type: View
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年1月26日下午5:03:40
 * 
 * @param <T>
 */
public class View<T> extends Return<T> {
	//用户提示
	private String hint;

	public View(String code, String hint, String memo, String info, T data) {
		super(code, memo, info, data);
		this.hint = hint;
	}

	//解决T为String时的构造
	public View(String code, String hint, String memo, String info, T data, Class<T> classOfT) {
		super(code, memo, info, data, classOfT);
		this.hint = hint;
	}

	public View(String code, String hint, String memo, String info) {
		super(code, memo, info, null);
		this.hint = hint;
	}

	public View(String code, String hint, String memo) {
		this(code);
		this.setMemo(memo);
		this.hint = hint;
	}

	public View(String code, String hint) {
		this(code);
		this.hint = hint;
	}
	
	public View(String code) {
		super(code);
	}

	public View() {
		super();
	}
	
	public View(View<T> view) {
		super(view);
		this.hint = view.hint;
	}
	
	public View(Return<T> ret) {
		super(ret);
	}
	
	public View(Result result) {
		super(result);
	}

	public View(T data) {
		super(null, null, null, data);
	}
	
	//解决T为String时的构造
	public View(T data, Class<T> classOfT) {
		super(null, null, null, data, classOfT);
	}
	
	public View(String code, T data) {
		this(code);
		this.setData(data);
	}

	//解决T为String时的构造
	public View(String code, T data, Class<T> classOfT) {
		this(code);
		this.setData(data);
	}
	
	public View(String code, String hint, T data) {
		this(code);
		this.setData(data);
		this.hint = hint;
	}

	//解决T为String时的构造
	public View(String code, String hint, T data, Class<T> classOfT) {
		this(code);
		this.setData(data);
		this.hint = hint;
	}
	
	public View(String code, String hint, String memo, T data) {
		this(code);
		this.setMemo(memo);
		this.setData(data);
		this.hint = hint;
	}

	//解决T为String时的构造
	public View(String code, String hint, String memo, T data, Class<T> classOfT) {
		this(code);
		this.setMemo(memo);
		this.setData(data);
		this.hint = hint;
	}
	
	public String getHint() {
		return hint;
	}

	public View<T> setHint(String hint) {
		this.hint = hint;
		return this;
	}
	
}
