/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import quanlyhocvu.api.mongodb.DTO.base.AbstractPersonDTO;

/**
 *
 * @author HuuTri
 */
public class AbstractStaffDTO extends AbstractPersonDTO{
    protected String NgayVaoLam;
    protected String NgayNghiViec;

    /**
     * @return the NgayVaoLam
     */
    public String getNgayVaoLam() {
        return NgayVaoLam;
    }

    /**
     * @param NgayVaoLam the NgayVaoLam to set
     */
    public void setNgayVaoLam(String NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    /**
     * @return the NgayNghiViec
     */
    public String getNgayNghiViec() {
        return NgayNghiViec;
    }

    /**
     * @param NgayNghiViec the NgayNghiViec to set
     */
    public void setNgayNghiViec(String NgayNghiViec) {
        this.NgayNghiViec = NgayNghiViec;
    }
}
