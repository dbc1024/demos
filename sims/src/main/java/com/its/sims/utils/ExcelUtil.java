package com.its.sims.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by csz on 2017/8/29.
 */
public class ExcelUtil extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String filename = (String) model.get("filename");
        List<String> thList = (List<String>)model.get("thList");
        List<List<String>> dataList = (List<List<String>>)model.get("dataList");

        //设置字体相关样式
        HSSFFont font = workbook.createFont();
        font.setItalic(false);                          //斜体true为斜体
        font.setFontName("微软雅黑");                   //设置字体的样式，如：宋体、微软雅黑等
        font.setFontHeightInPoints((short)12);          //设置字体的大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   //对文中进行加粗
        font.setColor(HSSFColor.GREY_25_PERCENT.index); //设置字体的颜色

        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);



        if(dataList != null && dataList.size() != 0){

            //创建一张表
            Sheet sheet = workbook.createSheet();
            //创建第一行（表头）
            Row row = sheet.createRow(0);
            //列
            Cell cell = null;

            //为表头的每一列赋值
            for (int i=0; i<thList.size(); i++){

                cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellStyle(style);
                cell.setCellValue(thList.get(i));
            }

            //为表体的每一行赋值，并为每一行的每个单元格赋值
            for (int i=0; i<dataList.size(); i++){

                sheet.setColumnWidth((short) i, (short) (35.7 * 100));
                row = sheet.createRow(i + 1);

                List<String> dataValues = dataList.get(i);
                for(int j=0; j<dataValues.size(); j++){
                    cell = row.createCell(j, cell.CELL_TYPE_STRING);
                    cell.setCellValue(dataValues.get(j));
                }
            }

            //web浏览通过MIME类型判断文件是excel类型
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            // 对文件名进行处理。防止文件名乱码
            //String fileName = CharEncodingEdit.processFileName(request, filename);
            String fileName = URLEncoder.encode(filename, "utf-8");

            // Content-disposition属性设置成以附件方式进行下载
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();

        }
    }

    public List<List<String>> importExcel(MultipartFile file){

        List<List<String>> dataList = new LinkedList<>();

        try {
            Workbook wb = new HSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            for( int i=1; i <= sheet.getLastRowNum(); i++ ){
                List<String> dataValues = new LinkedList<>();
                Row row = sheet.getRow(i);

                for(int m=0; m<row.getLastCellNum(); m++){
                    //将每个单元格转换为string类型，不然下一句可能报错
                    row.getCell(m).setCellType(Cell.CELL_TYPE_STRING);
                    dataValues.add(row.getCell(m).getStringCellValue());
                }

                dataList.add(dataValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

}
