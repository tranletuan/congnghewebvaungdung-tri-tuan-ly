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
public class GradeDTO extends AbstractObjectDTO{
    private String _gradeName;

    public String getGradeName() {
        return _gradeName;
    }

    public void setGradeName(String _gradeName) {
        this._gradeName = _gradeName;
    }
}
