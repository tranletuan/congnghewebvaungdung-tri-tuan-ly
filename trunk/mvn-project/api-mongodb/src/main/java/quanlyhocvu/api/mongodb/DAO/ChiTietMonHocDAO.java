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
import quanlyhocvu.api.mongodb.DTO.staff.ChiTietMonHocDTO;

/**
 *
 * @author Tuan
 */
@Repository
public class ChiTietMonHocDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(ChiTietMonHocDTO dto) {
        mongoOperation.insert(dto);
        return true;
    }
    
    public boolean delete(ChiTietMonHocDTO dto) {
        mongoOperation.remove(dto);
        return true;
    }
    
    public boolean update(ChiTietMonHocDTO dto) {
        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("idMonHoc", dto.getIdMonHoc());
        update.set("idKhoiLop", dto.getIdKhoiLop());
        
        mongoOperation.findAndModify(query, update, ChiTietMonHocDTO.class);
        return true;
    }
    
    public List<ChiTietMonHocDTO> getAllList() {
        return mongoOperation.findAll(ChiTietMonHocDTO.class);
    }
    
}

