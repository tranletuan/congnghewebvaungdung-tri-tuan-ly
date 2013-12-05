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
    MongoOperations mongoOperations;

    public List<DiemDTO> getAllDiem() {
        return mongoOperations.findAll(DiemDTO.class);
    }

    public boolean insertDiem(DiemDTO diem) {
        try {
            mongoOperations.insert(diem);
            return true;

        } catch (Exception ex) {
            logger.error("DiemDAO - insertDiem: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean updateDiem(DiemDTO diem) {
        try {
            Query query = Query.query(Criteria.where("id").is(diem.getid()));
            Update update = new Update();
            update.set("MaDiem" , diem.getMaDiem());
            update.set("magiaoVien", diem.getmagiaoVien());
            update.set("maHocSinh", diem.getmaHocSinh());
            update.set("MakhoiLop", diem.getMakhoiLop());
            update.set("MaMonHoc", diem.getMaMonHoc());
            update.set("ManamHoc", diem.getManamHoc());
            update.set("DiemSo", diem.getDiemSo());
            
            mongoOperations.findAndModify(query, update, DiemDTO.class);
            return true;
        }
        catch (Exception ex) {
            logger.error("DiemDAO - updateDiem " + ex.getMessage());
            return false;
        }
    }
}
