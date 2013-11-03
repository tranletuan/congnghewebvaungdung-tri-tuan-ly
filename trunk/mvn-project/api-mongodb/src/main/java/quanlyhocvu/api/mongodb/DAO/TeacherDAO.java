/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.base.DTOConstant;
import quanlyhocvu.api.mongodb.DTO.staff.TeacherDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class TeacherDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperations;
    
    public boolean insertTeacher(TeacherDTO teacher) {
        try {
            mongoOperations.insert(teacher);
            return true;
        } catch (Exception ex) {
            logger.error("TeacherDAO - insertTeacher " + ex.getMessage());
            return false;
        }
    }
    
    public boolean updateTeacher(TeacherDTO teacher) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(teacher.getId()));
            Update update = new Update();
            update.set(DTOConstant.PersonName, teacher.getPersonName());
            update.set(DTOConstant.PersonGender, teacher.getPersonGender());
            update.set(DTOConstant.PersonDateOfBirth, teacher.getPersonDateOfBirth());
            update.set(DTOConstant.PersonAddress, teacher.getPersonAddress());
            update.set(DTOConstant.TeacherId, teacher.getTeacherId());
            
            mongoOperations.findAndModify(query, update,  TeacherDTO.class);
            return true;           
        } catch (Exception ex) {
            logger.error("TeacherDAO - insertTeacher " + ex.getMessage());
            return false;
        }
    }
}
