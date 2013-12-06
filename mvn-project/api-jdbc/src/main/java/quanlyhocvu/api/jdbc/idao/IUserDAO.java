 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.idao;

import java.util.List;
import javax.sql.DataSource;
import quanlyhocvu.api.jdbc.dto.UserDTO;

/**
 *
 * @author HuuTri
 */
public interface IUserDAO {
    public void setDataSource(DataSource datasource);
    public int create(UserDTO user);
    public int delete(UserDTO user);
    public int update(UserDTO user);
    public int setEnabled(String enabled, String username);
    public UserDTO getUser(String username);
    public List<UserDTO> getListUsers();
}
