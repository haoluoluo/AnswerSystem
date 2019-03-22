package com.luoluohao.answer_system.x.asUtils;

import com.luoluohao.answer_system.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Excel文档读写工具
 *
 * @Filename: com.abcdpower.x.iwllutil.ExcelUtil.java
 * @Type: ExcelUtil
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/22 下午4:43
 * @Version: iwill.zt-service V1.0.0
 */
public class ExcelUtil {

    /**
     * @Description 导出内容成为表格
     * @Param [filename, sheetname, header, body]
     * @Return boolean
     */
    public static boolean Create(String filename, String sheetname, List<String> header, List<List<String>> body) throws IOException {
        // 新建excel表格
        HSSFWorkbook excel = Create(sheetname, header, body);
        if (null == excel) return false;
        // 写到文件输出流
        OutputStream out = new FileOutputStream(filename);
        excel.write(out);
        out.close();
        return true;
    }

    /**
     * @Description 输出内容到工作表对象
     * @Param [sheetname, header, body]
     * @Return org.apache.poi.hssf.usermodel.HSSFWorkbook
     */
    public static HSSFWorkbook Create(String sheetname, List<String> header, List<List<String>> body) {
        // 新建excel表格
        HSSFWorkbook excel = new HSSFWorkbook();
        // 添加sheet页面
        HSSFSheet hssfSheet = excel.createSheet(sheetname);
        // 在表格中创建行（excel的行号是从0开始）
        // 设置表格标题行
        int rowIndex = 0, colIndex = 0;
        int cnum = header.size() - 1, bnum = body.size() - 1;
        HSSFRow firstRow = hssfSheet.createRow(rowIndex);
        for (colIndex = 0; colIndex <= cnum; colIndex++) {
            //创建单元格 - 即列标题
            HSSFCell hssfCell = firstRow.createCell(colIndex);
            //填充单元格 - 即列标题
            String title = header.get(colIndex);
            hssfCell.setCellValue(StringUtil.IsEmpty(title) ? "-" : title);
        }
        // 设置表格数据行
        for (rowIndex = 0; rowIndex <= bnum; rowIndex++) {
            HSSFRow hssfRow = hssfSheet.createRow(rowIndex + 1);
            List<String> rowcells = body.get(rowIndex);
            //for (colIndex=0; cnum>=colIndex&&colIndex<rowcells.size(); colIndex++) {
            for (colIndex = 0; colIndex < rowcells.size(); colIndex++) {
                HSSFCell hssfCell = hssfRow.createCell(colIndex);
                String value = rowcells.get(colIndex);
                hssfCell.setCellValue(StringUtil.IsEmpty(value) ? "" : value);
            }
        }
        return excel;
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
