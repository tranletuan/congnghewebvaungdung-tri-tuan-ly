/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO;

import java.util.ArrayList;
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
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;

/**
 *
 * @author HuuTri
 */
@Repository
public class LopHocDAO {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperation;

    public boolean insert(LopHocDTO dto) {
        boolean res = true;

        mongoOperation.insert(dto);

        return res;
    }

    public boolean update(LopHocDTO dto) {
        boolean res = true;

        Query query = Query.query(Criteria.where("id").is(dto.getid()));
        Update update = new Update();
        update.set("giaoVien", dto.getgiaoVien());
        update.set("khoiLop", dto.getkhoiLop());
        update.set("namHoc", dto.getnamHoc());
        update.set("moTa", dto.getmoTa());
        update.set("listHocSinh", dto.getlistHocSinh());

        mongoOperation.findAndModify(query, update, LopHocDTO.class);

        return res;
    }

    public boolean delete(LopHocDTO dto) {
        boolean res = true;

        mongoOperation.remove(dto);

        return res;
    }

    public boolean insertHocSinh(String classId, HocSinhDTO studentIds) {
        boolean res = true;

        Query query = Query.query(Criteria.where("id").is(classId));
        Update update = new Update();
        update.push("listHocSinh", studentIds);

        mongoOperation.findAndModify(query, update, LopHocDTO.class);

        return res;
    }

    public boolean deleteHocSinh(String classId, String studentIds) {
        boolean res = true;

        Query query = Query.query(Criteria.where("id").is(classId));
        Update update = new Update();
        update.pull("listIDHocSinh", studentIds);

        mongoOperation.findAndModify(query, update, LopHocDTO.class);

        return res;
    }

    public List<LopHocDTO> getAllList() {
        return mongoOperation.findAll(LopHocDTO.class);
    }

    public LopHocDTO getLopHocById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperation.findOne(query, LopHocDTO.class);
    }

    public boolean addStudent(HocSinhDTO hs, String classId) {
        LopHocDTO lopHoc = getLopHocById(classId);
        List<HocSinhDTO> tempList = lopHoc.getlistHocSinh();
        tempList.add(hs);
        Query query = Query.query(Criteria.where("id").is(classId));
        Update update = new Update();
        update.set("listHocSinh", tempList);
        mongoOperation.findAndModify(query, update, LopHocDTO.class);
        return true;
    }

    public List<LopHocDTO> getLopHocTheoKhoiLop(String idKhoiLop) {
        Query query = Query.query(Criteria.where("khoiLop.$id").is(new ObjectId(idKhoiLop)));
        return mongoOperation.find(query, LopHocDTO.class);
    }

    public HocSinhDTO getHocSinhById(LopHocDTO dto, String idHocSinh) {
        HocSinhDTO hocSinh = new HocSinhDTO();
        List<HocSinhDTO> list = dto.getlistHocSinh();

        for (HocSinhDTO hs : list) {
            if (hs.getid().equalsIgnoreCase(idHocSinh)) {
                hocSinh = hs;
                break;
            }
        } 
        return hocSinh;
    }

}
