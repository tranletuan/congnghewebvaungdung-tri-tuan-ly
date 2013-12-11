package quanlyhocvu.api.mongodb.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DAO.AddressDAO;
import quanlyhocvu.api.mongodb.DAO.AuthorityDAO;
import quanlyhocvu.api.mongodb.DAO.ChiTietChuyenMonDAO;
import quanlyhocvu.api.mongodb.DAO.ChiTietDiemDAO;
import quanlyhocvu.api.mongodb.DAO.ChiTietMonHocDAO;
import quanlyhocvu.api.mongodb.DAO.DiemDAO;
import quanlyhocvu.api.mongodb.DAO.GiaoVienDAO;
import quanlyhocvu.api.mongodb.DAO.HocSinhDAO;
import quanlyhocvu.api.mongodb.DAO.KhoiLopDAO;
import quanlyhocvu.api.mongodb.DAO.LopHocDAO;
import quanlyhocvu.api.mongodb.DAO.MonHocDAO;
import quanlyhocvu.api.mongodb.DAO.NamHocDAO;
import quanlyhocvu.api.mongodb.DAO.PhanCongDAO;
import quanlyhocvu.api.mongodb.DAO.StaffDAO;
import quanlyhocvu.api.mongodb.DTO.Authority.RoleDTO;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietChuyenMonDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietDiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietMonHocDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.StaffDTO;
import quanlyhocvu.api.mongodb.utils.Authorities;

@Repository
public class MongoService {

     Logger logger = LoggerFactory.getLogger(getClass());
     //<editor-fold defaultstate="collapsed" desc="DAO variables">\
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
     private DiemDAO diemDAO;

     @Autowired
     private HocSinhDAO hocsinhDAO;

     @Autowired
     private PhanCongDAO phancongDAO;

     @Autowired
     private ChiTietChuyenMonDAO chitietchuyenmonDAO;

     @Autowired
     private ChiTietMonHocDAO chitietmonhocDAO;

     @Autowired
     private AuthorityDAO authorityDAO;

     @Autowired
     private StaffDAO staffDAO;

     @Autowired
     private ChiTietDiemDAO chitietdiemDAO;

     //</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="Function for TEACHER">\
     //</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="Function for TEACHER">\
     public boolean insertgiaoVien(GiaoVienDTO dto) {
          return giaoVienDAO.insertTeacher(dto);
     }

     public List<GiaoVienDTO> getAllgiaoVien() {
          return giaoVienDAO.getAllgiaoVien();
     }

     public boolean updategiaoVien(GiaoVienDTO dto) {
          return giaoVienDAO.updateTeacher(dto);
     }

     public GiaoVienDTO getTeacherById(String maGiaoVien) {
          return giaoVienDAO.getBymaGiaoVien(maGiaoVien);
     }

     public GiaoVienDTO getTeacherByid(String id) {
          return giaoVienDAO.getById(id);
     }

     //</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="Get Set DAO">\
     public StaffDAO getStaffDAO() {
          return staffDAO;
     }

     public void setStaffDAO(StaffDAO staffDAO) {
          this.staffDAO = staffDAO;
     }

     public AuthorityDAO getAuthorityDAO() {
          return authorityDAO;
     }

     public void setAuthorityDAO(AuthorityDAO authorityDAO) {
          this.authorityDAO = authorityDAO;
     }

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

     public GiaoVienDAO getGiaoVienDAO() {
          return giaoVienDAO;
     }

     public void setGiaoVienDAO(GiaoVienDAO giaoVienDAO) {
          this.giaoVienDAO = giaoVienDAO;
     }

     //<editor-fold defaultstate="collapsed" desc="Function for SCHOOL_YEAR">\
     /**
      * ************************************************
      * function for school-year
      *
      * @author: LyVV ***********************************************
      */
     /**
      * this function is used to return the list of school-year
      *
      * @return
      */
     public List<NamHocDTO> getAllnamHoc() {
          return namHocDAO.getAllList();
     }

     /**
      * Insert new namHoc into list
      *
      * @param dto
      * @return
      */
     public boolean insertnamHoc(NamHocDTO dto) {
          return namHocDAO.insert(dto);
     }

     /**
      * get namHoc by Id
      *
      * @param Id
      * @return
      */
     public NamHocDTO getnamHocById(String Id) {
          return namHocDAO.getnamHocById(Id);
     }

     public NamHocDTO getnamHocByName(String tenNamHoc) {
          return namHocDAO.getNamHocByName(tenNamHoc);
     }

     //</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="Function for SUBJECT">\
     /**
      * ************************************************
      * function for subject
      *
      * @author: LyVV ***********************************************
      */
     /**
      * this function is used to return the list of MonHoc
      *
      * @return
      */
     public List<MonHocDTO> getAllMonHoc() {
          return monhocDAO.getAllList();
     }

