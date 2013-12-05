/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import quanlyhocvu.api.jdbc.dto.User;
import quanlyhocvu.api.jdbc.idao.IUserDAO;
import quanlyhocvu.api.jdbc.mapper.UserMapper;

/**
 *
 * @author HuuTri
 */
public class UserDAO implements IUserDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource datasource) {
        this.dataSource = datasource;
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public int create(User user) {
        String sql = "insert into users(username, password, enabled) values(?,?,?)";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            user.getUsername(),
            user.getPassword(),
            user.getEnabled()});
    }

    @Override
    public int delete(User user) {
        String sql = "delete from users where username=?";
        return jdbcTemplate.update(
                sql,
                new Object[]{user.getUsername()});
    }

    @Override
    public int update(User user) {
        String sql = "update users set password=?, enabled=? where username=?";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            user.getPassword(),
            user.getEnabled(),
            user.getUsername()});
    }

    @Override
    public User getUser(String username) {
        String sql = "select * from users where username=?";
        return (User) jdbcTemplate.queryForObject(
                sql,
                new Object[]{username},
                new UserMapper());
    }

    @Override
    public List<User> getListUsers() {
        String sql = "select * from users";
        return (List<User>) jdbcTemplate.query(
                sql,
                new UserMapper());
    }

    @Override
    public int setEnabled(String enabled, String username) {
        String sql = "update users set enabled=? where username = ?";
        return jdbcTemplate.update(
                sql,
                new Object[]{
            Integer.parseInt(enabled),
            username
        });
    }
}
