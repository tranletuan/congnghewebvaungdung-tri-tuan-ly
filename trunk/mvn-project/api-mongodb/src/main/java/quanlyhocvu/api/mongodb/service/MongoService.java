package quanlyhocvu.api.mongodb.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.AddressDAO;
import quanlyhocvu.api.mongodb.DAO.GiaoVienDAO;
import quanlyhocvu.api.mongodb.DAO.HocSinhDAO;
import quanlyhocvu.api.mongodb.DAO.KhoiLopDAO;
import quanlyhocvu.api.mongodb.DAO.LopHocDAO;
import quanlyhocvu.api.mongodb.DAO.MonHocDAO;
import quanlyhocvu.api.mongodb.DAO.NamHocDAO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;

@Repository
public class MongoService {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AddressDAO addressDAO;
    
    @Autowired 
    private GiaoVienDAO giaoVienDAO;
    
    @Autowired
    private NamHocDAO namHocDAO;
    
    @Autowired
    private MonHocDAO monhocDAO;
    
    @Autowired
    private KhoiLopDAO khoiLopDAO;
    
    @Autowired 
    private LopHocDAO lophocDAO;
    
    @Autowired
    private HocSinhDAO hocsinhDAO;

    
    public boolean insertgiaoVien(GiaoVienDTO dto) {
        return giaoVienDAO.insertTeacher(dto);
    }
    
    public List<GiaoVienDTO> getAllgiaoVien() {
        return giaoVienDAO.getAllgiaoVien();
    }
    //<editor-fold defaultstate="collapsed" desc="Get Set DAO">\
    
    public HocSinhDAO getHocsinhDAO() {
        return hocsinhDAO;
    }

    public void setHocsinhDAO(HocSinhDAO hocsinhDAO) {
        this.hocsinhDAO = hocsinhDAO;
    }
    
    /**
     * @return the lophocDAO
     */
    public LopHocDAO getLophocDAO() {
        return lophocDAO;
    }

    /**
     * @param lophocDAO the lophocDAO to set
     */
    public void setLophocDAO(LopHocDAO lophocDAO) {
        this.lophocDAO = lophocDAO;
    }
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
    
    
    //</editor-fold>
    
    
    /**************************************************
     * function for school-year
     * @author: LyVV
     *************************************************/
    /**
     * this function is used to return the list of school-year
     * @return 
     */
    public List<NamHocDTO> getAllnamHoc(){
        return namHocDAO.getAllList();
    }
    /**
     * Insert new namHoc into list
     * @param dto
     * @return 
     */
    public boolean insertnamHoc(NamHocDTO dto) {
        return namHocDAO.insert(dto);
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
        return monhocDAO.getAllList();
    }
    /**
     * Insert new MonHoc into list
     * @param dto
     * @return 
     */
    public boolean insertMonHoc(MonHocDTO dto) {
        return monhocDAO.insert(dto);
    }
    
    /**************************************************
     * function for grade
     * @author: LyVV
     *************************************************/
    /**
     * this function is used to return the list of khoiLop
     * @return 
     */
    public List<KhoiLopDTO> getAllkhoiLop(){
        return khoiLopDAO.getAllList();
    }
    /**
     * Insert new khoiLop into list
     * @param dto
     * @return 
     */
    public boolean insertkhoiLop(KhoiLopDTO dto) {
        return khoiLopDAO.insert(dto);
    }

    /**************************************************
     * function for class
     * @author: LyVV
     *************************************************/
    /**
     * this function is used to return the list of LopHoc
     * @return 
     */
    public List<LopHocDTO> getAllLopHoc(){
        return lophocDAO.getAllList();
    }
    /**
     * Insert new LopHoc into list
     * @param dto
     * @return 
     */
    public boolean insertLopHoc(LopHocDTO dto) {
        return lophocDAO.insert(dto);
    }

    /**
     * get namHoc by Id
     * @param Id
     * @return 
     */
    public NamHocDTO getnamHocById(String Id) {
        return namHocDAO.getnamHocById(Id);
    }
    
    /**************************************************
     * function for students
     * @author: LyVV
     * @date created: Nov 17th
     *************************************************/
    public List<HocSinhDTO> getAllStudents(){
        return hocsinhDAO.getAllHocSinh();
    }
    
    public boolean insertStudent(HocSinhDTO dto){
        return hocsinhDAO.insertStudent(dto);
    }
    
    public boolean updateStudent(HocSinhDTO dto){
        return hocsinhDAO.updateStudent(dto);
    }
    
    public boolean deleteStudent(HocSinhDTO dto){
        return hocsinhDAO.delete(dto);
    }
    
    public HocSinhDTO getStudentByMaHS(String maHocSinh){
        System.out.println("Eo");
        return hocsinhDAO.getBymaHocSinh(maHocSinh);
    }

    public GiaoVienDAO getGiaoVienDAO() {
        return giaoVienDAO;
    }

    public void setGiaoVienDAO(GiaoVienDAO giaoVienDAO) {
        this.giaoVienDAO = giaoVienDAO;
    }


    
}
