/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

import java.util.Date;

/**
 *
 * @author HuuTri
 */
public class HocSinhDTO extends AbstractPersonDTO{
   private String MaHocSinh;
   private Date NgayNhapHoc;
   private Date NgayNghiHoc;

   public HocSinhDTO(){};
   public HocSinhDTO(String HoTen, int GioiTinh, Date NgaySinh, String DiaChi, String MaHocSinh, Date NgayNhapHoc, Date NgayNghiHoc){
     this.HoTen = HoTen;
     this.GioiTinh = GioiTinh;
     this.NgaySinh = NgaySinh;
     this.DiaChi = DiaChi;
     this.MaHocSinh = MaHocSinh;
     this.NgayNhapHoc = NgayNhapHoc;
     this.NgayNghiHoc = NgayNghiHoc;
             
   };
    public String getMaHocSinh() {
        return MaHocSinh;
    }

    public void setMaHocSinh(String MaHocSinh) {
        this.MaHocSinh = MaHocSinh;
    }

    public Date getNgayNhapHoc() {
        return NgayNhapHoc;
    }

    public void setNgayNhapHoc(Date NgayNhapHoc) {
        this.NgayNhapHoc = NgayNhapHoc;
    }

    public Date getNgayNghiHoc() {
        return NgayNghiHoc;
    }

    public void setNgayNghiHoc(Date NgayNghiHoc) {
        this.NgayNghiHoc = NgayNghiHoc;
    }
}
