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
import quanlyhocvu.api.mongodb.DTO.staff.ChiTietChuyenMonDTO;

/**
 *
 * @author Tuan
 */
@Repository
public class ChiTietChuyenMonDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(ChiTietChuyenMonDTO dto) {
        mongoOperation.insert(dto);
        return true;
    }
    
    public boolean update(ChiTietChuyenMonDTO dto) {
        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("idGiaoVien", dto.getIdGiaoVien());
        update.set("idChiTietMonHoc", dto.getIdChiTietMonHoc());
        update.set("mota", dto.getMota());
        
        mongoOperation.findAndModify(query, update, ChiTietChuyenMonDTO.class);
        return true;
    }
          
    public boolean delete(ChiTietChuyenMonDTO dto) {
        mongoOperation.remove(dto);
        return true;
    }
    
    public List<ChiTietChuyenMonDTO> getAllList() {
        return mongoOperation.findAll(ChiTietChuyenMonDTO.class);
    }
}
