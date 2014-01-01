/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DTO.staff;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import quanlyhocvu.api.mongodb.DTO.base.AbstractPersonDTO;

/**
 *
 * @author HuuTri
 */
public class AbstractStaffDTO extends AbstractPersonDTO{
    protected Date ngayVaoLam;
    protected Date ngayNghiViec;

    /**
     * @return the ngayVaoLam
     */
    @XmlElement
    public Date getngayVaoLam() {
        return ngayVaoLam;
    }

    /**
     * @param ngayVaoLam the ngayVaoLam to set
     */
    public void setngayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    /**
     * @return the ngayNghiViec
     */
    @XmlElement
    public Date getngayNghiViec() {
        return ngayNghiViec;
    }

    /**
     * @param ngayNghiViec the ngayNghiViec to set
     */
    public void setngayNghiViec(Date ngayNghiViec) {
        this.ngayNghiViec = ngayNghiViec;
    }
}
