/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import quanlyhocvu.api.mongodb.DTO.base.DTOConstant;
import quanlyhocvu.api.mongodb.DTO.staff.ClassDTO;

/**
 *
 * @author HuuTri
 */
public class ClassDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(ClassDTO dto) {
        boolean res = true;
        
        mongoOperation.insert(dto);
        
        return res;
    }
    
    public boolean update(ClassDTO dto) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where(DTOConstant.Id).is(dto.getId()));
        Update update = new Update();
        update.set(DTOConstant.TeacherId, dto.getTeacherId());
        update.set(DTOConstant.GradeId, dto.getGradeId());
        update.set(DTOConstant.SchoolYearId, dto.getSchoolYearId());
        update.set(DTOConstant.Description, dto.getDescription());
        update.set(DTOConstant.StudentIds, dto.getStudentIds());
        
        mongoOperation.findAndModify(query, update, ClassDTO.class);
        
        return res;
    }
    
    public boolean delete(ClassDTO dto) {
        boolean res = true;
        
        mongoOperation.remove(dto);
        
        return res;
    }
    
    public boolean insertStudent(String classId, String studentIds) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where(DTOConstant.Id).is(classId));
        Update update = new Update();
        update.push(DTOConstant.StudentIds, studentIds);
        
        mongoOperation.findAndModify(query, update, ClassDTO.class);
        
        return res;
    }
    
    public boolean deleteStudent(String classId, String studentIds) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where(DTOConstant.Id).is(classId));
        Update update = new Update();
        update.pull(DTOConstant.StudentIds, studentIds);
        
        mongoOperation.findAndModify(query, update, ClassDTO.class);
        
        return res;
    }
    
    public List<ClassDTO> getListBySchoolYear(String id) {
        Query query = Query.query(Criteria.where(DTOConstant.SchoolYearId).is(id));
        return mongoOperation.find(query, ClassDTO.class);
    }
}
