package quanlyhocvu.api.mongodb.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.AddressDAO;
import quanlyhocvu.api.mongodb.DAO.GiaoVienDAO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;

@Repository
public class MongoService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AddressDAO addressDAO;
    
    @Autowired 
    private GiaoVienDAO giaovienDAO;

    
    public boolean insertGiaoVien(GiaoVienDTO dto) {
        return giaovienDAO.insertTeacher(dto);
    }
    
    public List<GiaoVienDTO> getAllGiaoVien() {
        return giaovienDAO.getAllGiaoVien();
    }
    //<editor-fold defaultstate="collapsed" desc="Get Set DAO">
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }
    
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
    
      public GiaoVienDAO getGiaoVienDAO() {
        return giaovienDAO;
    }

    public void setGiaoVienDAO(GiaoVienDAO GiaoVienDAO) {
        this.giaovienDAO = GiaoVienDAO;
    }
    
    //</editor-fold>
}
