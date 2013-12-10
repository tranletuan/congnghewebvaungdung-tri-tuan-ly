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

   
    @DBRef
    private PhanCongDTO phanCong;
    

    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    private int hocKy;
    
    
}
