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
 * @author linhly
 */
@Repository
public class HocSinhDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperations;
    
    public List<HocSinhDTO> getAllHocSinh() {
        return mongoOperations.findAll(HocSinhDTO.class);
    }
    
    public boolean insertStudent(HocSinhDTO student) {
        try {
            mongoOperations.insert(student);
            return true;
        } catch (Exception ex) {
            logger.error("HocSinhDAO - insertStudent " + ex.getMessage());
            return false;
        }
    }
    
    public boolean updateStudent(HocSinhDTO student) {
        try {
            Query query = Query.query(Criteria.where("ID").is(student.getID()));
            Update update = new Update();
            update.set("HoTen", student.getHoTen());
            update.set("GioiTinh", student.getGioiTinh());
            update.set("NgaySinh", student.getNgaySinh());
            update.set("DiaChi", student.getDiaChi());
            update.set("MaHocSinh", student.getMaHocSinh());
            update.set("NgayNhapHoc", student.getNgayNhapHoc());
            update.set("NgayNghiHoc", student.getNgayNghiHoc());            
            
            mongoOperations.findAndModify(query, update,  HocSinhDTO.class);
            return true;           
        } catch (Exception ex) {
            logger.error("HocSinhDAO - updateStudent " + ex.getMessage());
            return false;
        }
    }
    
    public boolean delete(HocSinhDTO dto) {
        boolean res = true;
        mongoOperations.remove(dto);        
        
        return res;
    }
    
    public HocSinhDTO getByMaHocSinh(String MaHocSinh){
        Query query = Query.query(Criteria.where("MaHocSinh").is(MaHocSinh));
        System.out.println(MaHocSinh);
        System.out.println(query.toString());
        HocSinhDTO obj = mongoOperations.findOne(query, HocSinhDTO.class);    
        System.out.print(obj);
        return obj;
    }
}
