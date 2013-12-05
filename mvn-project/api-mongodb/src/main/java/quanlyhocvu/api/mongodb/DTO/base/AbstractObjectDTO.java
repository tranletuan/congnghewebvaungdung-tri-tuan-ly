/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

import org.springframework.data.annotation.Id;

/**
 *
 * @author HuuTri
 */
public abstract class AbstractObjectDTO {
    @Id
    protected String id;
    protected String moTa;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getmoTa() {
        return moTa;
    }

    public void setmoTa(String moTa) {
        this.moTa = moTa;
    }
}
