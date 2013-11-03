/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class ClassDTO extends AbstractObjectDTO {

    private String _className;
    private String _teacherId;
    private String _gradeId;
    private String _schoolYearId;
    private List<String> _studentIds;

    public List<String> getStudentIds() {
        return _studentIds;
    }

    public void setStudentIds(List<String> _studentIds) {
        this._studentIds = _studentIds;
    }

    public String getClassName() {
        return _className;
    }

    public void setClassName(String _className) {
        this._className = _className;
    }

    public String getTeacherId() {
        return _teacherId;
    }

    public void setTeacherId(String _teacherId) {
        this._teacherId = _teacherId;
    }

    public String getGradeId() {
        return _gradeId;
    }

    public void setGradeId(String _gradeId) {
        this._gradeId = _gradeId;
    }

    public String getSchoolYearId() {
        return _schoolYearId;
    }

    public void setSchoolYearId(String _schoolYearId) {
        this._schoolYearId = _schoolYearId;
    }
}
