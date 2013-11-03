/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

/**
 *
 * @author HuuTri
 */
public class StudentDTO extends AbstractPersonDTO{
   private String _studentId;
   private String _studentDateOfStudy;
   private String _studentDateOfEnd;

    public String getStudentId() {
        return _studentId;
    }

    public void setStudentId(String _studentId) {
        this._studentId = _studentId;
    }

    public String getStudentDateOfStudy() {
        return _studentDateOfStudy;
    }

    public void setStudentDateOfStudy(String _studentDateOfStudy) {
        this._studentDateOfStudy = _studentDateOfStudy;
    }

    public String getStudentDateOfEnd() {
        return _studentDateOfEnd;
    }

    public void setStudentDateOfEnd(String _studentDateOfEnd) {
        this._studentDateOfEnd = _studentDateOfEnd;
    }
}
