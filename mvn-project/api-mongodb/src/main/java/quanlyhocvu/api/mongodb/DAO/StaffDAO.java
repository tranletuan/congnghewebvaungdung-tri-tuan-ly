/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.staff.StaffDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class StaffDAO {

     @Autowired
     MongoOperations mongoOperation;

     /**
      * get all staff
      *
      * @return
      */
     public List<StaffDTO> getAllStaff() {
          return mongoOperation.findAll(StaffDTO.class);
     }

     /**
      * insert staff
      *
      * @param staff
      */
     public void insertStaff(StaffDTO staff) {
          mongoOperation.save(staff);
     }

     /**
      * get staff by id string
      *
      * @param manhanvien
      * @return
      */
     public StaffDTO getStaffById(String manhanvien) {
          Query query = Query.query(Criteria.where("manhanvien").is(manhanvien));
          return mongoOperation.findOne(query, StaffDTO.class);
     }

     /**
      * update staff current data
      *
      * @param staff
      * @return
      */
     public boolean updateStaff(StaffDTO staff) {
          try {
               Query query = Query.query(Criteria.where("id").is(staff.getid()));
               Update update = new Update();
               update.set("hoTen", staff.gethoTen());
               update.set("gioiTinh", staff.getgioiTinh());
               update.set("ngaySinh", staff.getngaySinh());
               update.set("diaChi", staff.getdiaChi());
               update.set("manhanvien", staff.getManhanvien());
               update.set("ngayVaoLam", staff.getngayVaoLam());
               update.set("ngayNghiViec", staff.getngayNghiViec());

               mongoOperation.findAndModify(query, update, StaffDTO.class);
               return true;
          } catch (Exception ex) {
               return false;
          }
     }
}
