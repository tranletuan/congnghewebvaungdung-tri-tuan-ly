/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.DTO.staff.CommentDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class CommentDAO {
     @Autowired
     MongoOperations mongoOperation;

     public void insertComment(CommentDTO comment) {
          mongoOperation.save(comment);
     }

     public List<CommentDTO> getCommentsByUser(UserDTO user) {
          Query query = Query.query(Criteria.where("user.$userId").is(new ObjectId(user.getUserId())));
          return mongoOperation.find(query, CommentDTO.class);
     }

     public List<CommentDTO> getAll() {
          return mongoOperation.findAll(CommentDTO.class);
     }
     
}
