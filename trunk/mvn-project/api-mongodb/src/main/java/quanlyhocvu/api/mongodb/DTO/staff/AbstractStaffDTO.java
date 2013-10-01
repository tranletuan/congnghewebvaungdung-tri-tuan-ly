/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import quanlyhocvu.api.mongodb.DTO.base.AbstractPersonDTO;
import quanlyhocvu.api.mongodb.DTO.base.AbstractPersonDTO;

/**
 *
 * @author HuuTri
 */
public class AbstractStaffDTO extends AbstractPersonDTO{
    private double _salary;

    public double getSalary() {
        return _salary;
    }

    public void setSalary(double _salary) {
        this._salary = _salary;
    }
}
