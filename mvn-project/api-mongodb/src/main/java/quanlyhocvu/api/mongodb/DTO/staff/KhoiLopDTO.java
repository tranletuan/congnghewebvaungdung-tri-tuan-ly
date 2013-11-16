/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class KhoiLopDTO extends AbstractObjectDTO{
    private String TenKhoiLop;

    public String getTenKhoiLop() {
        return TenKhoiLop;
    }

    public void setTenKhoiLop(String TenKhoiLop) {
        this.TenKhoiLop = TenKhoiLop;
    }
    
    public KhoiLopDTO(){
    }
    
    public KhoiLopDTO(String TenKhoiLop, String MoTa){
        this.TenKhoiLop = TenKhoiLop;
        this.MoTa = MoTa;
    }
}
