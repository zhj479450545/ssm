package com.zhj.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel 工具类
 * Created by Administrator on 2017/2/26.
 */
public class ExcelUtils {

    /**
     * 读取Excel
     *
     * @param is
     * @param fileType
     * @return
     */
    public static ArrayList<ArrayList<String>> readExcel(InputStream is, String fileType) {
        ArrayList<ArrayList<String>> rowList = new ArrayList<ArrayList<String>>();
        Workbook workbook = null;
        if ("XLS".equalsIgnoreCase(fileType)) {
            try {
                workbook = new HSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("XLSX".equalsIgnoreCase(fileType)) {//2007版
            try {
                workbook = new XSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("文件类型不正确");
        }

        if (null == workbook) {
            return rowList;
        }
        //获取页数
        int sheetNum = workbook.getNumberOfSheets();
        for (int cuurentSheet = 0; cuurentSheet < sheetNum; cuurentSheet++) {
            //获取页
            Sheet sheet = workbook.getSheetAt(cuurentSheet);
            if (null == sheet) {
                continue;
            }
            // 获取行数
            int rowNum = sheet.getLastRowNum() + 1;
            for (int cuttentRow = 0; cuttentRow < rowNum; cuttentRow++) {
                //获取行
                Row row = sheet.getRow(cuttentRow);
                if (null == row) {
                    continue;
                }
                ArrayList<String> cells = new ArrayList<String>();
                //获取列数
                int cellNum = row.getLastCellNum();
                for (int currentCell = 0; currentCell < cellNum; currentCell++) {
                    //获取列
                    Cell cell = row.getCell(currentCell);
                    if (null == cell) {
                        continue;
                    }
                    cells.add(getCellValue(cell));
                }
                rowList.add(cells);
            }
        }
        return rowList;
    }

    /**
     * 获取单元格内容
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String result = null;
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                result = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                // Excel 公式：不支持,按照原样输出
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                // 空白单元格，不做处理
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                result = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 创建Excel
     *
     * @param list        数据
     * @param keys        list中map的key值
     * @param columnNames 列名称
     * @param excelType   Excel版本，默认创建2003版
     * @return
     */
    public static Workbook createWorkBook(List<Map<String, Object>> list, String[] keys, String[] columnNames, String excelType) {
        //创建工作簿
        Workbook workBook = null;
        if (null != excelType && "XLSX".equals(excelType.toUpperCase())) {
            workBook = new XSSFWorkbook();
        } else {
            workBook = new HSSFWorkbook();
        }
        /*if (null == workBook) {
            throw new RuntimeException("无法创建Excel,请重新检查");
        }*/

        //创建两种字体
        Font titleFont = workBook.createFont();
        Font contentFont = workBook.createFont();
        //设置第一种字体样式（用于列标题）
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setColor(IndexedColors.BLACK.getIndex());
        titleFont.setBold(true);
        //设置第二种字体样式（用于列内容）
        contentFont.setFontHeightInPoints((short) 10);
        contentFont.setColor(IndexedColors.BLACK.getIndex());
        // 创建两种单元格格式
        CellStyle titleCellStyle = workBook.createCellStyle();
        CellStyle contentCellStyle = workBook.createCellStyle();
        // 设置第一种单元格样式
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置第二种单元格样式
        contentCellStyle.setFont(contentFont);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Sheet sheet = workBook.createSheet("Sheet1");
        // 创建第一行:title
        Row titleRow = sheet.createRow(0);
        if (null != columnNames && columnNames.length > 0) {
            for (int i = 0, size = columnNames.length; i < size; i++) {
                //设置列宽
                sheet.setColumnWidth(i, 50 * 100);
                Cell cell = titleRow.createCell(i);
                //设置单元格样式与名称
                cell.setCellStyle(titleCellStyle);
                cell.setCellValue(columnNames[i]);
            }
        }
        // 设置每行每列的值:内容
        if (null != list && list.size() > 0) {
            for (int i = 0, size = list.size(); i < size; i++) {
                // 创建一行，在页sheet上,row 行 cell 方格，row和cell都是从0开始的
                Row row = sheet.createRow(i+1);
                // 在row上创建一个方格
                if (null != keys && keys.length > 0) {
                    for (int j = 0, size1 = keys.length; j < size1; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(contentCellStyle);
                        cell.setCellValue(list.get(i).get(keys[j]) == null ? "" : list.get(i).get(keys[j]).toString());
                    }
                }
            }
        }
        return workBook;
    }

    public static void setExcelRespons(HttpServletResponse response, OutputStream os, String fileName) {
        ServletOutputStream sos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            sos = response.getOutputStream();
            bos = new BufferedOutputStream(sos);
            ByteArrayOutputStream baos = (ByteArrayOutputStream) os;
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            bis = new BufferedInputStream(is);
            byte[] buffer = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = is.read(buffer, 0, buffer.length))) {
                bos.write(buffer, 0, bytesRead);
            }

            // 若此处不要flush会导致下载的Excel没有数据
            bos.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != sos) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
