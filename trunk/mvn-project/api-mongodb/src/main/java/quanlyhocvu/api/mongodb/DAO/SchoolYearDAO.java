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
import quanlyhocvu.api.mongodb.DTO.staff.SchoolYearDTO;

/**
 *
 * @author HuuTri
 */
public class SchoolYearDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(SchoolYearDTO dto) {
        try {
        mongoOperation.insert(dto);
        } catch (Exception ex) {
            logger.error("School year insert: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean update(SchoolYearDTO dto) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where(DTOConstant.Id).is(dto.getId()));
        Update update = new Update();
        update.set(DTOConstant.SchoolYearName, dto.getSchoolYearName());
        update.set(DTOConstant.Description, dto.getDescription());
        
        mongoOperation.findAndModify(query, update, SchoolYearDTO.class);
        return res;
    }
    
    public boolean delete(SchoolYearDTO dto) {
        boolean res = true;
        
        
        return res;
    }
    
    public List<SchoolYearDTO> getAllList() {
        return mongoOperation.findAll(SchoolYearDTO.class);
    }
}
