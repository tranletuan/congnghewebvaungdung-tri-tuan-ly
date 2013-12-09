/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class StaffDTO extends AbstractStaffDTO {

     @Indexed(unique = true)
     private String manhanvien;

     public String getManhanvien() {
          return manhanvien;
     }

     public void setManhanvien(String manhanvien) {
          this.manhanvien = manhanvien;
     }

     public StaffDTO(String manhanvien, String hoTen,
             int gioiTinh, Date ngaySinh,
             String diaChi, Date ngayVaoLam) {
          this.manhanvien = manhanvien;
          this.hoTen = hoTen;
          this.ngaySinh = ngaySinh;
          this.ngayVaoLam = ngayVaoLam;
          this.diaChi = diaChi;
          this.gioiTinh = gioiTinh;
     }

     public StaffDTO(String manhanvien) {
          this.manhanvien = manhanvien;
     }
     
     public StaffDTO() {}

}
