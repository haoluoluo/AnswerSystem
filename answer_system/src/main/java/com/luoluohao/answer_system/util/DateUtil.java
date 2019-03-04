package com.luoluohao.answer_system.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * @param date   母数
     * @param type   时间类型(年，月，日)
     * @param symbol 符号(1为加号，2为减号)
     * @param number 增减时间具体值
     * @return
     */
    static public Date getDate(Date date, int type, int symbol, int number) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        int fieid = 0;
        if (type == 10) {
            fieid = 1;
        }
        if (type == 20) {
            fieid = 2;
        }
        if (type == 30) {
            fieid = 5;
        }
        if (symbol == 1) {
            gc.add(fieid, +number);
        }
        if (symbol == 2) {
            gc.add(fieid, -number);
        }

        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
        return gc.getTime();
    }


}
