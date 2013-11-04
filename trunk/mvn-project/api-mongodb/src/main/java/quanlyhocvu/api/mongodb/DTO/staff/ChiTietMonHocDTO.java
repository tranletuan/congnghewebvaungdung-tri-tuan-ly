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
public class ChiTietMonHocDTO extends AbstractObjectDTO {
    private String IDMonHoc;
    private String IDKhoiLop;

    public String getIDMonHoc() {
        return IDMonHoc;
    }

    public void setIDMonHoc(String IDMonHoc) {
        this.IDMonHoc = IDMonHoc;
    }

    public String getIDKhoiLop() {
        return IDKhoiLop;
    }

    public void setIDKhoiLop(String IDKhoiLop) {
        this.IDKhoiLop = IDKhoiLop;
    }
}
