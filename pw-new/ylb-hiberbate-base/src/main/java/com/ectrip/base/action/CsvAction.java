/*package com.ectrip.base.action;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

*//**
 * 导也当前所有查询LIST的数据。
 * 根据分页，PS的数据进行导出
 * @author lijin
 *
 *//*

public class CsvAction extends BaseAction {
    private static final long serialVersionUID = -2862629695443964658L;
    
    *//**
     * 包含完整路径的文件名
     * 传递给下载Action进行下载
     *//*
    private String fileName;
    
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	*//**
     * 导出数据
     *//*
    public String exportDataExcel() {
      
    	//判断 ps 是否有效
    	
    	
        writeData2CSV(null,fileName);
        return SUCCESS;
        
    }
    
    *//**
     * 构造一些数据
     * 实际上可能是从数据库中把大量的数据查出来
     *//*
    private List getNovels() {
       
        
     return new ArrayList();
    }
    
    *//**
     * 把数据按一定的格式写到csv文件中
     * @param novels     数据集合
     * @param fileName  csv文件完整路径
     *//*
    public void writeData2CSV(List novels,String fileName) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            //输出标题头
            //注意列之间用","间隔,写完一行需要回车换行"rn"
            String title = "序号,小说名称,作者,出版日期rn";
            fw.write(title);
            
            String content = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for(int i=0;i<novels.size();i++) {
                Novel novel = novels.get(i);
                //注意列之间用","间隔,写完一行需要回车换行"rn"
                content =(i+1)+","+novel.getName()+","+novel.getAuthor()+","+sdf.format(novel.getPublishDate())+"rn";
                fw.write(content);
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                if(fw!=null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/