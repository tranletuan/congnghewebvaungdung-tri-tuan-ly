package quanlyhocvu.api.jdbc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import quanlyhocvu.api.jdbc.dao.AuthorityDAO;
import quanlyhocvu.api.jdbc.dao.UserDAO;
import quanlyhocvu.api.jdbc.dto.Authority;
import quanlyhocvu.api.jdbc.dto.User;

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
        User user = new User();
        user.setUsername(username);
        authorityDAO.deleteAuthoritiesByUserName(username);
        return userDAO.delete(user);
    }

    public int createUser(User user) {
        return userDAO.create(user);
    }

    public int updateUser(User user) {
        return userDAO.update(user);
    }

    public List<User> getListUsers() {
        List<User> users = userDAO.getListUsers();
        for (User user : users) {
            user.setAuthorities(authorityDAO.getListAuthoritiesByUserName(user.getUsername()));
        }
        return users;
    }

    public User getUser(String username) {
        return userDAO.getUser(username);
    }
    
    public int setUserEnabled(String enabled, String username) {
        return userDAO.setEnabled(enabled, username);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="authority service">
    public int createAuthority(Authority auth) {
        return authorityDAO.create(auth);
    }

    public int deleteAuthority(Authority auth) {
        return authorityDAO.delete(auth);
    }

    public int setAuthorityFromParam(String username, String authority, String active) {
        return authorityDAO.setAuthorityFromParam(username, authority, active);
    }
    
    public int updateAuthority(Authority old, Authority auth) {
        return authorityDAO.update(old, auth);
    }

    public List<Authority> getAllListAuthorities() {
        return authorityDAO.getListAuthorities();
    }

    public List<String> getListAuthoritiesByUserName(String username) {
        return authorityDAO.getListAuthoritiesByUserName(username);
    }

    public List<Authority> getListAuthoritiesByAuthority(String authority) {
        return authorityDAO.getListAuthoritiesByAuthority(authority);
    }
    //</editor-fold>
}
