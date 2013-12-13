/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietMonHocDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;

/**
 *
 * @author Tuan
 */
@Repository
public class DiemDAO {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoOperations mongoOperation;

    public boolean insert(DiemDTO dto) {
        mongoOperation.insert(dto);
        return true;
    }

    public boolean delete(DiemDTO dto) {
        mongoOperation.remove(dto);
        return true;
    }

    public boolean update(DiemDTO dto) {

        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("hocSinh", dto.getHocSinh().getid());
        update.set("chiTietMonHoc", dto.getChiTietMonHoc());
        update.set("listDiemKTMieng", dto.getListDiemKTMieng());
        update.set("listDiemKT15", dto.getListDiemKT15());
        update.set("listDiemKT1Tiet", dto.getListDiemKT1Tiet());
        update.set("diemGiuaKy", dto.getDiemGiuaKy());
        update.set("diemCuoiKy", dto.getDiemCuoiKy());
        update.set("diemTB", dto.getDiemTB());

        mongoOperation.findAndModify(query, update, DiemDTO.class);
        return true;
    }

    public List<DiemDTO> getAllDiem() {
        return mongoOperation.findAll(DiemDTO.class);
    }

    public DiemDTO getDiemByHocSinhChiTietMonHoc(HocSinhDTO hocSinh, ChiTietMonHocDTO chiTietMonHoc) {
        DiemDTO dto = new DiemDTO();
        Query query = Query.query(Criteria.where("hocSinh.&id").is(hocSinh.getid())).addCriteria(Criteria.where("chiTietMonHoc.&id").is(chiTietMonHoc.getid()));
        dto = mongoOperation.findOne(query, DiemDTO.class);
        
        if(dto == null) {
            DiemDTO newDTO = new DiemDTO();
            newDTO.setChiTietMonHoc(chiTietMonHoc);
            newDTO.setHocSinh(hocSinh);
            mongoOperation.insert(newDTO);
            dto = newDTO;
        }
        
        return dto;
    }

    public DiemDTO getDiemById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperation.findOne(query, DiemDTO.class);
    }

    public boolean insertDiemSo(String loaiDiem, String idDiem, Float diemSo) {
        DiemDTO dto = getDiemById(idDiem);
        if (loaiDiem == "DiemKTMieng") {
            List<Float> tempList = dto.getListDiemKTMieng();
            tempList.add(0, diemSo);
            dto.setListDiemKTMieng(tempList);
        } else if (loaiDiem == "DiemKT15") {
            List<Float> tempList = dto.getListDiemKT15();
            tempList.add(0, diemSo);
            dto.setListDiemKT15(tempList);
        } else if (loaiDiem == "DiemKT15") {
            List<Float> tempList = dto.getListDiemKT1Tiet();
            tempList.add(0, diemSo);
            dto.setListDiemKT1Tiet(tempList);
        } else if (loaiDiem == "DiemGiuaKy") {
            dto.setDiemGiuaKy(diemSo);
        } else if (loaiDiem == "DiemCuoiKy") {
            dto.setDiemCuoiKy(diemSo);
        }

        update(dto);

        return true;
    }

    public boolean deleteDiemSo(String loaiDiem, String idDiem, Float diemSo) {
        DiemDTO dto = getDiemById(idDiem);
        if (loaiDiem == "DiemKTMieng") {
            List<Float> tempList = dto.getListDiemKTMieng();
            tempList.remove(diemSo);
            dto.setListDiemKTMieng(tempList);
        } else if (loaiDiem == "DiemKT15") {
            List<Float> tempList = dto.getListDiemKT15();
            tempList.remove(diemSo);
            dto.setListDiemKT15(tempList);
        } else if (loaiDiem == "DiemKT15") {
            List<Float> tempList = dto.getListDiemKT1Tiet();
            tempList.remove(diemSo);
            dto.setListDiemKT1Tiet(tempList);
        } else if (loaiDiem == "DiemGiuaKy") {
            dto.setDiemGiuaKy(-1);
        } else if (loaiDiem == "DiemCuoiKy") {
            dto.setDiemCuoiKy(-1);
        }

        update(dto);
        return true;
    }

    public boolean updateDiemSo(String loaiDiem, String idDiem, Float diemCu, Float diemMoi) {
        DiemDTO dto = getDiemById(idDiem);
        if (loaiDiem == "DiemKTMieng") {
            List<Float> tempList = dto.getListDiemKTMieng();
            int index = tempList.indexOf(diemCu);
            tempList.set(index, diemMoi);
            dto.setListDiemKTMieng(tempList);
        } else if (loaiDiem == "DiemKT15") {
            List<Float> tempList = dto.getListDiemKT15();
            int index = tempList.indexOf(diemCu);
            tempList.set(index, diemMoi);
            dto.setListDiemKTMieng(tempList);
        } else if (loaiDiem == "DiemKT15") {
            List<Float> tempList = dto.getListDiemKT1Tiet();
            int index = tempList.indexOf(diemCu);
            tempList.set(index, diemMoi);
            dto.setListDiemKTMieng(tempList);
        } else if (loaiDiem == "DiemGiuaKy") {
            dto.setDiemGiuaKy(diemMoi);
        } else if (loaiDiem == "DiemCuoiKy") {
            dto.setDiemCuoiKy(diemMoi);
        }

        update(dto);
        return true;
    }
}
