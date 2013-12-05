/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.idao;

import java.util.List;
import javax.sql.DataSource;
import quanlyhocvu.api.jdbc.dto.Authority;

/**
 *
 * @author HuuTri
 */
public interface IAuthorityDAO {
    
    /**
     * Cap nhat datasource
     * @param datasource 
     */
    public void setDataSource(DataSource datasource);
    
    /**
     * xoa authority
     * @param authority
     * @return 
     */
    public int delete(Authority authority);
    
    /**
     * cap nhat authority old thanh authority moi
     * @param old
     * @param authority
     * @return 
     */
    public int update(Authority old, Authority authority);
    
    /**
     * tao mot authority moi
     * @param authority
     * @return 
     */
    public int create(Authority authority);
   
    /**
     * neu active = 0, xoa cap username-authority, nguoc lai them moi
     * @param username 
     * @param authority
     * @param active mang gia tri 0,1
     * @return 
     */
    public int setAuthorityFromParam(String username, String authority, String active);
    
    /**
     * lay toan bo gia tri trong bang authority
     * @return 
     */
    public List<Authority> getListAuthorities();
    
    /**
     * Lay danh sach authority theo usernam
     * @param username
     * @return 
     */
    public List<String> getListAuthoritiesByUserName(String username);
    
    /**
     * Lay toan bo danh sach authority theo gia tri cua authority
     * @param authority
     * @return 
     */
    public List<Authority> getListAuthoritiesByAuthority(String authority);
    
    /**
     * Xoa toan bo cac authorities co username
     * @param username
     * @return 
     */
    public int deleteAuthoritiesByUserName(String username);
}
