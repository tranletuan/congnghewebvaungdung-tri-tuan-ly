/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.Teacher;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.base.AbstractObjectDTO;

/**
 *
 * @author Tuan
 */
@Document
public class ChiTietDiemDTO extends AbstractObjectDTO{
    
    @DBRef
    private DiemDTO diem;
    
    private float ktmieng_1 = -1;
    private float ktmieng_2 = -1;
    private float ktmieng_3 = -1;
    
    private float kt15_1 = -1;
    private float kt15_2 = -1;
    private float kt15_3 = -1;
    
    private float kt1tiet_1 = -1;
    private float kt1tiet_2 = -1;

    
    
    private float diemGiuaKy = -1;
    private float diemCuoiKy = -1;
    private float diemTB = -1;

    
    
    public DiemDTO getDiem() {
        return diem;
    }

    public void setDiem(DiemDTO diem) {
        this.diem = diem;
    }

    public float getKtmieng_1() {
        return ktmieng_1;
    }

    public void setKtmieng_1(float ktmieng_1) {
        this.ktmieng_1 = ktmieng_1;
    }

    public float getKtmieng_2() {
        return ktmieng_2;
    }

    public void setKtmieng_2(float ktmieng_2) {
        this.ktmieng_2 = ktmieng_2;
    }

    public float getKtmieng_3() {
        return ktmieng_3;
    }

    public void setKtmieng_3(float ktmieng_3) {
        this.ktmieng_3 = ktmieng_3;
    }

    public float getKt15_1() {
        return kt15_1;
    }

    public void setKt15_1(float kt15_1) {
        this.kt15_1 = kt15_1;
    }

    public float getKt15_2() {
        return kt15_2;
    }

    public void setKt15_2(float kt15_2) {
        this.kt15_2 = kt15_2;
    }

    public float getKt15_3() {
        return kt15_3;
    }

    public void setKt15_3(float kt15_3) {
        this.kt15_3 = kt15_3;
    }

    public float getKt1tiet_1() {
        return kt1tiet_1;
    }

    public void setKt1tiet_1(float kt1tiet_1) {
        this.kt1tiet_1 = kt1tiet_1;
    }

    public float getKt1tiet_2() {
        return kt1tiet_2;
    }

    public void setKt1tiet_2(float kt1tiet_2) {
        this.kt1tiet_2 = kt1tiet_2;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
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
}
