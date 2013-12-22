/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
     private String imageUrl;
     @DBRef
     private List<CatalogNewsDTO> catalogs;

     public List<CatalogNewsDTO> getCatalogs() {
          return catalogs;
     }

     public void setCatalogs(List<CatalogNewsDTO> catalogs) {
          this.catalogs = catalogs;
     }

     public NewsDTO() {
          catalogs = new ArrayList<>();
     }

     public NewsDTO(String title, String author, Date date, String content, String url, List<CatalogNewsDTO> catalogs) {
          this.title = title;
          this.author = author;
          this.date = date;
          this.content = content;
          this.url = url;
          this.catalogs = catalogs;
     }

     public NewsDTO(String title, String author, Date date, String content, String url, CatalogNewsDTO... catalogs) {
          this.title = title;
          this.author = author;
          this.date = date;
          this.content = content;
          this.url = url;
          this.catalogs = Arrays.asList(catalogs);
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

     public String getImageUrl() {
          return imageUrl;
     }

     public void setImageUrl(String imageUrl) {
          this.imageUrl = imageUrl;
     }
}
