package quanlyhocvu.api.mongodb;

import java.util.ArrayList;
import quanlyhocvu.api.mongodb.service.MongoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;

class App {

    public static void main(String[] args) {

//        ApplicationContext ctx = new GenericXmlApplicationContext(
//                "noSqlApplicationContext.xml");
//        MongoService mongoService = (MongoService) ctx.getBean("mongoService");
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
    }
}
