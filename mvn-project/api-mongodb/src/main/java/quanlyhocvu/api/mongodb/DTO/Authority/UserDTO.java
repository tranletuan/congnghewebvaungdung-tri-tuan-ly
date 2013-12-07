/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.Authority;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class UserDTO {
     @Indexed(unique=true)
     private String username; 
     private boolean enabled;
     private boolean nonlocked;
     private String password;
     private String userId; // user use this account
     @DBRef
     private List<RoleDTO> roles;

     public UserDTO(String username, String password, String userId) {
          this.username = username;
          this.userId = userId;
          this.password = password;
          this.roles = new ArrayList<>();
          this.enabled = true;
          this.nonlocked = true;
     }
     
     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password, boolean md5) {
          this.password = password;
     }
     
     public void setPassword(String password) {
          this.password = password;
     }

     public String getUserId() {
          return userId;
     }

     public void setUserId(String userId) {
          this.userId = userId;
     }

     public List<RoleDTO> getRoles() {
          return roles;
     }

     public void setRoles(List<RoleDTO> roles) {
          this.roles = roles;
     }

     public boolean isEnabled() {
          return enabled;
     }

     public void setEnabled(boolean enabled) {
          this.enabled = enabled;
     }

     public boolean isNonlocked() {
          return nonlocked;
     }

     public void setNonlocked(boolean nonlocked) {
          this.nonlocked = nonlocked;
     }
}
