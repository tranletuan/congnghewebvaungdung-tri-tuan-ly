/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Document
public class LopHocDTO extends AbstractObjectDTO {

    private String tenLopHoc;
    @DBRef
    private GiaoVienDTO giaoVien;
    @DBRef
    private KhoiLopDTO khoiLop;
    @DBRef
    private NamHocDTO namHoc;
    @DBRef
    private List<HocSinhDTO> listHocSinh;
    
    private TrangThaiLopHoc trangThaiLopHoc;    
        
    
    public enum TrangThaiLopHoc{
        ChuaLenLop,
        DaLenLop
        
    }
    @Autowired
    MongoService mongoService;
    public LopHocDTO(){
        listHocSinh = new ArrayList<>();
        trangThaiLopHoc = TrangThaiLopHoc.ChuaLenLop;
    };
    
    public int getSiSo(){
        return listHocSinh.size();
    }

    public String gettenLopHoc() {
        return tenLopHoc;
    }

    public void settenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    public GiaoVienDTO getgiaoVien() {
        return giaoVien;
    }

    public void setgiaoVien(GiaoVienDTO giaoVien) {
        this.giaoVien = giaoVien;
    }

    public KhoiLopDTO getkhoiLop() {
        return khoiLop;
    }

    public void setkhoiLop(KhoiLopDTO khoiLop) {
        this.khoiLop = khoiLop;
    }

    public NamHocDTO getnamHoc() {
        return namHoc;
    }

    public void setnamHoc(NamHocDTO namHoc) {
        this.namHoc = namHoc;
    }

    public List<HocSinhDTO> getlistHocSinh() {
        return listHocSinh;
    }

    public void setlistHocSinh(List<HocSinhDTO> listHocSinh) {
        this.listHocSinh = listHocSinh;
    }
    
    /**
     * @return the trangThaiLopHoc
     */
    public TrangThaiLopHoc getTrangThaiLopHoc() {
        return trangThaiLopHoc;
    }

    /**
     * @param trangThaiLopHoc the trangThaiLopHoc to set
     */
    public void setTrangThaiLopHoc(TrangThaiLopHoc trangThaiLopHoc) {
        this.trangThaiLopHoc = trangThaiLopHoc;
    }
    
    
    public String getNextNamHoc(){
        String namHoc = "";
        String[] res = this.namHoc.gettenNamHoc().split("\\D");
        int startNextYear = Integer.parseInt(res[0]) + 1;
        int endNextYear = Integer.parseInt(res[1]) + 1;
        return startNextYear + "-" + endNextYear;
    }
    
    public String getNextKhoiLop(){
        String khoiLop = "";
        int nextKhoiLop = Integer.parseInt(this.khoiLop.gettenKhoiLop()) + 1;
        return String.valueOf(nextKhoiLop);
    }
    
    public void setOLaiLop(HocSinhDTO hs){
        String maLopHoc = hs.getMaLopHoc();
        //Tu cho nay minh se lay ra thong tin lop de luu hoc sinh o lai lop nay
        //Logic o day se thuc hien sau
    }
    
    public void addStudents(){
        List<HocSinhDTO> listHocSinh = mongoService.getStudentsByLopHoc(this.id);
        this.setlistHocSinh(listHocSinh);
    }
    
}
