package ftc.shift_europe.sample.repositories;
import ftc.shift_europe.sample.models.Flag;
import ftc.shift_europe.sample.models.Route;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class RouteExtractor implements ResultSetExtractor<List<Route>> {
    private List<Flag> flags;
    @Override
    public List<Route> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Route> routes = new ArrayList<>();

        while (rs.next()) {
            int routeId = rs.getInt("ROUTE_ID");
            Route route;
            route = new Route();
            route.setId(routeId);
            route.setUserId(rs.getInt("USER_ID"));
            route.setPrice(rs.getInt("PRICE"));
            route.setRouteName(rs.getString("ROUTE_NAME"));
            routes.add(route);
        }
        return routes;
    }
}
