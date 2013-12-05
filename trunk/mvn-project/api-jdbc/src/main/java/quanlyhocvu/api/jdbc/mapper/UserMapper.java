/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import quanlyhocvu.api.jdbc.dto.User;

/**
 *
 * @author HuuTri
 */
public class UserMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getInt("enabled"));
        
        return user;
    }
    
}
