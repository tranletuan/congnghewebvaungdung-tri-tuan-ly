/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.Teacher;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class ChiTietMonHocDTO extends AbstractObjectDTO {

    @DBRef
    private MonHocDTO monHoc;
    
    @DBRef
    private KhoiLopDTO khoiLop;

    
    public MonHocDTO getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHocDTO monHoc) {
        this.monHoc = monHoc;
    }

    public KhoiLopDTO getKhoiLop() {
        return khoiLop;
    }

    public void setKhoiLop(KhoiLopDTO khoiLop) {
        this.khoiLop = khoiLop;
    }
}
