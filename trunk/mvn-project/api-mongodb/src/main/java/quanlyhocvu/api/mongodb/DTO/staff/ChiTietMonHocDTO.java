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
    private String idMonHoc;
    private String idKhoiLop;

    public String getidMonHoc() {
        return idMonHoc;
    }

    public void setidMonHoc(String idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public String getidKhoiLop() {
        return idKhoiLop;
    }

    public void setidKhoiLop(String idkhoiLop) {
        this.idKhoiLop = idkhoiLop;
    }
}
