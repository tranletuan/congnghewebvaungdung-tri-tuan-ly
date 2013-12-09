/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;

/**
 *
 * @author Tuan
 */
@Repository
public class PhanCongDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(PhanCongDTO dto) {
        mongoOperation.insert(dto);
        return true;
    }
    
    public boolean delete(PhanCongDTO dto) {
        mongoOperation.remove(dto);
        return true;
    }
    
    public boolean update(PhanCongDTO dto) {
        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("chiTietChuyenMon", dto.getChiTietChuyenMon().getid());
        update.set("lopHoc", dto.getLopHoc().getid());
        
        mongoOperation.findAndModify(query, update, PhanCongDTO.class);
        return true;
    }
    
    public List<PhanCongDTO> getAllList() {
        return mongoOperation.findAll(PhanCongDTO.class);
    }
    
    public List<PhanCongDTO> getListBy(String idChiTietChuyenMon) {
        Query query = Query.query(Criteria.where("chiTietChuyenMon.$id").is(new ObjectId(idChiTietChuyenMon)));
        return mongoOperation.find(query, PhanCongDTO.class);
    }
    
    public PhanCongDTO getPhanCongById(String idPhanCong) {
        Query query = Query.query(Criteria.where("id").is(idPhanCong));
        return mongoOperation.findOne(query, PhanCongDTO.class);
    }
}
