package quanlyhocvu.api.mongodb.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.AddressDAO;
import quanlyhocvu.api.mongodb.DAO.GiaoVienDAO;
import quanlyhocvu.api.mongodb.DAO.MonHocDAO;
import quanlyhocvu.api.mongodb.DAO.NamHocDAO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;

@Repository
public class MongoService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AddressDAO addressDAO;
    
    @Autowired 
    private GiaoVienDAO giaovienDAO;
    
    @Autowired
    private NamHocDAO namhocDAO;
    
    @Autowired
    private MonHocDAO monhocDAO;

    
    public boolean insertGiaoVien(GiaoVienDTO dto) {
        return giaovienDAO.insertTeacher(dto);
    }
    
    public List<GiaoVienDTO> getAllGiaoVien() {
        return giaovienDAO.getAllGiaoVien();
    }
    //<editor-fold defaultstate="collapsed" desc="Get Set DAO">
    /**
     * @return the monhocDAO
     */
    public MonHocDAO getMonhocDAO() {
        return monhocDAO;
    }

    /**
     * @param monhocDAO the monhocDAO to set
     */
    public void setMonhocDAO(MonHocDAO monhocDAO) {
        this.monhocDAO = monhocDAO;
    }
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
    
    
    /**************************************************
     * function for school-year
     * @author: LyVV
     *************************************************/
    /**
     * this function is used to return the list of school-year
     * @return 
     */
    public List<NamHocDTO> getAllNamHoc(){
        return namhocDAO.getAllList();
    }
    /**
     * Insert new NamHoc into list
     * @param dto
     * @return 
     */
    public boolean insertNamHoc(NamHocDTO dto) {
        return namhocDAO.insert(dto);
    }
    
    
    /**************************************************
     * function for subject
     * @author: LyVV
     *************************************************/
    /**
     * this function is used to return the list of MonHoc
     * @return 
     */
    public List<MonHocDTO> getAllMonHoc(){
        return getMonhocDAO().getAllList();
    }
    /**
     * Insert new MonHoc into list
     * @param dto
     * @return 
     */
    public boolean insertMonHoc(MonHocDTO dto) {
        return getMonhocDAO().insert(dto);
    }

    
}
