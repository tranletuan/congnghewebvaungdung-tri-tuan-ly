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
 * @author Tuan
 */
@Document
public class ChiTietChuyenMonDTO extends AbstractObjectDTO{

    
    @DBRef
    private GiaoVienDTO giaoVien;
    private String idGiaoVien;
    
    @DBRef
    private ChiTietMonHocDTO chiTietMonHoc;
    private String idChiTietMonHoc;
    
    @DBRef
    private List<GiaoVienDTO> listGiaoVien;
    private List<ChiTietMonHocDTO> listChiTietMonHoc;
    
    private String mota;
    
    public ChiTietChuyenMonDTO(String idGiaoVien, String idChiTietMonHoc, String mota) {
        this.idGiaoVien = idGiaoVien;
        this.idChiTietMonHoc = idChiTietMonHoc;
        this.mota = mota;
    
    }
    
    public GiaoVienDTO getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVienDTO giaoVien) {
        this.giaoVien = giaoVien;
    }

    public String getIdGiaoVien() {
        return idGiaoVien;
    }

    public void setIdGiaoVien(String idGiaoVien) {
        this.idGiaoVien = idGiaoVien;
    }

    public ChiTietMonHocDTO getChiTietMonHoc() {
        return chiTietMonHoc;
    }

    public void setChiTietMonHoc(ChiTietMonHocDTO chiTietMonHoc) {
        this.chiTietMonHoc = chiTietMonHoc;
    }

    public String getIdChiTietMonHoc() {
        return idChiTietMonHoc;
    }

    public void setIdChiTietMonHoc(String idChiTietMonHoc) {
        this.idChiTietMonHoc = idChiTietMonHoc;
    }

    public List<GiaoVienDTO> getListGiaoVien() {
        return listGiaoVien;
    }

    public void setListGiaoVien(List<GiaoVienDTO> listGiaoVien) {
        this.listGiaoVien = listGiaoVien;
    }

    public List<ChiTietMonHocDTO> getListChiTietMonHoc() {
        return listChiTietMonHoc;
    }

    public void setListChiTietMonHoc(List<ChiTietMonHocDTO> listChiTietMonHoc) {
        this.listChiTietMonHoc = listChiTietMonHoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
}

