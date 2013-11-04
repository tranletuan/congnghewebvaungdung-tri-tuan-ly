/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class LopHocDTO extends AbstractObjectDTO {

    private String TenLopHoc;
    private String IDGiaoVien;
    private String IDKhoiLop;
    private String IDNamHoc;
    private List<String> ListIDHocSinh;

    public List<String> getLisIDHocSinh() {
        return ListIDHocSinh;
    }

    public void setListIDHocSinh(List<String> ListIDHocSinh) {
        this.ListIDHocSinh = ListIDHocSinh;
    }

    public String getTenLopHoc() {
        return TenLopHoc;
    }

    public void setTenLopHoc(String TenLopHoc) {
        this.TenLopHoc = TenLopHoc;
    }

    public String getIDGiaoVien() {
        return IDGiaoVien;
    }

    public void setIDGiaoVien(String IDGiaoVien) {
        this.IDGiaoVien = IDGiaoVien;
    }

    public String getIDKhoiLop() {
        return IDKhoiLop;
    }

    public void setIDKhoiLop(String IDKhoiLop) {
        this.IDKhoiLop = IDKhoiLop;
    }

    public String getIDNamHoc() {
        return IDNamHoc;
    }

    public void setIDNamHoc(String IDNamHoc) {
        this.IDNamHoc = IDNamHoc;
    }
}
