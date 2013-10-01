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

    private String _dateOfWork;
    private String _teacherId;
    private List<String> _listMajorIds;

    public String getDateOfWork() {
        return _dateOfWork;
    }

    public void setDateOfWork(String _dateOfWork) {
        this._dateOfWork = _dateOfWork;
    }

    public String getTeacherId() {
        return _teacherId;
    }

    public void setTeacherId(String _teacherId) {
        this._teacherId = _teacherId;
    }

    public List<String> getListMajorIds() {
        return _listMajorIds;
    }

    public void setListMajorIds(List<String> _listMajorIds) {
        this._listMajorIds = _listMajorIds;
    }
}
