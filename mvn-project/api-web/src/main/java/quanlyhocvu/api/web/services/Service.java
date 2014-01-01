/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import quanlyhocvu.api.mongodb.DAO.MonHocDAO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.web.controller.staff.ManagementSubjectController;

/**
 *
 * @author Vu
 */
@Path("service")
public class Service {

    ApplicationContext ctx = new GenericXmlApplicationContext(
            // "aopApplicationContext.xml",
            "noSqlApplicationContext.xml");
    MongoService mongoService = (MongoService) ctx.getBean("mongoService");

    @GET
    @Path("/getStudentByIdJSON/{maHocSinh}")
    @Produces(MediaType.APPLICATION_JSON)
    public HocSinhDTO getHocSinhByIdJSON(@PathParam("maHocSinh") String maHocSinh) {  
        HocSinhDTO hocSinh = mongoService.getStudentByMaHS(maHocSinh);
        if (null == hocSinh) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return hocSinh;
    }
    
    @GET
    @Path("/getStudentByIdXML/{maHocSinh}")
    @Produces(MediaType.APPLICATION_XML)
    public HocSinhDTO getHocSinhByIdXML(@PathParam("maHocSinh") String maHocSinh) {        
        return mongoService.getStudentByMaHS(maHocSinh);
    }
    
    
    @GET
    @Path("/getTeacherByIdJSON/{maGiaoVien}")
    @Produces(MediaType.APPLICATION_JSON)
    public GiaoVienDTO getGiaoVienByIdJSON(@PathParam("maGiaoVien") String maGiaoVien) {        
        GiaoVienDTO giaoVien = mongoService.getTeacherById(maGiaoVien);
        if (null == giaoVien) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return giaoVien;
    }
    
    @GET
    @Path("/getTeacherByIdXML/{maGiaoVien}")
    @Produces(MediaType.APPLICATION_XML)
    public GiaoVienDTO getGiaoVienByIdXML(@PathParam("maGiaoVien") String maGiaoVien) {        
        return mongoService.getTeacherById(maGiaoVien);
    }
    
    @GET
    @Path("/getAllTeachersByJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GiaoVienDTO> getAllGiaoVienByJSON() {        
        return mongoService.getAllgiaoVien();
    }    
    
    @GET
    @Path("/getAllStudentsByJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HocSinhDTO> getAllHocSinhByJSON() {        
        return mongoService.getAllStudents();
    }  
    
    @POST
    @Path("/addNewStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(HocSinhDTO hocsinh){
         mongoService.insertStudent(hocsinh);
    }
    
        

    @GET
    @Path("/getAllSubjectsXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<MonHocDTO> getAllMonHocXML() {
        return mongoService.getAllMonHoc();
    }

    @GET
    @Path("/getAllSubjectsJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonHocDTO> getAllMonHocJSON() {
        return mongoService.getAllMonHoc();
    }

}
