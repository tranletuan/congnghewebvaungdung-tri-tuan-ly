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
public class PhanCongDTO extends AbstractObjectDTO{

    @DBRef
    private ChiTietChuyenMonDTO chiTietChuyenMon;
    private String idChiTietChuyenMon;
    
    @DBRef
    private LopHocDTO lopHoc;
    private String idLopHoc;
    
    @DBRef
    private List<ChiTietChuyenMonDTO> listChiTietChuyenMon;
    private List<LopHocDTO> listLopHoc;
    
    private String mota;
    
    public PhanCongDTO(String idChiTietChuyenMon, String idLopHoc, String mota){
        this.idChiTietChuyenMon = idChiTietChuyenMon;
        this.idLopHoc = idLopHoc;
        this.moTa = mota;
    }        
    
    public ChiTietChuyenMonDTO getChiTietChuyenMon() {
        return chiTietChuyenMon;
    }

    public void setChiTietChuyenMon(ChiTietChuyenMonDTO chiTietChuyenMon) {
        this.chiTietChuyenMon = chiTietChuyenMon;
    }

    public String getIdChiTietChuyenMon() {
        return idChiTietChuyenMon;
    }

    public void setIdChiTietChuyenMon(String idChiTietChuyenMon) {
        this.idChiTietChuyenMon = idChiTietChuyenMon;
    }

    public LopHocDTO getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHocDTO lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getIdLopHoc() {
        return idLopHoc;
    }

    public void setIdLopHoc(String idLopHoc) {
        this.idLopHoc = idLopHoc;
    }

    public List<ChiTietChuyenMonDTO> getListChiTietChuyenMon() {
        return listChiTietChuyenMon;
    }

    public void setListChiTietChuyenMon(List<ChiTietChuyenMonDTO> listChiTietChuyenMon) {
        this.listChiTietChuyenMon = listChiTietChuyenMon;
    }

    public List<LopHocDTO> getListLopHoc() {
        return listLopHoc;
    }

    public void setListLopHoc(List<LopHocDTO> listLopHoc) {
        this.listLopHoc = listLopHoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
}
