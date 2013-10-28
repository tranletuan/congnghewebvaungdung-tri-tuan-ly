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
    private String _personName;
    private int _personGender;
    private String _personDateOfBirth;
    private AddressDTO _personAddress;

    public String getPersonName() {
        return _personName;
    }

    public void setPersonName(String _personName) {
        this._personName = _personName;
    }

    public int getPersonGender() {
        return _personGender;
    }

    public void setPersonGender(int _personGender) {
        this._personGender = _personGender;
    }

    public String getPersonDateOfBirth() {
        return _personDateOfBirth;
    }

    public void setPersonDateOfBirth(String _personDateOfBirth) {
        this._personDateOfBirth = _personDateOfBirth;
    }

    public AddressDTO getPersonAddress() {
        return _personAddress;
    }

    public void setPersonAddress(AddressDTO _personAddress) {
        this._personAddress = _personAddress;
    }
}
