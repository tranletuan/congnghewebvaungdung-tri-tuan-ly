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
    private String TenNamHoc;    
    
    public NamHocDTO() {}
    
    public NamHocDTO(String TenNamHoc, String MoTa) {
        this.TenNamHoc = TenNamHoc;
        this.MoTa = MoTa;       
    }
    public String getTenNamHoc() {
        return TenNamHoc;
    }

    public void setTenNamHoc(String TenNamHoc) {
        this.TenNamHoc = TenNamHoc;
    }
}
