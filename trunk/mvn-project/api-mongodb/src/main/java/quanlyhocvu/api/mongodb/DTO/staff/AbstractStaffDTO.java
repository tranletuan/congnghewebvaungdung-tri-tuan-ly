/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import quanlyhocvu.api.mongodb.DTO.base.AbstractPersonDTO;

/**
 *
 * @author HuuTri
 */
public class AbstractStaffDTO extends AbstractPersonDTO{
    private String _staffDateOfWork;

    public String getStaffDateOfWork() {
        return _staffDateOfWork;
    }
    
    public void setStaffDateOfWork(String _dateOfWork) {
        this._staffDateOfWork = _dateOfWork;
    }
}
