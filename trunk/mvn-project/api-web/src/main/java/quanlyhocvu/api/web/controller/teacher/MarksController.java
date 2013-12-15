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

    @RequestMapping(value = "{phanCongId}/{loaiDiem}/{index}")
    public @ResponseBody
    ModelAndView marking(@PathVariable String phanCongId, @PathVariable String loaiDiem, @PathVariable String index, HttpServletRequest request) {

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

        List<Integer> soCotDiem = getSoCotDiem(loaiDiem, listDiem.get(0));

        map.put("hocKy", hocKy);
        map.put("tenLop", tenLop);
        map.put("monHoc", monHoc);
        map.put("listDiem", listDiem);
        map.put("loaiDiem", loaiDiem);
        map.put("tenLoaiDiem", tenLoaiDiem(loaiDiem));
        map.put("idPhanCong", phanCong.getid());
        map.put("index", Integer.valueOf(index));
        map.put("soCotDiem", soCotDiem);
        return new ModelAndView("teacher/marking", map);
    }

    @RequestMapping(value = "{phanCongId}/view")
    public @ResponseBody
    ModelAndView viewMark(@PathVariable String phanCongId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        PhanCongDTO phanCong = mongoService.getPhanCongById(phanCongId);
        ChiTietMonHocDTO chiTietMonHoc = phanCong.getChiTietChuyenMon().getChiTietMonHoc();

        List<HocSinhDTO> listHocSinh = phanCong.getLopHoc().getlistHocSinh();
        List<DiemDTO> listDiem = new ArrayList();

        String tenLop = chiTietMonHoc.getKhoiLop().gettenKhoiLop() + phanCong.getLopHoc().gettenLopHoc();
        String monHoc = chiTietMonHoc.getMonHoc().gettenMonHoc();
        int hocKy = phanCong.getHocKy();

        for (HocSinhDTO hs : listHocSinh) {
            DiemDTO diem = mongoService.getDiemByHocSinhChiTietMonHoc(hs, chiTietMonHoc);
            listDiem.add(diem);
        }

        int colSpanKTM = listDiem.get(0).getListDiemKTMieng().size();
        int colSpanKT15 = listDiem.get(0).getListDiemKT15().size();
        int colSpanKT1Tiet = listDiem.get(0).getListDiemKT1Tiet().size();

        map.put("listDiem", listDiem);
        map.put("colSpanKTM", colSpanKTM);
        map.put("colSpanKT15", colSpanKT15);
        map.put("colSpanKT1Tiet", colSpanKT1Tiet);
        map.put("hocKy", hocKy);
        map.put("tenLop", tenLop);
        map.put("monHoc", monHoc);

        return new ModelAndView("teacher/overview");
    }

    @RequestMapping(value = "chamdiem")
    public @ResponseBody
    ModelAndView saveMark(HttpServletRequest request) {
        Float diemMoi = Float.valueOf(request.getParameter("diemSo"));
        int index = Integer.valueOf(request.getParameter("index"));
        String idDiem = request.getParameter("idDiem");
        String loaiDiem = request.getParameter("loaiDiem");

        mongoService.updateDiemSo(loaiDiem, idDiem, index, diemMoi);
        return new ModelAndView("teacher/marking");
    }

    @RequestMapping(value = "themcotmoi")
    public @ResponseBody
    ModelAndView newMark(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        String idPhanCong = request.getParameter("idPhanCong");
        String loaiDiem = request.getParameter("loaiDiem");

        if (!"ktgiuaky".equals(loaiDiem) && !"ktcuoiky".equals(loaiDiem)) {
            PhanCongDTO phanCong = mongoService.getPhanCongById(idPhanCong);
            ChiTietMonHocDTO chiTietMonHoc = phanCong.getChiTietChuyenMon().getChiTietMonHoc();
            List<HocSinhDTO> listHocSinh = phanCong.getLopHoc().getlistHocSinh();

            for (HocSinhDTO hs : listHocSinh) {
                DiemDTO diem = mongoService.getDiemByHocSinhChiTietMonHoc(hs, chiTietMonHoc);
                mongoService.insertDiemSo(loaiDiem, diem.getid(), new Float(-1));
            }
        }

        return new ModelAndView("teacher/marking", map);
    }

    @RequestMapping(value = "xoacot")
    public @ResponseBody
    ModelAndView removeMark(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        String idPhanCong = request.getParameter("idPhanCong");
        String loaiDiem = request.getParameter("loaiDiem");
        int index = Integer.valueOf(request.getParameter("index"));

        PhanCongDTO phanCong = mongoService.getPhanCongById(idPhanCong);
        ChiTietMonHocDTO chiTietMonHoc = phanCong.getChiTietChuyenMon().getChiTietMonHoc();
        List<HocSinhDTO> listHocSinh = phanCong.getLopHoc().getlistHocSinh();

        for (HocSinhDTO hs : listHocSinh) {
            DiemDTO diem = mongoService.getDiemByHocSinhChiTietMonHoc(hs, chiTietMonHoc);
            mongoService.deleteDiemSo(loaiDiem, diem.getid(), index);
        }

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

    private List<Integer> getSoCotDiem(String loaiDiem, DiemDTO diem) {
        List<Float> tempList = new ArrayList();
        switch (loaiDiem) {
            case "ktmieng":
                tempList = diem.getListDiemKTMieng();
                break;
            case "kt15":
                tempList = diem.getListDiemKT15();
                break;
            case "kt1tiet":
                tempList = diem.getListDiemKT1Tiet();
                break;
            case "ktgiuaky":
                tempList.add(diem.getDiemGiuaKy());
                break;

            case "ktcuoiky":
                tempList.add(diem.getDiemCuoiKy());
                break;
        }

        List<Integer> rs = new ArrayList();
        Integer j = 0;
        for (Float i : tempList) {
            rs.add(j++);
        }

        return rs;
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
