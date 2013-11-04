package quanlyhocvu.api.mongodb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.AddressDAO;

@Repository
public class MongoService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AddressDAO addressDAO;
    
    
    //<editor-fold defaultstate="collapsed" desc="Get Set DAO">
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }
    
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
    //</editor-fold>
}
