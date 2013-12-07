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

/**
 *
 * @author Tuan
 */
@Document
public class ChiTietPhanCongDTO extends AbstractObjectDTO {

    @DBRef
    private PhanCongDTO phanCong;
    
    private String thoiGian;
    
    public PhanCongDTO getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCongDTO phanCong) {
        this.phanCong = phanCong;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
