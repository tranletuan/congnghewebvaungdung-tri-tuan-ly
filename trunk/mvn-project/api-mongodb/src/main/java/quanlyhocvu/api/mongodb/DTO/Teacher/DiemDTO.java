/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.Teacher;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;

/**
 *
 * @author Tuan
 */
@Document
public class DiemDTO extends AbstractObjectDTO {

    @DBRef
    private HocSinhDTO hocSinh;

    @DBRef
    private ChiTietMonHocDTO chiTietMonHoc;

    private List<Float> listDiemKTMieng;
    private List<Float> listDiemKT15;
    private List<Float> listDiemKT1Tiet;

    private float diemGiuaKy = -1;
    private float diemCuoiKy = -1;
    private float diemTB = -1;

    public DiemDTO() {
        listDiemKTMieng = new ArrayList();
        listDiemKTMieng.add(new Float(-1));
        listDiemKT15 = new ArrayList();
        listDiemKT15.add(new Float(-1));
        listDiemKT1Tiet = new ArrayList();
        listDiemKT1Tiet.add(new Float(-1));
    }

    public ChiTietMonHocDTO getChiTietMonHoc() {
        return chiTietMonHoc;
    }

    public void setChiTietMonHoc(ChiTietMonHocDTO chiTietMonHoc) {
        this.chiTietMonHoc = chiTietMonHoc;
    }

    public HocSinhDTO getHocSinh() {
        return hocSinh;
    }

    public void setHocSinh(HocSinhDTO hocSinh) {
        this.hocSinh = hocSinh;
    }

    public float getDiemGiuaKy() {
        return diemGiuaKy;
    }

    public void setDiemGiuaKy(float diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    public float getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(float diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }

    public List<Float> getListDiemKTMieng() {
        return listDiemKTMieng;
    }

    public void setListDiemKTMieng(List<Float> listDiemKTMieng) {
        this.listDiemKTMieng = listDiemKTMieng;
    }

    public List<Float> getListDiemKT15() {
        return listDiemKT15;
    }

    public void setListDiemKT15(List<Float> listDiemKT15) {
        this.listDiemKT15 = listDiemKT15;
    }

    public List<Float> getListDiemKT1Tiet() {
        return listDiemKT1Tiet;
    }

    public void setListDiemKT1Tiet(List<Float> listDiemKT1Tiet) {
        this.listDiemKT1Tiet = listDiemKT1Tiet;
    }

}
