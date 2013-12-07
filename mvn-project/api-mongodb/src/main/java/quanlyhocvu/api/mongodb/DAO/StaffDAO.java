/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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
      * @return 
      */
     public List<StaffDTO> getAllStaff() {
          return mongoOperation.findAll(StaffDTO.class);
     }
     
     /**
      * insert staff
      * @param staff
      */
     public void insertStaff(StaffDTO staff) {
          mongoOperation.save(staff);
     }
}
