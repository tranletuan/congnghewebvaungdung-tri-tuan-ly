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
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class GiaoVienDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperations;
    
    public List<GiaoVienDTO> getAllgiaoVien() {
        return mongoOperations.findAll(GiaoVienDTO.class);
    }
    
    public boolean insertTeacher(GiaoVienDTO teacher) {
        try {
            mongoOperations.insert(teacher);
            return true;
        } catch (Exception ex) {
            logger.error("TeacherDAO - insertTeacher " + ex.getMessage());
            return false;
        }
    }
    
    public boolean updateTeacher(GiaoVienDTO teacher) {
        try {
            Query query = Query.query(Criteria.where("id").is(teacher.getid()));
            Update update = new Update();
            update.set("hoTen", teacher.gethoTen());
            update.set("gioiTinh", teacher.getgioiTinh());
            update.set("ngaySinh", teacher.getngaySinh());
            update.set("diaChi", teacher.getdiaChi());
            update.set("maGiaoVien", teacher.getmaGiaoVien());
            update.set("ngayVaoLam", teacher.getngayVaoLam());
            update.set("ngayNghiViec", teacher.getngayNghiViec());
            
            mongoOperations.findAndModify(query, update,  GiaoVienDTO.class);
            return true;           
        } catch (Exception ex) {
            logger.error("TeacherDAO - updateTeacher " + ex.getMessage());
            return false;
        }
    }
    
    public GiaoVienDTO getBymaGiaoVien(String maGiaoVien){
        Query query = Query.query(Criteria.where("maGiaoVien").is(maGiaoVien));
        GiaoVienDTO obj = mongoOperations.find(query, GiaoVienDTO.class).get(0);    
        return obj;
    }
    
    public GiaoVienDTO getById(String id){
        Query query = Query.query(Criteria.where("id").is(id));
        GiaoVienDTO obj = mongoOperations.findOne(query, GiaoVienDTO.class);
        return obj;
    }
        
}
