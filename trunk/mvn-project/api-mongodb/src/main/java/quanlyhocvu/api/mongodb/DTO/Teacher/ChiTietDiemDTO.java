/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DTO.Teacher;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Tuan
 */
@Document
public class ChiTietDiemDTO {
    
    @DBRef
    private DiemDTO diem;
    
    private float ktmieng;
    
    private float kt15;
    
    private float kt1tiet;
    
    private float diemTB;
}
