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
    protected String _id;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
}
