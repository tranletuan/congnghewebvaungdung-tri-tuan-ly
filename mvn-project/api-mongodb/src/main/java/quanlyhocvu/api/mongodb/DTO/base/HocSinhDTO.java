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
   private String maHocSinh;
   private Date ngayNhapHoc;
   private Date ngayNghiHoc;

   public HocSinhDTO(){};
   public HocSinhDTO(String hoTen, int gioiTinh, Date ngaySinh, String diaChi, String maHocSinh, Date ngayNhapHoc, Date ngayNghiHoc){
     this.hoTen = hoTen;
     this.gioiTinh = gioiTinh;
     this.ngaySinh = ngaySinh;
     this.diaChi = diaChi;
     this.maHocSinh = maHocSinh;
     this.ngayNhapHoc = ngayNhapHoc;
     this.ngayNghiHoc = ngayNghiHoc;
             
   };
    public String getmaHocSinh() {
        return maHocSinh;
    }

    public void setmaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public Date getngayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setngayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public Date getngayNghiHoc() {
        return ngayNghiHoc;
    }

    public void setngayNghiHoc(Date ngayNghiHoc) {
        this.ngayNghiHoc = ngayNghiHoc;
    }
}
