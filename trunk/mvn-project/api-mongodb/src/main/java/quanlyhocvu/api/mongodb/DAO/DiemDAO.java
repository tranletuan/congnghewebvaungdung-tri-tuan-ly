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
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;

/**
 *
 * @author Tuan
 */
@Repository
public class DiemDAO {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoOperations mongoOperation;

    public boolean insert(DiemDTO dto) {
        mongoOperation.insert(dto);
        return true;
    }

    public boolean delete(DiemDTO dto) {
        mongoOperation.remove(dto);
        return true;
    }

    public boolean updateDiem(DiemDTO dto) {

        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("phanCong", dto.getPhanCong().getid());
        update.set("hocSinh", dto.getHocSinh().getid());
        update.set("diemSo", dto.getDiemSo());

        mongoOperation.findAndModify(query, update, DiemDTO.class);
        return true;
    }

    public List<DiemDTO> getDiemByMaHs(String id){
        Query query = Query.query(Criteria.where("idHocSinh").is(id));
        return mongoOperation.find(query, DiemDTO.class);
    }
    public List<DiemDTO> getAllDiem() {
        return mongoOperation.findAll(DiemDTO.class);
    }
}