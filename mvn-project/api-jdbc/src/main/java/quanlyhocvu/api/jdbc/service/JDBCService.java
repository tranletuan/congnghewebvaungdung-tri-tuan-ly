package quanlyhocvu.api.jdbc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import quanlyhocvu.api.jdbc.dao.AuthorityDAO;
import quanlyhocvu.api.jdbc.dao.UserDAO;
import quanlyhocvu.api.jdbc.dto.AuthorityDTO;
import quanlyhocvu.api.jdbc.dto.UserDTO;

/**
 * 
 * @author HuuTri
 */
public class JDBCService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    AuthorityDAO authorityDAO;

    //<editor-fold defaultstate="collapsed" desc="User services">
    public int deleteUser(String username) {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        authorityDAO.deleteAuthoritiesByUserName(username);
        return userDAO.delete(user);
    }

    public int createUser(UserDTO user) {
        return userDAO.create(user);
    }

    public int updateUser(UserDTO user) {
        return userDAO.update(user);
    }

    public List<UserDTO> getListUsers() {
        List<UserDTO> users = userDAO.getListUsers();
        for (UserDTO user : users) {
            user.setAuthorities(authorityDAO.getListAuthoritiesByUserName(user.getUsername()));
        }
        return users;
    }

    public UserDTO getUser(String username) {
        return userDAO.getUser(username);
    }
    
    public int setUserEnabled(String enabled, String username) {
        return userDAO.setEnabled(enabled, username);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="authority service">
    public int createAuthority(AuthorityDTO auth) {
        return authorityDAO.create(auth);
    }

    public int deleteAuthority(AuthorityDTO auth) {
        return authorityDAO.delete(auth);
    }

    public int setAuthorityFromParam(String username, String authority, String active) {
        return authorityDAO.setAuthorityFromParam(username, authority, active);
    }
    
    public int updateAuthority(AuthorityDTO old, AuthorityDTO auth) {
        return authorityDAO.update(old, auth);
    }

    public List<AuthorityDTO> getAllListAuthorities() {
        return authorityDAO.getListAuthorities();
    }

    public List<String> getListAuthoritiesByUserName(String username) {
        return authorityDAO.getListAuthoritiesByUserName(username);
    }

    public List<AuthorityDTO> getListAuthoritiesByAuthority(String authority) {
        return authorityDAO.getListAuthoritiesByAuthority(authority);
    }
    //</editor-fold>
}
