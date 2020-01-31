package ftc.shift_europe.sample.repositories;
import ftc.shift_europe.sample.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();

        while (rs.next()) {
            int userId = rs.getInt("USER_ID");

            User user = new User();
            user.setId(userId);
            user.setLogin(rs.getString("USER_LOGIN"));
            users.add(user);
        }

        return users;
    }
}
