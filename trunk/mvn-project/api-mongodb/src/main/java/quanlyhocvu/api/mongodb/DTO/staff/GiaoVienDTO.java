/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class GiaoVienDTO extends AbstractStaffDTO {

    private String MaGiaoVien;

    public String getTeacherId() {
        return MaGiaoVien;
    }

    public void setMaGiaoVien(String MaGiaoVien) {
        this.MaGiaoVien = MaGiaoVien;
    }
}
