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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietChuyenMonDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.web.util.Utils;

/**
 *
 * @author Tuan
 */
@Controller
public class ListClassController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "class")
    public @ResponseBody
    ModelAndView classTeaching(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        
        List<ChiTietChuyenMonDTO> listChuyenMon = mongoService.getListChiTietChuyenMonByIdGiaoVien(Utils.getCurrentUserId(mongoService));
        List<PhanCongDTO> listPhanCong = new ArrayList<>();

        for (int i = 0; i < listChuyenMon.size(); i++) {
            List<PhanCongDTO> listTemp = mongoService.getListPhanCongBy(listChuyenMon.get(i).getid());
            listPhanCong.addAll(listTemp);
        }

        map.put("listPhanCong", listPhanCong);

        return new ModelAndView("teacher/class", map);
    }
}
