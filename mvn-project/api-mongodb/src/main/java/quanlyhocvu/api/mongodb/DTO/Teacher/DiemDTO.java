/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.Teacher;

import org.springframework.data.mongodb.core.mapping.DBRef;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.PhanCongDTO;

/**
 *
 * @author Tuan
 */
public class DiemDTO extends AbstractObjectDTO {

    @DBRef
    private PhanCongDTO phanCong;
    private String idPhanCong;
    
    @DBRef
    private HocSinhDTO hocSinh;
    private String idHocSinh;

    private float DiemSo;


    public DiemDTO(String idPhanCong, String idHocSinh, float DiemSo) {
        this.idPhanCong = idPhanCong;
        this.idHocSinh = idHocSinh;
        this.DiemSo = DiemSo;
    }
    
    public PhanCongDTO getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCongDTO phanCong) {
        this.phanCong = phanCong;
    }

    public String getIdPhanCong() {
        return idPhanCong;
    }

    public void setIdPhanCong(String idPhanCong) {
        this.idPhanCong = idPhanCong;
    }

    public HocSinhDTO getHocSinh() {
        return hocSinh;
    }

    public void setHocSinh(HocSinhDTO hocSinh) {
        this.hocSinh = hocSinh;
    }

    public String getIdHocSinh() {
        return idHocSinh;
    }

    public void setIdHocSinh(String idHocSinh) {
        this.idHocSinh = idHocSinh;
    }
    
    public float getDiemSo() {
        return DiemSo;
    }

    public void setDiemSo(float DiemSo) {
        this.DiemSo = DiemSo;
    }
}
