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
    private String magiaoVien;
    private String maHocSinh;
    private String MakhoiLop;
    private String MaMonHoc;
    private String ManamHoc;
    private float DiemSo;

    public DiemDTO(String MaDiem, String magiaoVien, 
            String maHocSinh, String MakhoiLop,
            String MaMonHoc, String ManamHoc, float DiemSo) {
           this.MaDiem = MaDiem;
           this.magiaoVien = magiaoVien;
           this.maHocSinh = maHocSinh;
           this.MakhoiLop = MakhoiLop;
           this.MaMonHoc = MaMonHoc;
           this.ManamHoc = ManamHoc;
           this.DiemSo = DiemSo;
    }

    public String getMaDiem() {
        return MaDiem;
    }

    public void setMaDiem(String MaDiem) {
        this.MaDiem = MaDiem;
    }

    public String getmagiaoVien() {
        return magiaoVien;
    }

    public void setmagiaoVien(String magiaoVien) {
        this.magiaoVien = magiaoVien;
    }

    public String getmaHocSinh() {
        return maHocSinh;
    }

    public void setmaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public String getMakhoiLop() {
        return MakhoiLop;
    }

    public void setMakhoiLop(String MakhoiLop) {
        this.MakhoiLop = MakhoiLop;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getManamHoc() {
        return ManamHoc;
    }

    public void setManamHoc(String ManamHoc) {
        this.ManamHoc = ManamHoc;
    }

    public float getDiemSo() {
        return DiemSo;
    }

    public void setDiemSo(float DiemSo) {
        this.DiemSo = DiemSo;
    }

}
