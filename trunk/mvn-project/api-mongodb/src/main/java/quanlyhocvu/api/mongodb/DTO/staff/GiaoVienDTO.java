/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class GiaoVienDTO extends AbstractStaffDTO {

    private String MaGiaoVien;

    public GiaoVienDTO() {}
    
    public GiaoVienDTO(String MaGiaoVien, String HoTen, 
            int GioiTinh, Date NgaySinh, 
            String DiaChi, Date NgayVaoLam) {
        this.MaGiaoVien = MaGiaoVien;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.NgayVaoLam = NgayVaoLam;
        this.DiaChi = DiaChi;
        this.GioiTinh = GioiTinh;        
    }
    
    public String getMaGiaoVien() {
        return MaGiaoVien;
    }

    public void setMaGiaoVien(String MaGiaoVien) {
        this.MaGiaoVien = MaGiaoVien;
    }
}
