package com.luoluohao.answer_system.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间操作
 * @Filename: com.septinary.common.util.DateTimeUtil.java of the project [com.septinary.common]
 *     @Type: DateTimeUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年2月17日下午11:10:36
 *
 */
abstract public class DateTimeUtil {

	static public String Now(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	static public long Now() {
		return System.currentTimeMillis() / 1000;
	}
	
	static public long NowMS() {
		return System.currentTimeMillis();
	}

	static public String Format(Date datetime,String format) {
		return new SimpleDateFormat(format).format(datetime);
	}

	static public String Format(Timestamp datetime, String format) {
		return new SimpleDateFormat(format).format(datetime);
	}
}
