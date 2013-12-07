/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.Teacher;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;

/**
 *
 * @author Tuan
 */
@Document
public class ChiTietChuyenMonDTO extends AbstractObjectDTO{

    
    @DBRef
    private GiaoVienDTO giaoVien;
    
    @DBRef
    private ChiTietMonHocDTO chiTietMonHoc;
    
    private String mota;
    
    public GiaoVienDTO getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVienDTO giaoVien) {
        this.giaoVien = giaoVien;
    }

    public ChiTietMonHocDTO getChiTietMonHoc() {
        return chiTietMonHoc;
    }

    public void setChiTietMonHoc(ChiTietMonHocDTO chiTietMonHoc) {
        this.chiTietMonHoc = chiTietMonHoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
}

