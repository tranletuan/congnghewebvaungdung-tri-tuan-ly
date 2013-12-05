/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class ChiTietMonHocDTO extends AbstractObjectDTO {

    @DBRef
    private MonHocDTO monHoc;
    private String idMonHoc;
    
    @DBRef
    private KhoiLopDTO khoiLop;
    private String idKhoiLop;

    @DBRef
    private List<MonHocDTO> listMonHoc;
    private List<KhoiLopDTO> listKhoiLop;
    
    
    public ChiTietMonHocDTO(String idMonHoc, String idKhoiLop) {
        this.idMonHoc = idMonHoc;
        this.idKhoiLop = idKhoiLop;
    }
    public MonHocDTO getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHocDTO monHoc) {
        this.monHoc = monHoc;
    }

    public String getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(String idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public KhoiLopDTO getKhoiLop() {
        return khoiLop;
    }

    public void setKhoiLop(KhoiLopDTO khoiLop) {
        this.khoiLop = khoiLop;
    }

    public String getIdKhoiLop() {
        return idKhoiLop;
    }

    public void setIdKhoiLop(String idKhoiLop) {
        this.idKhoiLop = idKhoiLop;
    }

    public List<MonHocDTO> getListMonHoc() {
        return listMonHoc;
    }

    public void setListMonHoc(List<MonHocDTO> listMonHoc) {
        this.listMonHoc = listMonHoc;
    }

    public List<KhoiLopDTO> getListKhoiLop() {
        return listKhoiLop;
    }

    public void setListKhoiLop(List<KhoiLopDTO> listKhoiLop) {
        this.listKhoiLop = listKhoiLop;
    }
    
    
}
