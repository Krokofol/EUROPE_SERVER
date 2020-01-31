package ftc.shift_europe.sample.repositories;

import ftc.shift_europe.sample.models.Flag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlagExtractor implements ResultSetExtractor<List<Flag>> {
    @Override
    public List<Flag> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Flag> flags = new ArrayList<>();

        while (rs.next()) {
            int flagId = rs.getInt("FLAG_ID");
            Flag flag;
            flag = new Flag();
            flag.setId(flagId);
            flag.routeName = rs.getString("ROUTE_NAME");
            flag.setName(rs.getString("FLAG_NAME"));
            flag.setX(rs.getDouble("X"));
            flag.setY(rs.getDouble("Y"));
            flag.setPrice(rs.getInt("PRICE"));
            flag.setPrew(rs.getInt("PREW"));
            flags.add(flag);
        }
        return flags;
    }
}
