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
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;

/**
 *
 * @author linhly
 */
@Repository
public class MonHocDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(MonHocDTO dto) {
        try {
        mongoOperation.insert(dto);
        } catch (Exception ex) {
            logger.error("Subject insert: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean update(MonHocDTO dto) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("tenMonHoc", dto.gettenMonHoc());
        update.set("moTa", dto.getmoTa());
        
        mongoOperation.findAndModify(query, update, MonHocDTO.class);
        return res;
    }
    
    public boolean delete(MonHocDTO dto) {
        boolean res = true;
        mongoOperation.remove(dto);
        
        return res;
    }
    
    public List<MonHocDTO> getAllList() {
        return mongoOperation.findAll(MonHocDTO.class);
    }
}
