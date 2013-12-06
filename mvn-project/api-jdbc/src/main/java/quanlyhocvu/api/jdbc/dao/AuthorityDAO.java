package quanlyhocvu.api.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import quanlyhocvu.api.jdbc.dto.AuthorityDTO;
import quanlyhocvu.api.jdbc.idao.IAuthorityDAO;
import quanlyhocvu.api.jdbc.mapper.AuthorityMapper;

/**
 * 
 * @author HuuTri
 */
public class AuthorityDAO implements IAuthorityDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource datasource) {
        this.dataSource = datasource;
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public int delete(AuthorityDTO authority) {
        String sql = "delete from user_roles where username=? and authority=?";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            authority.getUsername(),
            authority.getAuthority()
        });
    }

    @Override
    public int update(AuthorityDTO old, AuthorityDTO authority) {
        String sql = "update user_roles set username=?, "
                + "authority=? where username=? and authority=?";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            old.getUsername(),
            old.getAuthority(),
            authority.getUsername(),
            authority.getAuthority()
        });
    }

    @Override
    public int create(AuthorityDTO authority) {
        String sql = "insert into user_roles(username, authority) values(?,?)";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            authority.getUsername(),
            authority.getAuthority()});
    }

    @Override
    public List<String> getListAuthoritiesByUserName(String username) {
        String sql = "select * from user_roles where username=?";
        List<AuthorityDTO> listAuth = (List<AuthorityDTO>) jdbcTemplate.query(
                sql,
                new Object[]{username},
                new AuthorityMapper());
        List<String> res = new ArrayList<String>();
        for (AuthorityDTO auth : listAuth) {
            res.add(auth.getAuthority());
        }
        return res;
    }

    @Override
    public List<AuthorityDTO> getListAuthoritiesByAuthority(String authority) {
        String sql = "select * from user_roles where authority=?";
        return (List<AuthorityDTO>) jdbcTemplate.queryForObject(
                sql,
                new Object[]{authority},
                new AuthorityMapper());
    }

    @Override
    public List<AuthorityDTO> getListAuthorities() {
        String sql = "select * from user_roles";
        return (List<AuthorityDTO>) jdbcTemplate.query(
                sql,
                new AuthorityMapper());
    }

    @Override
    public int setAuthorityFromParam(String username, String authority, String active) {
        AuthorityDTO auth = new AuthorityDTO();
        auth.setAuthority(authority);
        auth.setUserName(username);

        if (active.equals("0")) {
            return delete(auth);
        } else {
            return create(auth);
        }
    }

    @Override
    public int deleteAuthoritiesByUserName(String username) {
        String sql = "delete from user_roles where username=?";

        return jdbcTemplate.update(
                sql,
                new Object[]{username
        });
    }
}
