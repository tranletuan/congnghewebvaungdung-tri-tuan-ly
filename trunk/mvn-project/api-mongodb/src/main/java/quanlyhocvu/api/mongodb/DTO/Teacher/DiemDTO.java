/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.Teacher;

import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author Tuan
 */
public class DiemDTO extends AbstractObjectDTO {

    private String MaDiem;
    private String MaGiaoVien;
    private String MaHocSinh;
    private String MaKhoiLop;
    private String MaMonHoc;
    private String MaNamHoc;
    private float DiemSo;

    public DiemDTO(String MaDiem, String MaGiaoVien, 
            String MaHocSinh, String MaKhoiLop,
            String MaMonHoc, String MaNamHoc, float DiemSo) {
           this.MaDiem = MaDiem;
           this.MaGiaoVien = MaGiaoVien;
           this.MaHocSinh = MaHocSinh;
           this.MaKhoiLop = MaKhoiLop;
           this.MaMonHoc = MaMonHoc;
           this.MaNamHoc = MaNamHoc;
           this.DiemSo = DiemSo;
    }

    public String getMaDiem() {
        return MaDiem;
    }

    public void setMaDiem(String MaDiem) {
        this.MaDiem = MaDiem;
    }

    public String getMaGiaoVien() {
        return MaGiaoVien;
    }

    public void setMaGiaoVien(String MaGiaoVien) {
        this.MaGiaoVien = MaGiaoVien;
    }

    public String getMaHocSinh() {
        return MaHocSinh;
    }

    public void setMaHocSinh(String MaHocSinh) {
        this.MaHocSinh = MaHocSinh;
    }

    public String getMaKhoiLop() {
        return MaKhoiLop;
    }

    public void setMaKhoiLop(String MaKhoiLop) {
        this.MaKhoiLop = MaKhoiLop;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getMaNamHoc() {
        return MaNamHoc;
    }

    public void setMaNamHoc(String MaNamHoc) {
        this.MaNamHoc = MaNamHoc;
    }

    public float getDiemSo() {
        return DiemSo;
    }

    public void setDiemSo(float DiemSo) {
        this.DiemSo = DiemSo;
    }

}
