/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.staff;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class CoverImageDTO {
     private int id;
     private String link;

     public CoverImageDTO() {}
     public CoverImageDTO(String link) {
          this.link = link;
     }
     
     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getLink() {
          return link;
     }

     public void setLink(String link) {
          this.link = link;
     }
     
}
