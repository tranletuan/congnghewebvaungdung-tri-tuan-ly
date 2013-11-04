/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

/**
 *
 * @author HuuTri
 */
public class HocSinhDTO extends AbstractPersonDTO{
   private String MaHocSinh;
   private String NgayNhapHoc;
   private String NgayNghiHoc;

    public String getStudentId() {
        return MaHocSinh;
    }

    public void setMaHocSinh(String MaHocSinh) {
        this.MaHocSinh = MaHocSinh;
    }

    public String getNgayNhapHoc() {
        return NgayNhapHoc;
    }

    public void setNgayNhapHoc(String NgayNhapHoc) {
        this.NgayNhapHoc = NgayNhapHoc;
    }

    public String getNgayNghiHoc() {
        return NgayNghiHoc;
    }

    public void setNgayNghiHoc(String NgayNghiHoc) {
        this.NgayNghiHoc = NgayNghiHoc;
    }
}
