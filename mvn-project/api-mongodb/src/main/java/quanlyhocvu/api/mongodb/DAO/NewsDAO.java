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
import quanlyhocvu.api.mongodb.DTO.staff.NewsDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class NewsDAO {
     @Autowired
     MongoOperations mongoOperation;
     
     /**
      * insert new news
      * @param news 
      */
     public void insertNews(NewsDTO news) {
          mongoOperation.save(news);
     }
     
     /**
      * get news instance by id
      * @param id
      * @return 
      */
     public NewsDTO getNewsById(String id) {
          Query query = Query.query(Criteria.where("id").is(id));
          return mongoOperation.findOne(query, NewsDTO.class);
     }
     
     /**
      * get all news 
      * @return 
      */
     public List<NewsDTO> getAllNews() {
          return mongoOperation.findAll(NewsDTO.class);
     }
}
