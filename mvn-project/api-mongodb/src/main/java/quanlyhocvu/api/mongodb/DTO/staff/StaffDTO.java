/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.staff;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class StaffDTO extends AbstractStaffDTO{
     @Indexed(unique=true)
     private String manhanvien;

     public String getManhanvien() {
          return manhanvien;
     }

     public void setManhanvien(String manhanvien) {
          this.manhanvien = manhanvien;
     }
}
