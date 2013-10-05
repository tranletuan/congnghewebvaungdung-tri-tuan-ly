package quanlyhocvu.api.mongodb.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.AddressDAO;
import quanlyhocvu.api.mongodb.DTO.address.AddressDTO;
import quanlyhocvu.api.mongodb.DTO.address.DistrictDTO;
import quanlyhocvu.api.mongodb.DTO.address.ProvinceDTO;
import quanlyhocvu.api.mongodb.DTO.address.WardDTO;

@Repository
public class MongoService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AddressDAO addressDAO;
    
    public boolean insertProvince(ProvinceDTO province) {
        return addressDAO.insertProvince(province);
    }
    
    public List<ProvinceDTO> getAllProvince() {
        return addressDAO.getAllProvince();
    }

    public boolean insertDistrict(DistrictDTO district) {
        return addressDAO.insertDistrict(district);
    }
    
    public List<DistrictDTO> getDistrictByProvinceId(String provinceId) {
        return addressDAO.getDistrictByProvinceId(provinceId);
    }
    
    public boolean insertWard(WardDTO ward) {
        return addressDAO.insertWard(ward);
    }
    
    public List<WardDTO> getWardByDistrictId(String districtId) {
        return addressDAO.getWardByDistrictId(districtId);
    }
    
    public boolean insertAddress(AddressDTO address) {
        return addressDAO.insertAddress(address);
    }
    
    public List<AddressDTO> getAddressByWardId(String wardId) {
        return addressDAO.getAddressByWardId(wardId);
    }
    //<editor-fold defaultstate="collapsed" desc="Get Set DAO">
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }
    
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
    //</editor-fold>
}
