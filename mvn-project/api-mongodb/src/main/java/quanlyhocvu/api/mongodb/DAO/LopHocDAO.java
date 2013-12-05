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
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;

/**
 *
 * @author HuuTri
 */
@Repository
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
        
        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("idgiaoVien", dto.getidGiaoVien());
        update.set("idkhoiLop", dto.getidKhoiLop());
        update.set("idnamHoc", dto.getidnamHoc());
        update.set("moTa", dto.getmoTa());
        update.set("listIDHocSinh", dto.getLisidHocSinh());
        
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
        
        Query query = Query.query(Criteria.where("id").is(classId));
        Update update = new Update();
        update.push("listIDHocSinh", studentIds);
        
        mongoOperation.findAndModify(query, update, LopHocDTO.class);
        
        return res;
    }
    
    public boolean deleteHocSinh(String classId, String studentIds) {
        boolean res = true;
        
        Query query = Query.query(Criteria.where("id").is(classId));
        Update update = new Update();
        update.pull("listIDHocSinh", studentIds);
        
        mongoOperation.findAndModify(query, update, LopHocDTO.class);
        
        return res;
    }
    
    public List<LopHocDTO> getListBynamHoc(String id) {
        Query query = Query.query(Criteria.where("idnamHoc").is(id));
        return mongoOperation.find(query, LopHocDTO.class);
    }
    
    public List<LopHocDTO> getAllList() {
        return mongoOperation.findAll(LopHocDTO.class);
    }
}
