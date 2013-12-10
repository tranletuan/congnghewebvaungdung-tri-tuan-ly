/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietDiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;

/**
 *
 * @author Tuan
 */
@Repository
public class ChiTietDiemDAO {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoOperations mongoOperation;

    public boolean insert(ChiTietDiemDTO dto) {
        mongoOperation.insert(dto);
        return true;
    }

    public boolean delete(ChiTietDiemDTO dto) {
        mongoOperation.remove(dto);
        return true;
    }

    public boolean update(ChiTietDiemDTO dto) {
        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();

        update.set("diem", dto.getDiem());
        update.set("ktmieng_1", dto.getKtmieng_1());
        update.set("ktmieng_2", dto.getKtmieng_2());
        update.set("ktmieng_3", dto.getKtmieng_3());

        update.set("kt15_1", dto.getKt15_1());
        update.set("kt15_2", dto.getKt15_2());
        update.set("kt15_3", dto.getKt15_3());

        update.set("kt1tiet_1", dto.getKt1tiet_1());
        update.set("kt1tiet_2", dto.getKt1tiet_2());
        
        update.set("diemGiuaKy", dto.getDiemGiuaKy());
        update.set("diemCuoiKy", dto.getDiemCuoiKy());
        update.set("diemTB", dto.getDiemTB());

        return true;
    }

    public ChiTietDiemDTO getChiTietDiemByIdDiem(DiemDTO diem) {
        Query query = Query.query(Criteria.where("diem.$id").is(new ObjectId(diem.getid())));
        ChiTietDiemDTO obj = mongoOperation.findOne(query, ChiTietDiemDTO.class);
        if(obj == null )
        {
            ChiTietDiemDTO newObj = new ChiTietDiemDTO();
            newObj.setDiem(diem);
            mongoOperation.insert(newObj);
            obj = newObj;
        }
        
        return obj;
    }
    
    
}
