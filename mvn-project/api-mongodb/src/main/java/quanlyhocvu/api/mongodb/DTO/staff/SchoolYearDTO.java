/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class SchoolYearDTO extends AbstractObjectDTO {
    private String _schoolYearName;

    public String getSchoolYearName() {
        return _schoolYearName;
    }

    public void setSchoolYearName(String _schoolYearName) {
        this._schoolYearName = _schoolYearName;
    }
}
