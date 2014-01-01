/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;

/**
 *
 * @author HuuTri
 */
@Document
@XmlRootElement(name = "hocsinh")
public class HocSinhDTO extends AbstractPersonDTO{
   private String maHocSinh;
   private Date ngayNhapHoc;
   private Date ngayNghiHoc;
   private String maLopHoc;
   private TrangThaiHS trangThaiHS;
   @DBRef
   private KhoiLopDTO khoiLopHienTai;

    /**
     * @return the trangThaiHS
     */
   @XmlElement
    public TrangThaiHS getTrangThaiHS() {
        return trangThaiHS;
    }

    /**
     * @param trangThaiHS the trangThaiHS to set
     */
    public void setTrangThaiHS(TrangThaiHS trangThaiHS) {
        this.trangThaiHS = trangThaiHS;
    }

    /**
     * @return the maLopHoc
     */
    @XmlElement
    public String getMaLopHoc() {
        return maLopHoc;
    }

    /**
     * @param maLopHoc the maLopHoc to set
     */
    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    /**
     * @return the khoiLopHienTai
     */
    @XmlElement
    public KhoiLopDTO getKhoiLopHienTai() {
        return khoiLopHienTai;
    }

    /**
     * @param khoiLopHienTai the khoiLopHienTai to set
     */
    public void setKhoiLopHienTai(KhoiLopDTO khoiLopHienTai) {
        this.khoiLopHienTai = khoiLopHienTai;
    }
   public enum TrangThaiHS{
       DaNghi,
       DaTotNghiep,
       DangHoc
   }

   public HocSinhDTO(){
       this.trangThaiHS = TrangThaiHS.DangHoc;
   };
   public HocSinhDTO(String id, String hoTen, int gioiTinh, Date ngaySinh, String diaChi, String maHocSinh, Date ngayNhapHoc, Date ngayNghiHoc){
     this.id = id;
     this.hoTen = hoTen;
     this.gioiTinh = gioiTinh;
     this.ngaySinh = ngaySinh;
     this.diaChi = diaChi;
     this.maHocSinh = maHocSinh;
     this.ngayNhapHoc = ngayNhapHoc;
     this.ngayNghiHoc = ngayNghiHoc;
     this.trangThaiHS = TrangThaiHS.DangHoc;
             
   };
   public HocSinhDTO(String hoTen, int gioiTinh, Date ngaySinh, String diaChi, String maHocSinh, Date ngayNhapHoc){
     this.hoTen = hoTen;
     this.gioiTinh = gioiTinh;
     this.ngaySinh = ngaySinh;
     this.diaChi = diaChi;
     this.maHocSinh = maHocSinh;
     this.ngayNhapHoc = ngayNhapHoc;  
     this.trangThaiHS = TrangThaiHS.DangHoc;
             
   };
   @XmlElement
    public String getmaHocSinh() {
        return maHocSinh;
    }

    public void setmaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    @XmlElement
    public Date getngayNhapHoc() {
        return ngayNhapHoc;
    }

    
    public void setngayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    @XmlElement
    public Date getngayNghiHoc() {
        return ngayNghiHoc;
    }

    public void setngayNghiHoc(Date ngayNghiHoc) {
        this.ngayNghiHoc = ngayNghiHoc;
    }        
    
    /*
    Check whether this student can upgrade from grade lower to higher
    */
    public boolean canUpgrade(){
        return true;
    }
    
    /*
    Check whether this student can graduate or not
    */
    public boolean canGraduate(){
        return true;
    }

    
}
