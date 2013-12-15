/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;

/**
 *
 * @author HuuTri
 */
@Document
public class CommentDTO {
     @Id 
     private String id;
     private String content;
     private Date date;
     @DBRef 
     private UserDTO user;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {
          this.content = content;
     }

     public Date getDate() {
          return date;
     }

     public void setDate(Date date) {
          this.date = date;
     }

     public UserDTO getUser() {
          return user;
     }

     public void setUser(UserDTO user) {
          this.user = user;
     }
}
