/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietChuyenMonDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietMonHocDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author Tuan
 */
@Controller
@RequestMapping(value = "class")
public class MarksController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "{phanCongId}/{loaiDiem}")
    public @ResponseBody
    ModelAndView marking(@PathVariable String phanCongId, @PathVariable String loaiDiem, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        PhanCongDTO phanCong = mongoService.getPhanCongById(phanCongId);
        ChiTietMonHocDTO chiTietMonHoc = phanCong.getChiTietChuyenMon().getChiTietMonHoc();
        LopHocDTO lopHoc = phanCong.getLopHoc();
        List<HocSinhDTO> listHocSinh = phanCong.getLopHoc().getlistHocSinh();
        List<DiemDTO> listDiem = new ArrayList();
        String tenLop = chiTietMonHoc.getKhoiLop().gettenKhoiLop() + lopHoc.gettenLopHoc();
        String monHoc = chiTietMonHoc.getMonHoc().gettenMonHoc();
        int hocKy = phanCong.getHocKy();

        for (HocSinhDTO hs : listHocSinh) {
            DiemDTO diem = mongoService.getDiemByHocSinhChiTietMonHoc(hs, chiTietMonHoc);
            listDiem.add(diem);
        }

        map.put("hocKy", hocKy);
        map.put("tenLop", tenLop);
        map.put("monHoc", monHoc);
        map.put("listDiem", listDiem);
        map.put("loaiDiem", loaiDiem);
        map.put("tenLoaiDiem", tenLoaiDiem(loaiDiem));

        return new ModelAndView("teacher/marking", map);
    }

    @RequestMapping(value = "chamdiem")
    public @ResponseBody
    ModelAndView saveMark(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("Nhon Ly");
        Float diemMoi = Float.valueOf(request.getParameter("diemSo"));
        Float diemCu = Float.valueOf(request.getParameter("diemCu"));
        String idDiem = request.getParameter("idDiem");
        String loaiDiemKT = request.getParameter("loaiDiemKT");
    
        mongoService.updateDiemSo(loaiDiemKT, idDiem, diemCu, diemMoi);
        return new ModelAndView("teacher/marking", map);
    }

    private String tenLoaiDiem(String type) {
        switch (type) {
            case "ktmieng":
                return "Kiểm Tra Miệng";
            case "kt15":
                return "Kiểm Tra 15 Phút";
            case "kt1tiet":
                return "Kiểm Tra 1 Tiết";
            case "ktgiuaky":
                return "Kiểm Tra Giữa Kỳ";
            case "ktcuoiky":
                return "Kiểm Tra Cuối Kỳ";
        }
        return " ";
    }
//    @RequestMapping(value = "{majorId}")
//    public @ResponseBody
//    ModelAndView marks(@PathVariable String majorId, @PathVariable String studentId, HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<>();
//        PhanCongDTO phanCong = mongoService.getPhanCongById(majorId);
//        LopHocDTO lopHoc = phanCong.getLopHoc();
//        HocSinhDTO hocSinh = mongoService.getHocSinhLopHocById(lopHoc, studentId);
//        DiemDTO diem = mongoService.getDiemByIdPhanCong(majorId);
//        DiemDTO chiTietDiem = mongoService.getChiTietDiemByIdDiem(diem);
//
//        map.put("diem", diem);
//        map.put("hocSinh", hocSinh);
//        map.put("chiTietDiem", chiTietDiem);
//        return new ModelAndView("mark", map);
//    }
//    @RequestMapping(value = "class/{majorId}/{studentId}")
//    public @ResponseBody
//    ModelAndView marks(@PathVariable String majorId, @PathVariable String studentId, HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<>();
//        PhanCongDTO phanCong = mongoService.getPhanCongById(majorId);
//        LopHocDTO lopHoc = phanCong.getLopHoc();
//        HocSinhDTO hocSinh = mongoService.getHocSinhLopHocById(lopHoc, studentId);
//        DiemDTO diem = mongoService.getDiemByIdPhanCong(majorId);
//        ChiTietDiemDTO chiTietDiem = mongoService.getChiTietDiemByIdDiem(diem);
//        
//        map.put("diem", diem);
//        map.put("hocSinh", hocSinh);
//        map.put("chiTietDiem", chiTietDiem);
//        return new ModelAndView("teacher/mark", map);
//    }
}
