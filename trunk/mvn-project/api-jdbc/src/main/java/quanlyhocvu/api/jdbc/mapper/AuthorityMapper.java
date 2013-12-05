/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import quanlyhocvu.api.jdbc.dto.Authority;

/**
 *
 * @author HuuTri
 */
public class AuthorityMapper implements RowMapper{

    @Override
    public Authority mapRow(ResultSet rs, int i) throws SQLException {
        Authority auth = new Authority();
        
        auth.setAuthority(rs.getString("authority"));
        auth.setUserName(rs.getString("username"));
        
        return auth;
    }
    
}
