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
public class WardDTO extends AbstractObjectDTO{
    private String _wardName; 
    private String _districtId;

    public String getWardName() {
        return _wardName;
    }

    public void setWardName(String _wardName) {
        this._wardName = _wardName;
    }

    public String getDistrictId() {
        return _districtId;
    }

    public void setDistrictId(String _districtId) {
        this._districtId = _districtId;
    }
}
