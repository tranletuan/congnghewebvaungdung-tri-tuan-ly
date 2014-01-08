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
        update.set("hocSinh", dto.getHocSinh());
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
        Query query = Query.query(Criteria.where("hocSinh.$id").is(new ObjectId(hocSinh.getid())).and("chiTietMonHoc.$id").is(new ObjectId(chiTietMonHoc.getid())));
        dto = mongoOperation.findOne(query, DiemDTO.class);

        if (dto == null) {
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
        switch (loaiDiem) {
            case "ktmieng": {
                List<Float> tempList = dto.getListDiemKTMieng();
                tempList.add(0, diemSo);
                dto.setListDiemKTMieng(tempList);
                break;
            }
            case "kt15": {
                List<Float> tempList = dto.getListDiemKT15();
                tempList.add(0, diemSo);
                dto.setListDiemKT15(tempList);
                break;
            }
            case "kt1tiet": {
                List<Float> tempList = dto.getListDiemKT1Tiet();
                tempList.add(0, diemSo);
                dto.setListDiemKT1Tiet(tempList);
                break;
            }
            case "ktgiuaky":
                dto.setDiemGiuaKy(diemSo);
                break;
            case "ktcuoiky":
                dto.setDiemCuoiKy(diemSo);
                break;
        }

        update(dto);

        return true;
    }

    public boolean deleteDiemSo(String loaiDiem, String idDiem, int index) {
        DiemDTO dto = getDiemById(idDiem);
        switch (loaiDiem) {
            case "ktmieng": {
                List<Float> tempList = dto.getListDiemKTMieng();
                tempList.remove(index);
                dto.setListDiemKTMieng(tempList);
                break;
            }
            case "kt15": {
                List<Float> tempList = dto.getListDiemKT15();
                tempList.remove(index);
                dto.setListDiemKT15(tempList);
                break;
            }
            case "kt1tiet": {
                List<Float> tempList = dto.getListDiemKT1Tiet();
                tempList.remove(index);
                dto.setListDiemKT1Tiet(tempList);
                break;
            }
            case "ktgiuaky":
                dto.setDiemGiuaKy(-1);
                break;
            case "ktcuoiky":
                dto.setDiemCuoiKy(-1);
                break;
        }
        
        update(dto);
        return true;
    }

    public boolean updateDiemSo(String loaiDiem, String idDiem, int index, Float diemMoi) {
        DiemDTO dto = getDiemById(idDiem);
        switch (loaiDiem) {
            case "ktmieng": {
                List<Float> tempList = dto.getListDiemKTMieng();
                tempList.set(index, diemMoi);
                dto.setListDiemKTMieng(tempList);
                break;
            }
            case "kt15": {
                List<Float> tempList = dto.getListDiemKT15();
                tempList.set(index, diemMoi);
                dto.setListDiemKTMieng(tempList);
                break;
            }
            case "kt1tiet": {
                List<Float> tempList = dto.getListDiemKT1Tiet();
                tempList.set(index, diemMoi);
                dto.setListDiemKTMieng(tempList);
                break;
            }
            case "ktgiuaky":
                dto.setDiemGiuaKy(diemMoi);
                break;
            case "ktcuoiky":
                dto.setDiemCuoiKy(diemMoi);
                break;
        }
        update(dto);
        return true;
    }

    public boolean tinhDiemTB(DiemDTO dto) {

        List<Float> listKTMieng = dto.getListDiemKTMieng();
        int soCotMieng = 0;
        float tongMieng = 0;
        List<Float> listKT15 = dto.getListDiemKT15();
        int soCot15 = 0;
        float tong15 = 0;
        List<Float> listKT1Tiet = dto.getListDiemKT1Tiet();
        int soCot1Tiet = 0;
        float tong1Tiet = 0;
        Float ktgiuaky = dto.getDiemGiuaKy();
        Float ktcuoiky = dto.getDiemCuoiKy();
        Float DTB = new Float(0);
        int tongSoCot = 0;

        for (float diemMieng : listKTMieng) {
            if (diemMieng >= 0) {
                tongMieng += diemMieng;
                soCotMieng++;
            }
        }

        for (float diem15 : listKT15) {
            if (diem15 >= 0) {
                tong15 += diem15;
                soCot15++;
            }
        }

        for (float diem1tiet : listKT1Tiet) {
            if (diem1tiet >= 0) {
                tong1Tiet += diem1tiet * 2;
                soCot1Tiet++;
            }
        }

        if (soCotMieng > 0) {
            DTB += tongMieng;
            tongSoCot += soCotMieng;
            System.out.println("tongMieng " + tongMieng);
            System.out.println("soCotMieng " + soCotMieng); 
        }

        if (soCot15 > 0) {
            DTB += tong15;
            tongSoCot += soCot15;
            
            System.out.println("tong15 " + tong15);
            System.out.println("soCot15 " + soCot15); 
        }

        if (soCot1Tiet > 0) {
            DTB += tong1Tiet;
            tongSoCot += soCot1Tiet * 2;
            
            System.out.println("tong1Tiet " + tong1Tiet);
            System.out.println("soCot1Tiet " + soCot1Tiet); 
        }

        if (ktgiuaky >= 0) {
            DTB += ktgiuaky * 2;
            tongSoCot += 2;
            
            System.out.println("ktgiuaky " + ktgiuaky);
            
        }

        if (ktcuoiky >= 0) {
            DTB += ktcuoiky * 3;
            tongSoCot += 3;
            
            System.out.println("ktcuoiky " + ktcuoiky);
        }

        if (tongSoCot > 0) {
            DTB = DTB / tongSoCot;
            
            System.out.println("DTB " + DTB);
            System.out.println("tongSoCot " + tongSoCot); 
        }

        dto.setDiemTB(DTB);

        update(dto);

        return true;
    }
}
