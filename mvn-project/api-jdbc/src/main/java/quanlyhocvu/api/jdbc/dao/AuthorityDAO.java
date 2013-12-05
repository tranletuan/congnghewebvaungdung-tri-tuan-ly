package quanlyhocvu.api.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import quanlyhocvu.api.jdbc.dto.Authority;
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
    public int delete(Authority authority) {
        String sql = "delete from authorities where username=? and authority=?";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            authority.getUsername(),
            authority.getAuthority()
        });
    }

    @Override
    public int update(Authority old, Authority authority) {
        String sql = "update authorites set username=?, "
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
    public int create(Authority authority) {
        String sql = "insert into authorities(username, authority) values(?,?)";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            authority.getUsername(),
            authority.getAuthority()});
    }

    @Override
    public List<String> getListAuthoritiesByUserName(String username) {
        String sql = "select * from authorities where username=?";
        List<Authority> listAuth = (List<Authority>) jdbcTemplate.query(
                sql,
                new Object[]{username},
                new AuthorityMapper());
        List<String> res = new ArrayList<String>();
        for (Authority auth : listAuth) {
            res.add(auth.getAuthority());
        }
        return res;
    }

    @Override
    public List<Authority> getListAuthoritiesByAuthority(String authority) {
        String sql = "select * from authorities where authority=?";
        return (List<Authority>) jdbcTemplate.queryForObject(
                sql,
                new Object[]{authority},
                new AuthorityMapper());
    }

    @Override
    public List<Authority> getListAuthorities() {
        String sql = "select * from authorities";
        return (List<Authority>) jdbcTemplate.query(
                sql,
                new AuthorityMapper());
    }

    @Override
    public int setAuthorityFromParam(String username, String authority, String active) {
        Authority auth = new Authority();
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
        String sql = "delete from authorities where username=?";

        return jdbcTemplate.update(
                sql,
                new Object[]{username
        });
    }
}
