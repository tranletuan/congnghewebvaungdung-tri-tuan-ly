/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author HuuTri
 */
@Document
@XmlRootElement(name = "giaovien")
public class GiaoVienDTO extends AbstractStaffDTO {

    @Indexed(unique = true)
    private String maGiaoVien;

    public GiaoVienDTO() {}
    
    public GiaoVienDTO(String maGiaoVien, String hoTen, 
            int gioiTinh, Date ngaySinh, 
            String diaChi, Date ngayVaoLam) {
        this.maGiaoVien = maGiaoVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;     
        
    }
    @XmlElement
    public String getmaGiaoVien() {
        return maGiaoVien;
    }

    public void setmaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }
}
