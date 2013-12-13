/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.CellFormat;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.HocSinhDAO;
import quanlyhocvu.api.mongodb.DAO.LopHocDAO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;

/**
 *
 * @author linhly
 */
@Repository
public class HandleExcelFile {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    @Autowired
    private LopHocDAO lophocDAO;
    private WritableWorkbook workbook;

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write(List<HocSinhDTO> listHocSinh) throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("DanhSachHocSinh", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet, listHocSinh);
        workbook.write();
        workbook.close();
    }
    //Create workbook
    public void createWorkbook() throws IOException{
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        setWorkbook(Workbook.createWorkbook(file, wbSettings));
    }
    //Write data into every sheet
    public void writeSheetClass( List<HocSinhDTO> listHocSinh, LopHocDTO lopHoc, int numberSheet, String sheetName) throws WriteException, IOException {        
        this.getWorkbook().createSheet(sheetName, numberSheet);
        WritableSheet excelSheet = this.getWorkbook().getSheet(numberSheet);
        createLabelByClass(excelSheet);
        createContentByClass(excelSheet, listHocSinh);
        getWorkbook().write();        
    }
    
    

    private void createLabel(WritableSheet sheet) throws WriteException {
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
        String[] listHeader = {"STT", "Mã Học Sinh", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Lớp", "Ngày Nhập Học", "Ngày Nghỉ Học", "Trạng Thái Học Sinh"};
        for (int i = 0; i < listHeader.length; i++) {
            addCaption(sheet, i, 0, listHeader[i]);
        }

    }

    private void createContent(WritableSheet sheet, List<HocSinhDTO> listHocSinh) throws WriteException {

        for (int i = 0; i < listHocSinh.size(); i++) {
            //insert moi hoc sinh vao
            int row = i + 1;
            HocSinhDTO hs = listHocSinh.get(i);
            //STT
            addNumber(sheet, 0, row, row);
            //Ma Hoc Sinh
            addLable(sheet, 1, row, hs.getmaHocSinh());
            //Ho Ten
            addLable(sheet, 2, row, hs.gethoTen());
            //Gioi Tinh
            if (hs.getgioiTinh() == 1) {
                addLable(sheet, 3, row, "Nam");
            } else {
                addLable(sheet, 3, row, "Nữ");
            }
            //Ngay Sinh 
            if (hs.getngaySinh() != null) {
                addDate(sheet, 4, row, hs.getngaySinh());
            } else {
                addLable(sheet, 4, row, "");
            }

            //Dia Chi
            addLable(sheet, 5, row, hs.getdiaChi());
            //Lop
            String khoiLop = hs.getKhoiLopHienTai().gettenKhoiLop();
            //LopHocDTO lopHoc = lophocDAO.getLopHocById(hs.getMaLopHoc());
//            addLable(sheet, 6, row, khoiLop + lopHoc.gettenLopHoc());//se them lophoc nua
            addLable(sheet, 6, row, khoiLop);//se them lophoc nua
            //Ngay Nhap Hoc
            if (hs.getngayNhapHoc() != null) {
                addDate(sheet, 7, row, hs.getngayNhapHoc());
            } else {
                addLable(sheet, 7, row, "");
            }

            //Ngay Nghi Hoc
            if (hs.getngayNghiHoc() != null) {
                addDate(sheet, 8, row, hs.getngayNghiHoc());
            } else {
                addLable(sheet, 8, row, "");
            }

            //Trang Thai Hoc Sinh
            addLable(sheet, 9, row, hs.getTrangThaiHS().name());

        }

    }

    private void createContentByClass(WritableSheet sheet, List<HocSinhDTO> listHocSinh) throws WriteException{
        for (int i = 0; i < listHocSinh.size(); i++) {
            //insert moi hoc sinh vao
            int row = i + 1;
            HocSinhDTO hs = listHocSinh.get(i);
            //STT
            addNumber(sheet, 0, row, row);
            //Ma Hoc Sinh
            addLable(sheet, 1, row, hs.getmaHocSinh());
            //Ho Ten
            addLable(sheet, 2, row, hs.gethoTen());
            //Gioi Tinh
            if (hs.getgioiTinh() == 1) {
                addLable(sheet, 3, row, "Nam");
            } else {
                addLable(sheet, 3, row, "Nữ");
            }
            //Ngay Sinh 
            if (hs.getngaySinh() != null) {
                addDate(sheet, 4, row, hs.getngaySinh());
            } else {
                addLable(sheet, 4, row, "");
            }

            //Dia Chi
            addLable(sheet, 5, row, hs.getdiaChi());
            //Ngay Nhap Hoc
            if (hs.getngayNhapHoc() != null) {
                addDate(sheet, 6, row, hs.getngayNhapHoc());
            } else {
                addLable(sheet, 6, row, "");
            }

            //Ngay Nghi Hoc
            if (hs.getngayNghiHoc() != null) {
                addDate(sheet, 7, row, hs.getngayNghiHoc());
            } else {
                addLable(sheet, 7, row, "");
            }

            //Trang Thai Hoc Sinh
            addLable(sheet, 8, row, hs.getTrangThaiHS().name());

        }
    }
    
    private void createLabelByClass(WritableSheet sheet) throws WriteException {
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        times = new WritableCellFormat(times10pt);
        times.setWrap(true);

        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write Header
        String[] listHeader = {"STT", "Mã Học Sinh", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Ngày Nhập Học", "Ngày Nghỉ Học", "Trạng Thái Học Sinh"};
        for (int i = 0; i < listHeader.length; i++) {
            addCaption(sheet, i, 0, listHeader[i]);
        }

    }
    //add Text
    private void addLable(WritableSheet sheet, int column, int row, String s) throws WriteException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addDate(WritableSheet sheet, int column, int row, Date date) throws WriteException {        
        WritableCellFormat wcfdate = new WritableCellFormat(new DateFormat("dd/MM/yyyy"));
        wcfdate.setAlignment(Alignment.LEFT);
        wcfdate.setWrap(true);
        DateTime dateTime = new DateTime(column, row, date, wcfdate);
        sheet.addCell(dateTime);
    }
    //add Header

    private void addCaption(WritableSheet sheet, int column, int row, String s) throws WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    /**
     * @return the lophocDAO
     */
    public LopHocDAO getLophocDAO() {
        return lophocDAO;
    }

    /**
     * @param lophocDAO the lophocDAO to set
     */
    public void setLophocDAO(LopHocDAO lophocDAO) {
        this.lophocDAO = lophocDAO;
    }

    /**
     * @return the workbook
     */
    public WritableWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * @param workbook the workbook to set
     */
    public void setWorkbook(WritableWorkbook workbook) {
        this.workbook = workbook;
    }

}
