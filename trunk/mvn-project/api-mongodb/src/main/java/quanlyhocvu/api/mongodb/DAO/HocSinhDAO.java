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
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;

/**
 *
 * @author linhly
 */
@Repository
public class HocSinhDAO {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoOperations mongoOperations;
    
    @SuppressWarnings("empty-statement")
    public long Count() {
        return mongoOperations.count(Query.query(Criteria.where("ID").lt("500000000")), HocSinhDTO.class);
    }
    
    public List<HocSinhDTO> getAllHocSinh() {
        return mongoOperations.findAll(HocSinhDTO.class);
    }
    
    public List<HocSinhDTO> getHocSinhByID(String id) {
        Query query = Query.query(Criteria.where("ID").exists(true));
        return mongoOperations.find(query, HocSinhDTO.class);
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
            Query query = Query.query(Criteria.where("id").is(student.getid()));
            Update update = new Update();
            update.set("hoTen", student.gethoTen());
            update.set("gioiTinh", student.getgioiTinh());
            update.set("ngaySinh", student.getngaySinh());
            update.set("diaChi", student.getdiaChi());
            update.set("maHocSinh", student.getmaHocSinh());
            update.set("ngayNhapHoc", student.getngayNhapHoc());
            update.set("ngayNghiHoc", student.getngayNghiHoc());   
            update.set("maLopHoc", student.getMaLopHoc());
            
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
    
    public HocSinhDTO getBymaHocSinh(String maHocSinh){
        
        Query query = Query.query(Criteria.where("maHocSinh").is(maHocSinh));
        HocSinhDTO obj = mongoOperations.findOne(query, HocSinhDTO.class);
        return obj;
    }
    
    public List<HocSinhDTO> getByLopHoc(String maLopHoc){
        Query query = Query.query(Criteria.where("maLopHoc").is(maLopHoc));
        return mongoOperations.find(query, HocSinhDTO.class);
        
    }
    
    public HocSinhDTO getHocSinhById(String idHocSinh) {
        Query query = Query.query(Criteria.where("id").is(idHocSinh));
        return mongoOperations.findOne(query, HocSinhDTO.class);
    }
    
    public List<HocSinhDTO> getHocSinhChuaXepLop(){
        Query query = Query.query(Criteria.where("maLopHoc").is(null));
        return mongoOperations.find(query, HocSinhDTO.class);
    }
    
    public List<HocSinhDTO> getHocSinhChuaXepLopTheoKhoiLop(String khoiLopId){
        Query query = Query.query(Criteria.where("maLopHoc").is(null).and("khoiLopHienTai.$id").is(new ObjectId(khoiLopId)));
        return mongoOperations.find(query, HocSinhDTO.class);
    }
    
}
