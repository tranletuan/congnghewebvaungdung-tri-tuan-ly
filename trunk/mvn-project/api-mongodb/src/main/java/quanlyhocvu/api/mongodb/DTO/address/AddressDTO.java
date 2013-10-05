/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.address;

import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
public class AddressDTO extends AbstractObjectDTO{

    private String _addressName;
    private String _wardId;
    
    public String getAddressName() {
        return _addressName;
    }

    public void setAddressName(String _addressName) {
        this._addressName = _addressName;
    }

    public String getWardId() {
        return _wardId;
    }

    public void setWardId(String _wardId) {
        this._wardId = _wardId;
    }    
}
