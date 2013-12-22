/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.staff;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
public class NewsCounterDTO {
     private int counter;
     @DBRef 
     private NewsDTO news;

     public int getCounter() {
          return counter;
     }

     public void setCounter(int counter) {
          this.counter = counter;
     }

     public NewsDTO getNews() {
          return news;
     }

     public void setNews(NewsDTO news) {
          this.news = news;
     }
}
