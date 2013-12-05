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
public class MonHocDTO extends AbstractObjectDTO{
    private String tenMonHoc;
        
    public String gettenMonHoc() {
        return tenMonHoc;
    }

    public void settenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }
    
    public MonHocDTO() {}
    
    public MonHocDTO(String tenMonHoc, String moTa) {
        this.tenMonHoc = tenMonHoc;
        this.moTa = moTa;       
    }
}
