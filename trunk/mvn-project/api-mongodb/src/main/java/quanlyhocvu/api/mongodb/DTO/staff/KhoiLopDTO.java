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
    private String tenKhoiLop;

    public String gettenKhoiLop() {
        return tenKhoiLop;
    }

    public void settenKhoiLop(String tenkhoiLop) {
        this.tenKhoiLop = tenkhoiLop;
    }
    
    public KhoiLopDTO(){
    }
    
    public KhoiLopDTO(String tenkhoiLop, String moTa){
        this.tenKhoiLop = tenkhoiLop;
        this.moTa = moTa;
    }
}
