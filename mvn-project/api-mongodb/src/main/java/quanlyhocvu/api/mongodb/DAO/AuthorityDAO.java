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
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.Authority.RoleDTO;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.utils.Authorities;

/**
 *
 * @author HuuTri
 */
@Repository
public class AuthorityDAO {

     @Autowired
     MongoOperations mongoOperation;
     
     /**
      * load user from username
      * @param username
      * @return UserDTO object
      */
     public UserDTO loadUserDTOByUserName(String username) {
          Query query = Query.query(Criteria.where("username").is(username));
          return mongoOperation.findOne(query, UserDTO.class);
     }

     /**
      * get RoleDTO by role name
      * @param rolename
      * @return 
      */
     public RoleDTO getRoleByRoleName(String rolename) {
          Query query = Query.query(Criteria.where("rolename").is(rolename));
          return mongoOperation.findOne(query, RoleDTO.class);
     }
     
     /**
      * get all system roles 
      * @return List<RoleDTO> object
      */
     public List<RoleDTO> getAllRoles() {
          return mongoOperation.findAll(RoleDTO.class);
     }
     
     /**
      * get all user 
      * @return 
      */
     public List<UserDTO> getAllUser() {
          return mongoOperation.findAll(UserDTO.class);
     }
     
     /**
      * insert new user to database
      * @param user 
      */
     public void insertUser(UserDTO user) {
          mongoOperation.save(user);
     }
     
     /**
      * insert new role to database
      * @param role 
      */
     public void insertRole(RoleDTO role) {
          mongoOperation.save(role);
     }
     
     /**
      * insert user account from input data
      * @param username
      * @param password
      * @param userId of linking data
      * @param auths of Authorities
      */
     public void insertUserAccount(String username, String password, String userId, Authorities... auths) {
          UserDTO user = new UserDTO(username, password, userId);
          for (Authorities auth : auths) {
               user.getRoles().add(this.getRoleByRoleName(auth.toString()));
          }
          mongoOperation.save(user);
     }
}


