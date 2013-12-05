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
public class ChiTietPhanCongDTO extends AbstractObjectDTO {

    @DBRef
    private PhanCongDTO phanCong;
    private String idPhanCong;
    
    @DBRef
    private List<PhanCongDTO> listPhanCong;
    
    private String thoiGian;
    
    public ChiTietPhanCongDTO(String idPhanCong, String thoiGian) {
        this.idPhanCong = idPhanCong;
        this.thoiGian = thoiGian;
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

    public List<PhanCongDTO> getListPhanCong() {
        return listPhanCong;
    }

    public void setListPhanCong(List<PhanCongDTO> listPhanCong) {
        this.listPhanCong = listPhanCong;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
