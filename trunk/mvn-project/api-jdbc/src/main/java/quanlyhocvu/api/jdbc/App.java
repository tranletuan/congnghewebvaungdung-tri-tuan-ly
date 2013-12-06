package quanlyhocvu.api.jdbc;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import quanlyhocvu.api.jdbc.dto.UserDTO;
import quanlyhocvu.api.jdbc.service.JDBCService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       
        ApplicationContext ctx = new GenericXmlApplicationContext(
                "jdbcApplicationContext.xml");
        JDBCService jdbcService = (JDBCService) ctx.getBean("jdbcService");
        List<UserDTO> users = jdbcService.getListUsers();
        for (UserDTO user: users) {
            System.out.println(user.toString());
        }
    }
}