     /**
      * Insert new MonHoc into list
      *
      * @param dto
      * @return
      */
     public boolean insertMonHoc(MonHocDTO dto) {
          return monhocDAO.insert(dto);
     }
    //</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="Function for GRADE">\
     /**
      * ************************************************
      * function for grade
      *
      * @author: LyVV ***********************************************
      */
     /**
      * this function is used to return the list of khoiLop
      *
      * @return
      */
     public List<KhoiLopDTO> getAllkhoiLop() {
          return khoiLopDAO.getAllList();
     }

     /**
      * Insert new khoiLop into list
      *
      * @param dto
      * @return
      */
     public boolean insertkhoiLop(KhoiLopDTO dto) {
          return khoiLopDAO.insert(dto);
     }

     public KhoiLopDTO getKhoiLopByid(String id) {
          return khoiLopDAO.getById(id);
     }

     public KhoiLopDTO getKhoiLopByName(String tenKhoiLop) {
          return khoiLopDAO.getByName(tenKhoiLop);
     }
    //</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="Function for CLASS">\
     /**
      * ************************************************
      * function for class
      *
      * @author: LyVV ***********************************************
      */
     /**
      * this function is used to return the list of LopHoc
      *
      * @return
      */
     public List<LopHocDTO> getAllLopHoc() {
          return lophocDAO.getAllList();
     }

     /**
      * Insert new LopHoc into list
      *
      * @param dto
      * @return
      */
     public boolean insertLopHoc(LopHocDTO dto) {
          return lophocDAO.insert(dto);
     }

     public LopHocDTO getLopHocById(String idLopHoc) {
          return lophocDAO.getLopHocById(idLopHoc);
     }

     public boolean addStudent(HocSinhDTO hs, String id) {
          return lophocDAO.addStudent(hs, id);
     }
   
    public List<LopHocDTO> getLopHocTheoNamHocKhoiLop(String idNamHoc, String idKhoiLop){
        return lophocDAO.getLopHocTheoNamHocKhoiLop(idNamHoc, idKhoiLop);
    }
    //</editor-fold>    

     public boolean updateLopHoc(LopHocDTO dto) {
          return lophocDAO.update(dto);
     }

     public List<LopHocDTO> getLopHocTheoKhoiLop(String tenKhoiLop) {
          return lophocDAO.getLopHocTheoKhoiLop(tenKhoiLop);
     }

     public HocSinhDTO getHocSinhLopHocById(LopHocDTO dto, String idHocSinh) {
          return lophocDAO.getHocSinhById(dto, idHocSinh);
     }

      //</editor-fold>    
     //<editor-fold defaultstate="collapsed" desc="Function for STUDENT">\
     /**
      * ************************************************
      * function for students
      *
      * @author: LyVV
      * @date created: Nov 17th ***********************************************
      */
     public List<HocSinhDTO> getAllStudents() {
          return hocsinhDAO.getAllHocSinh();
     }

     public boolean insertStudent(HocSinhDTO dto) {
          return hocsinhDAO.insertStudent(dto);
     }

     public boolean updateStudent(HocSinhDTO dto) {
          return hocsinhDAO.updateStudent(dto);
     }

     public boolean deleteStudent(HocSinhDTO dto) {
          return hocsinhDAO.delete(dto);
     }

     public HocSinhDTO getStudentByMaHS(String maHocSinh) {
          return hocsinhDAO.getBymaHocSinh(maHocSinh);
     }

     public List<HocSinhDTO> getStudentsByLopHoc(String maLopHoc) {
          return hocsinhDAO.getByLopHoc(maLopHoc);
     }
   
    public HocSinhDTO getHocSinhById(String idStudent) {
        return hocsinhDAO.getHocSinhById(idStudent);
    }
   
    public List<HocSinhDTO> getHocSinhChuaXepLop(){
        return hocsinhDAO.getHocSinhChuaXepLop();
    }
    
    public List<HocSinhDTO> getHocSinhChuaXepLopTheoKhoiLop(String khoiLopId){
        return hocsinhDAO.getHocSinhChuaXepLopTheoKhoiLop(khoiLopId);
    }
   
    //</editor-fold>

     //</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="Function for MARK">
     public List<DiemDTO> getAllDiem() {
          return diemDAO.getAllDiem();
     }

     public boolean insertDiem(DiemDTO dto) {
          return diemDAO.insert(dto);
     }

     public boolean deleteDiem(DiemDTO dto) {
          return diemDAO.delete(dto);
     }

     public boolean updateDiem(DiemDTO dto) {
          return diemDAO.updateDiem(dto);
     }

     public DiemDTO getDiemByIdPhanCong(String idPhanCong) {
          return diemDAO.getDiemByIdPhanCong(idPhanCong);
     }

//</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="CHI TIET DIEM">
     public boolean insertChiTietDiem(ChiTietDiemDTO dto) {
          return chitietdiemDAO.insert(dto);
     }

