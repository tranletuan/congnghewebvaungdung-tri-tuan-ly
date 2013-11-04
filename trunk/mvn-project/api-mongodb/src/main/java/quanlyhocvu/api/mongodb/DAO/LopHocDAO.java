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
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;

/**
 *
 * @author HuuTri
 */
public class LopHocDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperation;
    
    public boolean insert(LopHocDTO dto) {
        boolean res = true;
        
        mongoOperation.insert(dto);
        
        return res;
    }
    
    public boolean update(LopHocDTO dto) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where("ID").is(dto.getID()));
        Update update = new Update();
        update.set("IDGiaoVien", dto.getIDGiaoVien());
        update.set("IDKhoiLop", dto.getIDKhoiLop());
        update.set("IDNamHoc", dto.getIDNamHoc());
        update.set("MoTa", dto.getMoTa());
        update.set("ListIDHocSinh", dto.getLisIDHocSinh());
        
        mongoOperation.findAndModify(query, update, LopHocDTO.class);
        
        return res;
    }
    
    public boolean delete(LopHocDTO dto) {
        boolean res = true;
        
        mongoOperation.remove(dto);
        
        return res;
    }
    
    public boolean insertHocSinh(String classId, String studentIds) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where("ID").is(classId));
        Update update = new Update();
        update.push("ListIDHocSinh", studentIds);
        
        mongoOperation.findAndModify(query, update, LopHocDTO.class);
        
        return res;
    }
    
    public boolean deleteHocSinh(String classId, String studentIds) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where("ID").is(classId));
        Update update = new Update();
        update.pull("ListIDHocSinh", studentIds);
        
        mongoOperation.findAndModify(query, update, LopHocDTO.class);
        
        return res;
    }
    
    public List<LopHocDTO> getListByNamHoc(String id) {
        Query query = Query.query(Criteria.where("IDNamHoc").is(id));
        return mongoOperation.find(query, LopHocDTO.class);
    }
}
