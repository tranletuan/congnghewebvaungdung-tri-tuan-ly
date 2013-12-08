/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.Teacher;

import org.springframework.data.mongodb.core.mapping.DBRef;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;

/**
 *
 * @author Tuan
 */
public class DiemDTO extends AbstractObjectDTO {

    public PhanCongDTO getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCongDTO phanCong) {
        this.phanCong = phanCong;
    }

    public HocSinhDTO getHocSinh() {
        return hocSinh;
    }

    public void setHocSinh(HocSinhDTO hocSinh) {
        this.hocSinh = hocSinh;
    }

    public float getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(float diemSo) {
        this.diemSo = diemSo;
    }

    @DBRef
    private PhanCongDTO phanCong;
    
    @DBRef
    private HocSinhDTO hocSinh;

    private float diemSo;


    
}