/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;
import quanlyhocvu.api.mongodb.service.FunctionService;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/class")
public class ManagementClassController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView classList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<LopHocDTO> listLopHoc = new ArrayList<LopHocDTO>();
        listLopHoc = mongoService.getAllLopHoc();
        map.put("listLopHoc", listLopHoc);
        map.put("stt", 1);
        return new ModelAndView("management/class/index", map);
    }

    @RequestMapping(value = "new")
    public @ResponseBody
    ModelAndView newClass(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<NamHocDTO> listnamHoc = mongoService.getAllnamHoc();
        List<GiaoVienDTO> listgiaoVien = mongoService.getAllgiaoVien();
        List<KhoiLopDTO> listkhoiLop = mongoService.getAllkhoiLop();

        map.put("listnamHoc", listnamHoc);
        map.put("listgiaoVien", listgiaoVien);
        map.put("listkhoiLop", listkhoiLop);
        return new ModelAndView("management/class/new", map);
    }

    @RequestMapping(value = "save")
    public @ResponseBody
    ModelAndView saveClass(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LopHocDTO obj = new LopHocDTO();
        obj.setgiaoVien(mongoService.getTeacherByid(request.getParameter("IDgiaoVien")));
        obj.setkhoiLop(mongoService.getKhoiLopByid(request.getParameter("IDkhoiLop")));
        obj.setnamHoc(mongoService.getnamHocById(request.getParameter("IDnamHoc")));
        obj.settenLopHoc(request.getParameter("tenLopHoc"));
                
        boolean res = mongoService.insertLopHoc(obj);
        map.put("message", "Đã thêm thành công 1 lớp học");
        return new ModelAndView("redirect:/staff/management/class/index", map);
    }
    
    @RequestMapping(value="view/{classId}")
    public @ResponseBody
    ModelAndView viewClass(HttpServletRequest request, @PathVariable String classId){
        Map<String, Object> map = new HashMap<String, Object>();
        List<HocSinhDTO> listHocSinh = new ArrayList<HocSinhDTO>();
        LopHocDTO lopHoc = mongoService.getLopHocById(classId);
        listHocSinh = lopHoc.getlistHocSinh();
        map.put("lopHoc", lopHoc);        
        map.put("listHocSinh", listHocSinh);
        return new ModelAndView("management/class/view", map);
    }
    
    @RequestMapping(value="upgrade/{classId}")
    public @ResponseBody
    ModelAndView upgradeClass(HttpServletRequest request, @PathVariable String classId){
        System.out.println("Enter Upgrate");
        Map<String, Object> map = new HashMap<String, Object>();
        LopHocDTO lopHoc = mongoService.getLopHocById(classId);
        System.out.println(lopHoc);
        if (lopHoc.getTrangThaiLopHoc() == LopHocDTO.TrangThaiLopHoc.ChuaLenLop) {
            upgradeStudent(lopHoc);
        }else{
            System.out.println("Lop Hoc da duoc len lop");
        }        
        System.out.println("Finish Upgrade");
        map.put("message", "Lên lớp thành công");
        return new ModelAndView("redirect:/staff/management/class/index", map);
    }
        
    public boolean upgradeStudent(LopHocDTO lopHoc){                
        Iterator<HocSinhDTO> iterator = lopHoc.getlistHocSinh().iterator();
        //Create a new NamHoc if there is no next NamHoc in database
        String tenNamHoc = lopHoc.getNextNamHoc();     
        NamHocDTO namHoc = mongoService.getnamHocByName(tenNamHoc);
        if (namHoc == null) {
            NamHocDTO dtoNamHoc = new NamHocDTO(tenNamHoc, "");
            mongoService.insertnamHoc(dtoNamHoc);
            namHoc = dtoNamHoc;
        }
        String tenKhoiLop = lopHoc.getNextKhoiLop();
        KhoiLopDTO khoiLop = mongoService.getKhoiLopByName(tenKhoiLop);
        //System.out.println("Khoi Lop: "+khoiLop);
        if (khoiLop == null && Integer.parseInt(tenKhoiLop) < 10) {
            KhoiLopDTO dtoKhoiLop = new KhoiLopDTO(tenKhoiLop, "");
            mongoService.insertkhoiLop(dtoKhoiLop); 
            khoiLop = dtoKhoiLop;
        }
        //Neu lop ke < 10 thi tao lop hoc moi
        //Nguoc lai thi xet tot nghiep cho no
        if (Integer.parseInt(tenKhoiLop) < 10) {
            //Create a new Class
            LopHocDTO lopHocMoi = new LopHocDTO();
            lopHocMoi.setkhoiLop(khoiLop);
            lopHocMoi.setnamHoc(namHoc);    
            lopHocMoi.settenLopHoc(lopHoc.gettenLopHoc());            
            
            List<HocSinhDTO> listHocSinh = new ArrayList<HocSinhDTO>();
            while(iterator.hasNext()){
                HocSinhDTO hs = iterator.next();
                //Neu Hoc Sinh do len lop duoc thi add vao lop moi
                //Neu khong thi add vao lop nay tiep theo
                if(hs!=null){
                    if (hs.canUpgrade()){                       
                        listHocSinh.add(hs);
                    }else{
                        lopHoc.setOLaiLop(hs);
                    }
                }
            }
            lopHocMoi.setlistHocSinh(listHocSinh);
            mongoService.insertLopHoc(lopHocMoi);
        }else{
            while(iterator.hasNext()){
                HocSinhDTO hs = iterator.next();
                if (hs != null) {
                    if (hs.canGraduate()) {
                        hs.setTrangThaiHS(HocSinhDTO.TrangThaiHS.DaTotNghiep);
                    }else{
                        lopHoc.setOLaiLop(hs);
                    }
                }            
            }
        }
        //Set trang thai cho Lop da duoc xet len lop
        
        lopHoc.setTrangThaiLopHoc(LopHocDTO.TrangThaiLopHoc.DaLenLop) ;
        return true;
    }
        
}
