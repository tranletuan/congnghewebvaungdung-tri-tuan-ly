/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.staff.CoverImageDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsCounterDTO;
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
      *
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
      *
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
      *
      * @param news
      */
     public void update(NewsDTO news) {
          mongoOperation.remove(news);
          mongoOperation.save(news);
     }

     /**
      *
      * @param link
      */
     public void insertCover(String link, int id) {
          CoverImageDTO image = new CoverImageDTO(link);
          image.setId(id);
          mongoOperation.save(image);
     }

     /**
      *
      * @param id
      */
     public void deleteCorver(int id) {
          mongoOperation.remove(
                  Query.query(Criteria.where("id").is(id)),
                  CoverImageDTO.class
          );
     }

     /**
      *
      * @return
      */
     public List<CoverImageDTO> getAllCover() {
          return mongoOperation.findAll(CoverImageDTO.class);
     }

     /**
      *
      */
     public void removeAllCover() {
          Query query = Query.query(Criteria.where("id").exists(true));
          mongoOperation.remove(query, CoverImageDTO.class);
     }

     /**
      *
      * @param newsId
      * @return
      */
     public String getNewsContentByNewsId(String newsId) {
          Query query = Query.query(Criteria.where("id").is(newsId));
          NewsDTO news = mongoOperation.findOne(query, NewsDTO.class);
          return news.getContent();
     }

     /**
      *
      * @param newsId
      */
     public void increaseCounterNews(String newsId) {
          Query query = Query.query(Criteria.where("news.$id").is(new ObjectId(newsId)));
          Update update = new Update().inc("counter", 1);
          NewsCounterDTO news = mongoOperation.findAndModify(query, update, NewsCounterDTO.class);
          if (news == null) {
               news = new NewsCounterDTO();
               news.setCounter(1);
               news.setNews(this.getNewsById(newsId));
               mongoOperation.insert(news);
          }
     }

     public List<NewsCounterDTO> getHotNews(int limit) {
          Query query = Query.query(Criteria.where("counter").exists(true));
          query.limit(limit);
          query.with(new Sort(Sort.Direction.DESC, "counter"));
          return mongoOperation.find(query, NewsCounterDTO.class);
     }

     public List<NewsCounterDTO> getNewsByCatalogId(String catalogId) {
          Query query = Query.query(Criteria.where("catalogs.$id").is(new ObjectId(catalogId)));
          List<NewsDTO> listNews = mongoOperation.find(query, NewsDTO.class);
          List<ObjectId> ids = new ArrayList<ObjectId>();
          for(NewsDTO news : listNews) 
               ids.add(new ObjectId(news.getId()));
          query = Query.query(Criteria.where("news.$id").in(ids));
          return mongoOperation.find(query, NewsCounterDTO.class);
     }

}
