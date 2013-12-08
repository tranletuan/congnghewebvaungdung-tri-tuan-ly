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
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class NamHocDAO {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperation;

    public boolean insert(NamHocDTO dto) {
        try {
            mongoOperation.insert(dto);
        } catch (Exception ex) {
            logger.error("School year insert: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean update(NamHocDTO dto) {
        boolean res = true;

        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("tenNamHoc", dto.gettenNamHoc());
        update.set("moTa", dto.getmoTa());

        mongoOperation.findAndModify(query, update, NamHocDTO.class);
        return res;
    }

    public boolean delete(NamHocDTO dto) {
        boolean res = true;

        mongoOperation.remove(dto);
        return res;
    }

    public List<NamHocDTO> getAllList() {
        return mongoOperation.findAll(NamHocDTO.class);
    }

    public NamHocDTO getnamHocById(String id) {

        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperation.findOne(query, NamHocDTO.class);
    }
    
    public NamHocDTO getNamHocByName(String tenNamHoc){
        Query query = Query.query(Criteria.where("tenNamHoc").is(tenNamHoc));
        return mongoOperation.findOne(query, NamHocDTO.class);
    }
}
