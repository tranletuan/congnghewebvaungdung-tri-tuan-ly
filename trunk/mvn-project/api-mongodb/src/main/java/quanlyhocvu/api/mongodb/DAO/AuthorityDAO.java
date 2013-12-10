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
import quanlyhocvu.api.mongodb.DTO.Authority.RoleDTO;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.StaffDTO;
import quanlyhocvu.api.mongodb.utils.Authorities;
import quanlyhocvu.api.mongodb.utils.MD5;

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
      *
      * @param username
      * @return UserDTO object
      */
     public UserDTO loadUserDTOByUserName(String username) {
          Query query = Query.query(Criteria.where("username").is(username));
          return mongoOperation.findOne(query, UserDTO.class);
     }

     /**
      * get RoleDTO by role name
      *
      * @param rolename
      * @return
      */
     public RoleDTO getRoleByRoleName(String rolename) {
          Query query = Query.query(Criteria.where("rolename").is(rolename));
          return mongoOperation.findOne(query, RoleDTO.class);
     }

     /**
      * get all system roles
      *
      * @return List<RoleDTO> object
      */
     public List<RoleDTO> getAllRoles() {
          return mongoOperation.findAll(RoleDTO.class);
     }

     /**
      * get all user
      *
      * @return
      */
     public List<UserDTO> getAllUser() {
          return mongoOperation.findAll(UserDTO.class);
     }

     /**
      * insert new user to database
      *
      * @param user
      */
     public void insertUser(UserDTO user) {
          mongoOperation.save(user);
     }

     /**
      * insert new role to database
      *
      * @param role
      */
     public void insertRole(RoleDTO role) {
          mongoOperation.save(role);
     }

     /**
      * insert user account from input data
      *
      * @param username
      * @param password
      * @param userId of linking data
      * @param auths of Authorities
      */
     public void insertUserAccount(String username, String password, String userId, Authorities... auths) {
          UserDTO user = new UserDTO(username, MD5.getMD5(password), userId);
          for (Authorities auth : auths) {
               user.getRoles().add(this.getRoleByRoleName(auth.toString()));
          }
          mongoOperation.save(user);
     }

     /**
      * generate all user
      */
     public void generateAllUser() {
          generateUserByTypeList(GiaoVienDTO.class, StaffDTO.class, HocSinhDTO.class);
     }

     /**
      *
      * @param classes
      */
     public void generateUserByTypeList(Class... classes) {
          for (Class cls : classes) {
               generateUserByType(cls);
          }
     }

     /**
      * generate password and user by class default: user = MaObj, pass = 'admin'
      *
      * @param cls
      */
     public void generateUserByType(Class cls) {
          if (cls == GiaoVienDTO.class) {
               List<GiaoVienDTO> giaoviens = mongoOperation.findAll(GiaoVienDTO.class);
               if (giaoviens != null) {
                    for (GiaoVienDTO giaovien : giaoviens) {
                         if (giaovien.getmaGiaoVien() != null) {
                              UserDTO user = new UserDTO(giaovien.getmaGiaoVien(), MD5.getMD5("admin"), giaovien.getid());
                              user.getRoles().add(mongoOperation.findOne(
                                      Query.query(Criteria.where("rolename").
                                              is(Authorities.TEACHER.toString())), RoleDTO.class));

                              try {
                                   this.insertUser(user);
                              } catch (Exception ex) {
                              }
                         }
                    }
               }
          } else if (cls == StaffDTO.class) {
               List<StaffDTO> staffs = mongoOperation.findAll(StaffDTO.class);
               if (staffs != null) {
                    for (StaffDTO staff : staffs) {
                         if (staff.getManhanvien() != null) {
                              UserDTO user = new UserDTO(staff.getManhanvien(), MD5.getMD5("admin"), staff.getid());
                              user.getRoles().add(mongoOperation.findOne(
                                      Query.query(Criteria.where("rolename").
                                              is(Authorities.STAFF.toString())), RoleDTO.class));
                              try {
                                   this.insertUser(user);
                              } catch (Exception ex) {
                              }
                         }
                    }
               }
          } else if (cls == HocSinhDTO.class) {
               List<HocSinhDTO> hocsinhs = mongoOperation.findAll(HocSinhDTO.class);
               if (hocsinhs != null) {
                    for (HocSinhDTO hocsinh : hocsinhs) {
                         if (hocsinh.getmaHocSinh() != null) {
                              UserDTO user = new UserDTO(hocsinh.getmaHocSinh(), MD5.getMD5("admin"), hocsinh.getid());
                              user.getRoles().add(mongoOperation.findOne(
                                      Query.query(Criteria.where("rolename").
                                              is(Authorities.STUDENT.toString())), RoleDTO.class));
                              try {
                                   this.insertUser(user);
                              } catch (Exception ex) {
                              }
                         }
                    }
               }
          }
     }

     /**
      * remove user by id
      *
      * @param userIds
      */
     public void removeUserByUserId(String... userIds) {
          for (String id : userIds) {
               mongoOperation.remove(Query.query(Criteria.where("userId").is(userIds)), UserDTO.class);
          }
     }

     /**
      * get list user by role name
      *
      * @param rolename
      * @return
      */
     public List<UserDTO> getUsersByRoleName(String rolename) {
          RoleDTO role = getRoleByRoleName(rolename);
          Query query = Query.query(Criteria.where("roles.$id").is(new ObjectId(role.getId())));
          return mongoOperation.find(query, UserDTO.class);
     }

}
