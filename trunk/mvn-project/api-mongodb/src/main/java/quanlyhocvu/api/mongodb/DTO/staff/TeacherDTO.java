/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class TeacherDTO extends AbstractStaffDTO {

    private String _teacherId;

    public String getTeacherId() {
        return _teacherId;
    }

    public void setTeacherId(String _teacherId) {
        this._teacherId = _teacherId;
    }
}
