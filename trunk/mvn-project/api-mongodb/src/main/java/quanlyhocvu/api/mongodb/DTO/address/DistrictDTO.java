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
public class DistrictDTO extends AbstractObjectDTO{
    private String _districtName;
    private String _provinceId;

    public String getDistrictName() {
        return _districtName;
    }

    public void setDistrictName(String _districtName) {
        this._districtName = _districtName;
    }

    public String getProvinceId() {
        return _provinceId;
    }

    public void setProvinceId(String _provinceId) {
        this._provinceId = _provinceId;
    }
}
