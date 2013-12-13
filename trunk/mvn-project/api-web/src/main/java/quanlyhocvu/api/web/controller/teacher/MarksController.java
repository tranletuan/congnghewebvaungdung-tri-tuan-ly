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
        List<HocSinhDTO> listHocSinh = phanCong.getLopHoc().getlistHocSinh();
        List<DiemDTO> listDiem = new ArrayList();

        for (HocSinhDTO hs : listHocSinh) {
            DiemDTO diem = mongoService.getDiemByHocSinhChiTietMonHoc(hs, chiTietMonHoc);
            listDiem.add(diem);
        }

        map.put("listDiem", listDiem);
        map.put("listHocSinh", listHocSinh);
        map.put("phanCong", phanCong);
        map.put("loaiDiem", loaiDiem);
        map.put("tenLoaiDiem", tenLoaiDiem(loaiDiem));

        return new ModelAndView("marking", map);
    }

//    @RequestMapping(value = "{phanCongId/{loaiDiem}/diem_moi}")
//    public @ResponseBody
//    ModelAndView save_mark(@PathVariable String phanCongId, @PathVariable String loaiDiem, HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        float diemSo = Float.valueOf(request.getParameter("diemSo"));
//
//        PhanCongDTO phanCong = mongoService.getPhanCongById(phanCongId);
//        HocSinhDTO hocSinh = new HocSinhDTO();
//        DiemDTO diem = mongoService.getDiemByHocSinh(hocSinh);
//
//        if (loaiDiem == "ktmieng") {
//            mongoService.insertDiemSo("DiemKTMieng", diem.getid(), diemSo);
//        } else if (loaiDiem == "kt15") {
//            mongoService.insertDiemSo("DiemKT15", diem.getid(), diemSo);
//        } else if (loaiDiem == "kt1tiet") {
//            mongoService.insertDiemSo("DiemKT1Tiet", diem.getid(), diemSo);
//        } else if (loaiDiem == "ktgiuaky") {
//            mongoService.insertDiemSo("DiemGiuaKy", diem.getid(), diemSo);
//        } else if (loaiDiem == "ktcuoiky") {
//            mongoService.insertDiemSo("DiemCuoiKy", diem.getid(), diemSo);
//        }
//
//        return new ModelAndView("marking");
//    }

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
