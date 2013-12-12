/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class CatalogNewsDTO {
     @Id
     private String id;
     private String name;
     private String info;
     
     public CatalogNewsDTO(String name, String info) {
          this.name = name;
          this.info = info;
     }
     
     public CatalogNewsDTO() {
     }
     
     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }
     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getInfo() {
          return info;
     }

     public void setInfo(String info) {
          this.info = info;
     }
}
