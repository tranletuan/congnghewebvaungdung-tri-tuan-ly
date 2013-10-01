/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

import quanlyhocvu.api.mongodb.DTO.address.AddressDTO;

/**
 *
 * @author HuuTri
 */
public class AbstractPersonDTO extends AbstractObjectDTO{
    private AddressDTO _address;
    private String _personName;
    private String _dateOfBirt;
    private int _personGender;

    public AddressDTO getAddress() {
        return _address;
    }

    public void setAddress(AddressDTO _address) {
        this._address = _address;
    }

    public String getPersonName() {
        return _personName;
    }

    public void setPersonName(String _personName) {
        this._personName = _personName;
    }

    public String getDateOfBirt() {
        return _dateOfBirt;
    }

    public void setDateOfBirt(String _dateOfBirt) {
        this._dateOfBirt = _dateOfBirt;
    }

    public int getPersonGender() {
        return _personGender;
    }

    public void setPersonGender(int _personGender) {
        this._personGender = _personGender;
    }
}
