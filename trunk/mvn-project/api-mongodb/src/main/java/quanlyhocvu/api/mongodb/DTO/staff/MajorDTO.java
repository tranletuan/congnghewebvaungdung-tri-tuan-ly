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
public class MajorDTO extends AbstractObjectDTO {
    private String _majorName;
    private String _majorDescription;

    public String getMajorName() {
        return _majorName;
    }

    public void setMajorName(String majorName) {
        this._majorName = majorName;
    }

    public String getMajorDescription() {
        return _majorDescription;
    }

    public void setMajorDescription(String majorDescription) {
        this._majorDescription = majorDescription;
    }
}
