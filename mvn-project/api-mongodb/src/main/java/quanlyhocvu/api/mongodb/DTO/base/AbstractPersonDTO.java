/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.base;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author HuuTri
 */
public class AbstractPersonDTO extends AbstractObjectDTO{
    protected String hoTen;
    protected int gioiTinh;
    protected Date ngaySinh;
    protected String diaChi;

    @XmlElement
    public String gethoTen() {
        return hoTen;
    }

    public void sethoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @XmlElement
    public int getgioiTinh() {
        return gioiTinh;
    }

    public void setgioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @XmlElement
    public Date getngaySinh() {
        return ngaySinh;
    }

    public void setngaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @XmlElement
    public String getdiaChi() {
        return diaChi;
    }

    public void setdiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
