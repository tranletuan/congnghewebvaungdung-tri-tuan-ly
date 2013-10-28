/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.address.AddressDTO;
import quanlyhocvu.api.mongodb.DTO.address.DistrictDTO;
import quanlyhocvu.api.mongodb.DTO.address.ProvinceDTO;
import quanlyhocvu.api.mongodb.DTO.address.WardDTO;
import quanlyhocvu.api.mongodb.DTO.base.DTOConstant;

/**
 *
 * @author HuuTri
 */
@Repository
public class AddressDAO {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperations;
    
public AddressDAO() {}    
    
//<editor-fold defaultstate="collapsed" desc="ProvinceDAO">
    public boolean insertProvince(ProvinceDTO province) {
        try {
            mongoOperations.insert(province);
        } catch (Exception ex) {
            logger.debug("AddressDAO: insertProvince error"  + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean updateProvince(ProvinceDTO province) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(province.getId()));
            
            Update update = new Update();
            update.set(DTOConstant.ProvinceName, province.getProvinceName());
            
            mongoOperations.findAndModify(query, update, ProvinceDTO.class);
        } catch (Exception ex) {
            logger.debug("AddressDAO: updateProvince error"  + ex.getMessage());
            return true;
        }
        return false;
    }
    
    public List<ProvinceDTO> getAllProvince() {
        return mongoOperations.findAll(ProvinceDTO.class);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="DistrictDAO">
    public List<DistrictDTO> getDistrictByProvinceId(String provinceId) {
        Query query = Query.query(Criteria.where(DTOConstant.ProvinceId).is(provinceId));
        return mongoOperations.find(query, DistrictDTO.class);
    }
    
    public boolean insertDistrict(DistrictDTO district) {
        try {
            mongoOperations.insert(district);
        } catch (Exception ex) {
            logger.debug("AddressDAO: insertDistrict error"  + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean updateDistrict(DistrictDTO district) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(district.getId()));
            
            Update update = new Update();
            update.set(DTOConstant.DistrictName, district.getDistrictName());
            update.set(DTOConstant.ProvinceId, district.getProvinceId());
            
            mongoOperations.findAndModify(query, update, DistrictDTO.class);
        } catch (Exception ex) {
            logger.debug("AddressDAO: updateDistrict error"  + ex.getMessage());
            return false;
        }
        return true;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="WardDAO">
    public List<WardDTO> getWardByDistrictId(String districtId) {
        Query query = Query.query(Criteria.where(DTOConstant.DistrictId).is(districtId));
        return mongoOperations.find(query, WardDTO.class);
    }
    
    public boolean insertWard(WardDTO ward) {
        try {
            mongoOperations.insert(ward);
        } catch (Exception ex) {
            logger.debug("AddressDAO: insertWard error" + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean updateWard(WardDTO ward) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(ward.getId()));
            
            Update update = new Update();
            update.set(DTOConstant.WardName, ward.getWardName());
            update.set(DTOConstant.DistrictName, ward.getDistrictId());
            
            mongoOperations.findAndModify(query, update, WardDTO.class);
        } catch (Exception ex) {
            logger.debug("AddressDAO: updateWard error" + ex.getMessage());
            return false;
        }
        return true;
    }
//</editor-fold>
    
    public List<AddressDTO> getAddressByWardId(String wardId) {
        Query query = Query.query(Criteria.where(DTOConstant.DistrictId).is(wardId));
        return mongoOperations.find(query, AddressDTO.class);
    }
    
    public boolean insertAddress(AddressDTO address) {
        try {
            mongoOperations.insert(address);
        } catch (Exception ex) {
            logger.debug("AddressDAO: insertAddress error" + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean updateAddress(AddressDTO address) {
        try {
            Query query = Query.query(Criteria.where(DTOConstant.Id).is(address.getId()));
            
            Update update = new Update();
            update.set(DTOConstant.AddressName, address.getAddressName());
            update.set(DTOConstant.DistrictName, address.getWardId());
            
            mongoOperations.findAndModify(query, update, AddressDTO.class);
        } catch (Exception ex) {
            logger.debug("AddressDAO: updateAddress error" + ex.getMessage());
            return false;
        }
        return true;
    }
}
