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
public class NamHocDTO extends AbstractObjectDTO {
    private String tenNamHoc;    
    
    public NamHocDTO() {}
    
    public NamHocDTO(String tenNamHoc, String moTa) {
        this.tenNamHoc = tenNamHoc;
        this.moTa = moTa;       
    }
    public String gettenNamHoc() {
        return tenNamHoc;
    }

    public void settenNamHoc(String tenNamHoc) {
        this.tenNamHoc = tenNamHoc;
    }
}
