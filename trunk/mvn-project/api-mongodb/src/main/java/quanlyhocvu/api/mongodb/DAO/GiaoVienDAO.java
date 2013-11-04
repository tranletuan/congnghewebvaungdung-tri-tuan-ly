/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
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
            Query query = Query.query(Criteria.where("ID").is(teacher.getID()));
            Update update = new Update();
            update.set("HoTen", teacher.getHoTen());
            update.set("GioiTinh", teacher.getGioiTinh());
            update.set("NgaySinh", teacher.getNgaySinh());
            update.set("DiaChi", teacher.getDiaChi());
            update.set("MaGiaoVien", teacher.getTeacherId());
            
            mongoOperations.findAndModify(query, update,  GiaoVienDTO.class);
            return true;           
        } catch (Exception ex) {
            logger.error("TeacherDAO - insertTeacher " + ex.getMessage());
            return false;
        }
    }
}
