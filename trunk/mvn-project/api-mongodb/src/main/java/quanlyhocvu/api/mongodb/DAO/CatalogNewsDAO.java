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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.staff.CatalogNewsDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class CatalogNewsDAO {

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

//     public List<NewsDTO> getNewsPageByCatalog(String catalogId, int limit, int offset) {
//          Query query = Query.query(Criteria.where("id").is(catalogId));
//          query.limit(limit);
//          query.skip(offset);
//          return mongoOperation.find(query, )
//     }
}
