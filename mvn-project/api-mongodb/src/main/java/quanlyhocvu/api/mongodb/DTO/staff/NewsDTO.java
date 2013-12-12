/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class NewsDTO {
     @Id 
     private String id;
     private String title;
     private String author;
     private Date date;
     private String content;
     private String url;
     private List<String> catalogIds;

     
     public NewsDTO() {}
     
     public NewsDTO(String title, String author, Date date, String content, String url, List<String> catalogIds) {
          this.title = title;
          this.author = author;
          this.date = date;
          this.content = content;
          this.url = url;
          this.catalogIds = catalogIds;
     }
     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public String getAuthor() {
          return author;
     }

     public void setAuthor(String author) {
          this.author = author;
     }

     public Date getDate() {
          return date;
     }

     public void setDate(Date date) {
          this.date = date;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {
          this.content = content;
     }

     public String getUrl() {
          return url;
     }

     public void setUrl(String url) {
          this.url = url;
     }

     public List<String> getCatalogIds() {
          return catalogIds;
     }

     public void setCatalogIds(List<String> catalogIds) {
          this.catalogIds = catalogIds;
     }
}
