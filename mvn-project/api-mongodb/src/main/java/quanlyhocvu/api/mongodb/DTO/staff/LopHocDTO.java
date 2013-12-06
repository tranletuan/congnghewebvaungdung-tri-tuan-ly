/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class LopHocDTO extends AbstractObjectDTO {

    private String tenLopHoc;
    @DBRef
    private GiaoVienDTO giaoVien;
//    private String idGiaoVien;
    @DBRef
    private KhoiLopDTO khoiLop;
//    private String idKhoiLop;
    @DBRef
    private NamHocDTO namHoc;
//    private String idnamHoc;
    @DBRef
    private List<HocSinhDTO> listHocSinh;
//    private List<String> ListidHocSinh;
    
    public LopHocDTO(){};
    
//    public LopHocDTO(String tenLopHoc, String idGiaoVien, String idKhoiLop, String idnamHoc){
//        this.tenLopHoc = tenLopHoc;
//        this.idGiaoVien = idGiaoVien;
//        this.idKhoiLop = idKhoiLop;
//        this.idnamHoc = idnamHoc;
//    }

//    public List<String> getLisidHocSinh() {
//        return ListidHocSinh;
//    }
//
//    public void setListidHocSinh(List<String> ListidHocSinh) {
//        this.ListidHocSinh = ListidHocSinh;
//    }

    public String gettenLopHoc() {
        return tenLopHoc;
    }

    public void settenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

//    public String getidGiaoVien() {
//        return idGiaoVien;
//    }
//
//    public void setidGiaoVien(String idGiaoVien) {
//        this.idGiaoVien = idGiaoVien;
//    }
//
//    public String getidKhoiLop() {
//        return idKhoiLop;
//    }
//
//    public void setidKhoiLop(String idKhoiLop) {
//        this.idKhoiLop = idKhoiLop;
//    }
//
//    public String getidnamHoc() {
//        return idnamHoc;
//    }
//
//    public void setidnamHoc(String idnamHoc) {
//        this.idnamHoc = idnamHoc;
//    }

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
}
