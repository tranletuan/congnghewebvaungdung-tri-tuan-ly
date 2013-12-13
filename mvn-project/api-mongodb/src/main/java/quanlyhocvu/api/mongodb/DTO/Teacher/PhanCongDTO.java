/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.Teacher;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;

/**
 *
 * @author Tuan
 */
@Document
public class PhanCongDTO extends AbstractObjectDTO{

    @DBRef
    private ChiTietChuyenMonDTO chiTietChuyenMon;

    @DBRef
    private LopHocDTO lopHoc;
    
    private int hocKy;

    private String mota;
    
    public ChiTietChuyenMonDTO getChiTietChuyenMon() {
        return chiTietChuyenMon;
    }

    public void setChiTietChuyenMon(ChiTietChuyenMonDTO chiTietChuyenMon) {
        this.chiTietChuyenMon = chiTietChuyenMon;
    }

    public LopHocDTO getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHocDTO lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }
}
