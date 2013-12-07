package quanlyhocvu.api.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.mongodb.DTO.Teacher.*;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;

class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new GenericXmlApplicationContext(
                "noSqlApplicationContext.xml");
        MongoService mongoService = (MongoService) ctx.getBean("mongoService");
        
        List<HocSinhDTO> hs = mongoService.getAllStudents();
        LopHocDTO lop = mongoService.getLopHocById("52a1429344ae1dabcd60b2bc");
        lop.setlistHocSinh(hs);
      
//        List<HocSinhDTO> listHocSinh = lop.getlistHocSinh();
//        
//        System.out.print(listHocSinh.size());
        
//        Map<String, Object> map = new HashMap<String, Object>();
//        List<ChiTietChuyenMonDTO> listChuyenMon = mongoService.getListChiTietChuyenMonByIdGiaoVien("52a0aa8d44aeb4910f530db8");
//        List<PhanCongDTO> listPhanCong = new ArrayList<PhanCongDTO>();
//        
//        for(int i = 0; i < listChuyenMon.size(); i++) {
//            List<PhanCongDTO> listTemp = mongoService.getListPhanCongBy(listChuyenMon.get(i).getid());
//            listPhanCong.addAll(listTemp);
//            
//        }
//        
//        System.out.print(listPhanCong.size());
//        System.out.print("End");
//        List<ChiTietChuyenMonDTO> ctcm = mongoService.getAllChiTietChuyenMon();
//        List<LopHocDTO> lop = mongoService.getAllLopHoc();
//        
//        PhanCongDTO p = new PhanCongDTO();
//        p.setChiTietChuyenMon(ctcm.get(0));
//        p.setLopHoc(lop.get(0));
//        p.setMota("Mo ta choi");
//        
//        mongoService.insertPhanCong(p);
//        List<GiaoVienDTO> gv = mongoService.getAllgiaoVien();
//        List<ChiTietMonHocDTO> ct = mongoService.getAllChiTietMonHoc();
//        
//        ChiTietChuyenMonDTO ctcm = new ChiTietChuyenMonDTO();
//        ctcm.setChiTietMonHoc(ct.get(0));
//        ctcm.setGiaoVien(gv.get(0));
//        ctcm.setMota("Mo ta choi");
//        
//        mongoService.insertChiTietChuyenMon(ctcm);
//        List<MonHocDTO> mon = mongoService.getAllMonHoc();
//        List<KhoiLopDTO> khoi = mongoService.getAllkhoiLop();
//        
//        ChiTietMonHocDTO chitietmonhoc = new ChiTietMonHocDTO();
//        chitietmonhoc.setKhoiLop(khoi.get(0));
//        chitietmonhoc.setMonHoc(mon.get(0));
//        
//        mongoService.insertChiTietMonHoc(chitietmonhoc);
//        //////////
//        GiaoVienDTO giaoVien = new GiaoVienDTO();
//        giaoVien.sethoTen("Vu Van Ly");
//        mongoService.insertgiaoVien(giaoVien);
//        /////////////
//        NamHocDTO namHoc = new NamHocDTO();
//        namHoc.settennamHoc("2012");
//        mongoService.insertnamHoc(namHoc);
//        ////////////
//        KhoiLopDTO khoiLop = new KhoiLopDTO();
//        khoiLop.settenkhoiLop("Khoi 2");
//        mongoService.insertkhoiLop(khoiLop);
//        //////////////
//        HocSinhDTO hocsinh = new HocSinhDTO();
//        hocsinh.sethoTen("Tran Le Tuan");
//        mongoService.insertStudent(hocsinh);
//        HocSinhDTO hocsinh1 = new HocSinhDTO();
//        hocsinh1.sethoTen("Tran Le Tuan1");
//        mongoService.insertStudent(hocsinh1);
//        ///////////////
//        LopHocDTO lophoc = new LopHocDTO();
//        lophoc.settenLopHoc("2/2");
//        lophoc.setgiaoVien(giaoVien);
//        lophoc.setnamHoc(namHoc);
//        lophoc.setkhoiLop(khoiLop);
//        lophoc.setlistHocSinh(new ArrayList<HocSinhDTO>());
//        lophoc.getlistHocSinh().add(hocsinh);
//        lophoc.getlistHocSinh().add(hocsinh1);      
//        mongoService.insertLopHoc(lophoc);
//<editor-fold defaultstate="collapsed" desc="comment">
                /// insert khoi lop
//                 KhoiLopDTO khoilop = new KhoiLopDTO();
//                 khoilop.setTenKhoiLop("Grade 3");
//                 mongoService.insertKhoiLop(khoilop);
//                 // insert nam hoc
//                 NamHocDTO namhoc = new NamHocDTO();
//                 namhoc.setTenNamHoc("2013-2014");
//                 mongoService.insertNamHoc(namhoc);
//                 // insert giao vien
//                 GiaoVienDTO giaovien = new GiaoVienDTO();
//                 giaovien.setHoTen("Vu Van Ly");
//                 mongoService.insertGiaoVien(giaovien);
//                 // insert hoc sinh
//                 HocSinhDTO hocsinh = new HocSinhDTO();
//                 hocsinh.setHoTen("aaaaaa");
//                 HocSinhDTO hocsinh1 = new HocSinhDTO();
//                 hocsinh1.setHoTen("aaaaaa");
//                 HocSinhDTO hocsinh2 = new HocSinhDTO();
//                 hocsinh2.setHoTen("aaaaaa");
//                 mongoService.insertStudent(hocsinh);
//                 mongoService.insertStudent(hocsinh1);
//                 mongoService.insertStudent(hocsinh2);
//                 // insert lop hoc
//                 LopHocDTO lophoc = new LopHocDTO();
//                 lophoc.setTenLopHoc("Class 2/3");
//                 lophoc.setKhoiLop(khoilop);
//                 lophoc.setNamHoc(namhoc);
//                 lophoc.setGiaoVien(giaovien);
//                 lophoc.setListHocSinh(new ArrayList<HocSinhDTO>());
//                 lophoc.getListHocSinh().add(hocsinh);
//                 lophoc.getListHocSinh().add(hocsinh1);
//                 lophoc.getListHocSinh().add(hocsinh2);
//                 mongoService.insertLopHoc(lophoc);
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="comment">
//        List<LopHocDTO> listlophoc = new ArrayList<LopHocDTO>();
//        listlophoc = mongoService.getAllLopHoc();
//        for (int i = 0; i < listlophoc.size(); i++) {
//            LopHocDTO lophoc = listlophoc.get(i);
//            System.out.println(lophoc.getTenLopHoc() + "-"
//                    + lophoc.getKhoiLop().getTenKhoiLop() + "-"
//                    + lophoc.getNamHoc().getTenNamHoc());
//            List<HocSinhDTO> list = lophoc.getListHocSinh();
//            if (list != null)
//            for (int j = 0; j < list.size(); j++) {
//                System.out.println(list.get(j).getHoTen());
//            }
//        }
//</editor-fold>
    }
}
