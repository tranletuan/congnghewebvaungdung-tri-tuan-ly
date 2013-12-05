 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.idao;

import java.util.List;
import javax.sql.DataSource;
import quanlyhocvu.api.jdbc.dto.User;

/**
 *
 * @author HuuTri
 */
public interface IUserDAO {
    public void setDataSource(DataSource datasource);
    public int create(User user);
    public int delete(User user);
    public int update(User user);
    public int setEnabled(String enabled, String username);
    public User getUser(String username);
    public List<User> getListUsers();
}