     public boolean deleteChiTietDiem(ChiTietDiemDTO dto) {
          return chitietdiemDAO.delete(dto);
     }

     public boolean updateChiTietDiem(ChiTietDiemDTO dto) {
          return chitietdiemDAO.update(dto);
     }
     public ChiTietDiemDTO getChiTietDiemByIdDiem(DiemDTO diem) {
         return chitietdiemDAO.getChiTietDiemByIdDiem(diem);
     }
//</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="PHAN CONG">
     public List<PhanCongDTO> getAllPhanCong() {
          return phancongDAO.getAllList();
     }

     public List<PhanCongDTO> getListPhanCongBy(String idChiTietChuyenMon) {
          return phancongDAO.getListBy(idChiTietChuyenMon);
     }

     public boolean insertPhanCong(PhanCongDTO dto) {
          return phancongDAO.insert(dto);
     }

     public boolean deletePhanCong(PhanCongDTO dto) {
          return phancongDAO.delete(dto);
     }

     public boolean updatePhanCong(PhanCongDTO dto) {
          return phancongDAO.update(dto);
     }

     public PhanCongDTO getPhanCongById(String idPhanCong) {
          return phancongDAO.getPhanCongById(idPhanCong);
     }
//</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="CHI TIET CHUYEN MON">
     public List<ChiTietChuyenMonDTO> getAllChiTietChuyenMon() {
          return chitietchuyenmonDAO.getAllList();
     }

     public List<ChiTietChuyenMonDTO> getListChiTietChuyenMonByIdGiaoVien(String idGiaoVien) {
          return chitietchuyenmonDAO.getListBy(idGiaoVien);
     }

     public boolean insertChiTietChuyenMon(ChiTietChuyenMonDTO dto) {
          return chitietchuyenmonDAO.insert(dto);
     }

     public boolean deleteChiTietChuyenMon(ChiTietChuyenMonDTO dto) {
          return chitietchuyenmonDAO.delete(dto);
     }

     public boolean updateChiTietChuyenMon(ChiTietChuyenMonDTO dto) {
          return chitietchuyenmonDAO.update(dto);
     }
//</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="Chi Tiet Mon Hoc">
     public boolean insertChiTietMonHoc(ChiTietMonHocDTO dto) {
          return chitietmonhocDAO.insert(dto);
     }

     public boolean deleteChiTietMonHoc(ChiTietMonHocDTO dto) {
          return chitietmonhocDAO.delete(dto);
     }

     public boolean updateChiTietMonHoc(ChiTietMonHocDTO dto) {
          return chitietmonhocDAO.update(dto);
     }

     public List<ChiTietMonHocDTO> getAllChiTietMonHoc() {
          return chitietmonhocDAO.getAllList();
     }
//</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="Function For User">
     public UserDTO loadUserByUserName(String username) {
          return authorityDAO.getUserByUserName(username);
     }

     public void insertStaff(StaffDTO staff) {
          staffDAO.insertStaff(staff);
     }

     public List<StaffDTO> getAllStaff() {
          return staffDAO.getAllStaff();

     }

     public List<UserDTO> getAllUser() {
          return authorityDAO.getAllUser();
     }

     public void generateAllUser() {
          authorityDAO.generateAllUser();
     }

     public List<UserDTO> getUsersByRoleName(String rolename) {
          return authorityDAO.getUsersByRoleName(rolename);
     }

     public RoleDTO getRoleByRoleName(String RoleName) {
          return authorityDAO.getRoleByRoleName(RoleName);
     }
     
     public UserDTO getUserByUserName(String username) {
          return authorityDAO.getUserByUserName(username);
     }

     public void updateUser(UserDTO user) {
          authorityDAO.updateUser(user);
     }

     public void insertUser(String username, String password, String rolename, String userId) throws Throwable {
          UserDTO user = new UserDTO(username, password, userId);
          RoleDTO role = authorityDAO.getRoleByRoleName(rolename);
          user.getRoles().add(role);
          authorityDAO.insertUser(user);
     }

     public List<RoleDTO> getAllRole() {
          List<RoleDTO> roles = authorityDAO.getAllRoles();
          RoleDTO temp = null;
          for (RoleDTO role : roles) {
               if (role.getRolename().equals(Authorities.ADMIN.toString())) {
                    temp = role;
               }
          }
          roles.remove(temp);
          return roles;
     }
//</editor-fold>  

     //<editor-fold defaultstate="collapsed" desc="Function for Staff">
     public StaffDTO getStaffById(String manhanvien) {
          return staffDAO.getStaffById(manhanvien);
     }

     public void updateStaff(StaffDTO obj) {
          staffDAO.updateStaff(obj);
     }
//</editor-fold>
}

