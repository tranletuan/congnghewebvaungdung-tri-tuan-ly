/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.staff.CatalogNewsDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class CatalogDAO {

     @Autowired
     MongoOperations mongoOperation;

     /**
      * get all catalog
      * @return 
      */
     public List<CatalogNewsDTO> getAllCatalog() {
          return mongoOperation.findAll(CatalogNewsDTO.class);
     }
     
     /**
      * 
      * @param id
      * @return 
      */
     public CatalogNewsDTO getCatalogById(String id) {
          Query query = Query.query(Criteria.where("id").is(id));
          return mongoOperation.findOne(query, CatalogNewsDTO.class);
     }
     
     /**
      * insert catalog
      * @param dto 
      */
     public void insertCatalog(CatalogNewsDTO dto) {
          mongoOperation.insert(dto);
     }
     
     /**
      * update catalog info
      * @param dto 
      */
     public void udpateCatalog(CatalogNewsDTO dto) {
          Query query = Query.query(Criteria.where("id").is(dto.getId()));
          Update update = new Update();
          if (!"".equals(dto.getInfo())) {
               update.set("info", dto.getInfo());
          }
          if (!"".equals(dto.getName())) {
               update.set("name", dto.getName());
          }
          mongoOperation.findAndModify(query, update, CatalogNewsDTO.class);
     }

     /**
      * 
      * @param catalogId 
      */
     public void generateCatalogCounter(String catalogId) {
          Query query = Query.query(Criteria.where("catalogs.$id").is(new ObjectId(catalogId)));
          int count = mongoOperation.find(query, NewsDTO.class).size();
          query = Query.query(Criteria.where("id").is(catalogId));
          Update update = new Update().set("counter", count);
          mongoOperation.findAndModify(query, update, CatalogNewsDTO.class);
     }
     
     /**
      * 
      */
     public void generateAllCatalogCounter() {
          List<CatalogNewsDTO> catalogs = mongoOperation.findAll(CatalogNewsDTO.class);
          for(CatalogNewsDTO catalog : catalogs) {
               generateCatalogCounter(catalog.getId());
          }
     }
     
     /**
      * 
      * @param catalogId 
      */
     public void deleteCatalogById(String catalogId) {
          Query query = Query.query(Criteria.where("id").is(catalogId));
          mongoOperation.remove(query, CatalogNewsDTO.class);
     }
     
     /**
      * 
      * @param catalog 
      */
     public void updateCatalog(CatalogNewsDTO catalog) {
          Query query = Query.query(Criteria.where("id").is(catalog.getId()));
          Update update = new Update();
          if (!"".equals(catalog.getName())) {
               update.set("name", catalog.getName());
          }
          if (!"".equals(catalog.getInfo())) {
               update.set("info", catalog.getInfo());
          }
          
          mongoOperation.findAndModify(query, update, CatalogNewsDTO.class);
     }
}
