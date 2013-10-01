/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.address;

import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class ProvinceDTO extends AbstractObjectDTO {
    private String _provinceName;

    public String getProvinceName() {
        return _provinceName;
    }

    public void setProvinceName(String _provinceName) {
        this._provinceName = _provinceName;
    }
}

