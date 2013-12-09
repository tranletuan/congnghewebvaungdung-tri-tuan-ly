/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.Authority;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class RoleDTO {

     @Id
     private String id;
     private String rolename;
     private String roleDescription;
     
     public RoleDTO(String rolename, String roleDescription) {
          this.rolename = rolename;
          this.roleDescription = roleDescription;
     }

     public String getRolename() {
          return rolename;
     }

     public void setRolename(String rolename) {
          this.rolename = rolename;
     }

     public String getRoleDescription() {
          return roleDescription;
     }

     public void setRoleDescription(String roleDescription) {
          this.roleDescription = roleDescription;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }
}
