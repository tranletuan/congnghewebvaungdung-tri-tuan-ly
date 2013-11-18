/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import quanlyhocvu.api.mongodb.DTO.base.AbstractPersonDTO;

/**
 *
 * @author HuuTri
 */
public class AbstractStaffDTO extends AbstractPersonDTO{
    protected Date NgayVaoLam;
    protected Date NgayNghiViec;

    /**
     * @return the NgayVaoLam
     */
    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    /**
     * @param NgayVaoLam the NgayVaoLam to set
     */
    public void setNgayVaoLam(Date NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    /**
     * @return the NgayNghiViec
     */
    public Date getNgayNghiViec() {
        return NgayNghiViec;
    }

    /**
     * @param NgayNghiViec the NgayNghiViec to set
     */
    public void setNgayNghiViec(Date NgayNghiViec) {
        this.NgayNghiViec = NgayNghiViec;
    }
}
