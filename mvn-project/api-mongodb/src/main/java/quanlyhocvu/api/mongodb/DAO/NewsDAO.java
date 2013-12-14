/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
      *
      * @param news
      */
     public void insertNews(NewsDTO news) {
          mongoOperation.save(news);
     }

     /**
      * get news instance by id
      *
      * @param id
      * @return
      */
     public NewsDTO getNewsById(String id) {
          Query query = Query.query(Criteria.where("id").is(id));
          return mongoOperation.findOne(query, NewsDTO.class);
     }

     /**
      * get all news
      *
      * @return
      */
     public List<NewsDTO> getAllNews() {
          return mongoOperation.findAll(NewsDTO.class);
     }

     /**
      * delete news by id
      *
      * @param newsId
      */
     public void deleteNewsById(String newsId) {
          Query query = Query.query(Criteria.where("id").is(newsId));
          mongoOperation.remove(query, NewsDTO.class);
     }

     /**
      * get pagination of news by catalogId
      * @param catalogId
      * @param limit
      * @param offset
      * @return 
      */
     public List<NewsDTO> getNewsByCatalogIdAndPate(String catalogId, int limit, int offset) {
          Query query = Query.query(Criteria.where("catalogs.$id").is(new ObjectId(catalogId)));
          query.limit(limit);
          query.skip(offset);
          query.with(new Sort(Sort.Direction.DESC, "date"));
          return mongoOperation.find(query, NewsDTO.class);
     }

     /**
      * get all data pagination
      * @param limit
      * @param offset
      * @return 
      */
     public List<NewsDTO> getAllNewsByPage(int limit, int offset) {
          Query query = Query.query(Criteria.where("id").exists(true));
          query.limit(limit);
          query.skip(offset);
          query.with(new Sort(Sort.Direction.DESC, "date"));
          return mongoOperation.find(query, NewsDTO.class);
     }

     /**
      * update news
      * @param news 
      */
     public void update(NewsDTO news) {
         mongoOperation.remove(news);
         mongoOperation.save(news);
     }
}
