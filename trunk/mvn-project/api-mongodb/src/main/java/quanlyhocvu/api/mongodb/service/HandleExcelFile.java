/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author linhly
 */
public class HandleExcelFile { 
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    
    public void setOutputFile(String inputFile){
        this.inputFile = inputFile;
    }
    
    public void write() throws IOException, WriteException{
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("DanhSachHocSinh", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);
        workbook.write();
        workbook.close();
    }
    
    private void createLabel(WritableSheet sheet) throws WriteException{
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        times = new WritableCellFormat(times10pt);
        times.setWrap(true);
        
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        timesBoldUnderline.setWrap(true);
        
        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        //cv.setAutosize(true);
        
        // Write Header
        String[] listHeader = {"Mã Học Sinh","Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Lớp", "Ngày Nhập Học", "Ngày Nghỉ Học", "Trạng Thái Học Sinh"};
        for (int i = 0; i < listHeader.length; i++) {
            System.out.println(listHeader[i]);
            addCaption(sheet, i, 0, listHeader[i]);
        }
        
        
    }
    
    private void createContent(WritableSheet sheet) throws WriteException{
        
       
    }
    
    //add Text
    private void addLable(WritableSheet sheet, int column, int row, String s) throws WriteException{
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }
    
    //add Header
    private void addCaption(WritableSheet sheet, int column, int row, String s) throws WriteException{
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }
    
   

}
