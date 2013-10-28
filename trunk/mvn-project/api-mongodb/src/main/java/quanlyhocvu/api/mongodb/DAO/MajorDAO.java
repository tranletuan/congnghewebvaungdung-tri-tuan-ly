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
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.base.DTOConstant;
import quanlyhocvu.api.mongodb.DTO.staff.MajorDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class MajorDAO {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperations;

    public boolean updateMajor(MajorDTO major) {
        try {
            Update update = new Update();
            update.set(DTOConstant.MajorName, major.getMajorName());
            update.set(DTOConstant.MajorDescription, major.getMajorDescription());
            
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(major.getId()));
            
            mongoOperations.findAndModify(query, update, MajorDTO.class);
            return true;
        } catch (Exception ex) {
            logger.error("MajorDAO - udpateMajor: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean insertMajor(MajorDTO major) {
        try {
            mongoOperations.insert(major);
        } catch (Exception ex) {
            logger.error("MajorDAO: insertMajor - " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public MajorDTO getMajorById(String id) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(id));
            return mongoOperations.findOne(query, MajorDTO.class);
        } catch (Exception ex) {
            logger.error("MajorDTO - getMajorById : " + ex.getMessage());
            return null;
        }
    }
    
    public List<MajorDTO> getListMajorById(List<String> ids) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).in(ids));
            return mongoOperations.find(query, MajorDTO.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
