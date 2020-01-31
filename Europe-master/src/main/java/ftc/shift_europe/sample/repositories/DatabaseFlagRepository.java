package ftc.shift_europe.sample.repositories;//package ftc.shift.sample.repositories;

import ftc.shift_europe.sample.models.User;
import ftc.shift_europe.sample.models.Route;
import ftc.shift_europe.sample.models.Flag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseFlagRepository implements FlagRepository, RouteRepository, UserRepository{
    private NamedParameterJdbcTemplate jdbcTemplate;
    private FlagExtractor flagExtractor;
    private RouteExtractor routeExtractor;
    private UserExtractor userExtractor;

    @Autowired
    public DatabaseFlagRepository(NamedParameterJdbcTemplate jdbcTemplate, FlagExtractor flagExtractor,
                                  RouteExtractor routeExtractor, UserExtractor userExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.flagExtractor = flagExtractor;
        this.routeExtractor = routeExtractor;
        this.userExtractor = userExtractor;
    }

    @PostConstruct
    public void initialize() {
        String createGenerateFlagIdSequenceSql = "create sequence Flag_ID_GENERATOR";
        String createGenerateRouteIdSequenceSql = "create sequence Route_ID_GENERATOR";
        String createGenerateUserIdSequenceSql = "create sequence User_ID_GENERATOR";

        String createFlagTableSql = "create table FLAGS (" +
                "FLAG_ID   INTEGER default FLAG_ID_GENERATOR.nextval," +
                "FLAG_NAME  VARCHAR(64)," +
                "ROUTE_ID  INTEGER," +
                "ROUTE_NAME  VARCHAR(64)," +
                "X         DOUBLE," +
                "Y         DOUBLE," +
                "PRICE     INTEGER," +
                "PREW      INTEGER" +
                ");";
        String createRouteTableSql = "create table ROUTES (" +
                "ROUTE_ID  INTEGER default ROUTE_ID_GENERATOR.nextval," +
                "USER_ID   INTEGER," +
                "ROUTE_NAME  VARCHAR(64)," +
                "PRICE      INTEGER" +
                ");";
        String createUserTableSql = "create table USERS (" +
                "USER_ID     INTEGER default USER_ID_GENERATOR.nextval," +
                "USER_LOGIN  VARCHAR(64)" +
                ");";
        jdbcTemplate.update(createGenerateFlagIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createGenerateRouteIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createGenerateUserIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createFlagTableSql, new MapSqlParameterSource());
        jdbcTemplate.update(createRouteTableSql, new MapSqlParameterSource());
        jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //-------------------------------------------------------------------------------------------\
        createUser(new User("Mashnila"));
        createRoute("Mashnila", new Route("Mashnila", "I_LOVE_C++"));
        createFlag("Mashnila", "I_LOVE_C++", new Flag("A", 54.847260, 83.092349, 25, 1));
        createFlag("Mashnila", "I_LOVE_C++", new Flag("B", 54.842958, 83.090939, 30, 1));
        createFlag("Mashnila", "I_LOVE_C++", new Flag("C", 54.839313, 83.095359, 500, 2));
        Flag flag = fetchFlag("A");
        Integer i = 0;
        //-------------------------------------------------------------------------------------------
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Integer getUserId(String login){
        String sql = "select USER_ID, USER_LOGIN " +
                "from USERS " +
                "where USER_LOGIN = :userLogin";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userLogin", login);
        List<User> users = jdbcTemplate.query(sql, params, userExtractor); // user
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0).getId();
    }
    public Integer getRouteId(Integer userId, Integer routeNumber){
        String sql = "select USER_ID, ROUTE_ID, PRICE, ROUTE_NAME " +
                "from ROUTES " +
                "where USER_ID = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        List<Route> array = jdbcTemplate.query(sql, params, routeExtractor);
        return array.get(routeNumber - 1).getRouteId();
    }
    public Integer getFlagId(Integer routeId, Integer flagNumber){
        String sql = "select ROUTE_ID, FLAG_ID, ROUTE_NAME, X, Y, PRICE, PREW " +
                "from FLAGS " +
                "where ROUTE_ID = :routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        List<Flag> array = jdbcTemplate.query(sql, params, flagExtractor);
        return array.get(flagNumber - 1).getId();
    }

    public Collection<Route> getAllRoutes(String userLogin) {
        Integer userId = getUserId(userLogin);
        String sql = "select USER_ID, ROUTE_ID, PRICE, ROUTE_NAME  " +
                "from ROUTES " +
                "where USER_ID = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        return jdbcTemplate.query(sql, params, routeExtractor);
    }

    public Collection<User> getAllUsers() {
        String sql = "select USER_ID, USER_LOGIN " +
                "from USERS ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, userExtractor);
    }

    public List<Flag> getAllFlags(String routeName) {
        Integer routeId = getRouteIdByName(routeName);
        String sql = "select ROUTE_ID, FLAG_NAME, ROUTE_NAME, FLAG_ID, X, Y, PRICE, PREW " +
                "from FLAGS " +
                "where ROUTE_ID = :routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        return jdbcTemplate.query(sql, params, flagExtractor);
    }

    public Boolean checkUser(String userLogin) {
        String sql = "select USER_ID, USER_LOGIN " +
                "from USERS " +
                "where USER_LOGIN = :userLogin";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userLogin", userLogin);
        List<User> users = jdbcTemplate.query(sql, params, userExtractor); // user
        return !users.isEmpty();
    }

    @Override
    public Flag fetchFlag(String flagName) {
        Integer flagId = getFlagIdByName(flagName);
        String sql = "select ROUTE_ID, ROUTE_NAME, FLAG_ID, FLAG_NAME, X, Y, PRICE, PREW " +
                "from FLAGS " +
                "where FLAG_ID = :flagId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("flagId", flagId);
        List<Flag> flags = jdbcTemplate.query(sql, params, flagExtractor);
        if (flags.isEmpty()) {
            return null;
        }
        return flags.get(0);
    }


    public Integer getRouteIdByName(String routeName){
        String sql = "select USER_ID, ROUTE_ID, ROUTE_NAME, PRICE " +
                "from ROUTES " +
                "where ROUTE_NAME = :routeName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeName", routeName);
        List<Route> routes = jdbcTemplate.query(sql, params, routeExtractor); // user
        if (routes.isEmpty()) {
            return null;
        }
        return routes.get(0).getRouteId();
    }
    public Integer getFlagIdByName(String flagName){
        String sql = "select FLAG_ID, FLAG_NAME, ROUTE_NAME, X, Y, PRICE, PREW " +
                "from FLAGS " +
                "where FLAG_NAME = :flagName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("flagName", flagName);
        List<Flag> flags = jdbcTemplate.query(sql, params, flagExtractor); // user
        if (flags.isEmpty()) {
            return null;
        }
        return flags.get(0).getId();
    }

    public Route fetchRoute(String routeName) {
        Integer routeId = getRouteIdByName(routeName);
        String sql = "select ROUTE_ID, USER_ID, PRICE, ROUTE_NAME  " +
                "from ROUTES " +
                "where ROUTE_ID = :routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        List<Route> routes = jdbcTemplate.query(sql, params, routeExtractor);
        if (routes.isEmpty()) {
            return null;
        }
        routes.get(0).setCollection(getAllFlags(routeName));
        return routes.get(0);
    }

    public void deleteRoute(String routeName) {
        Integer routeId = getRouteIdByName(routeName);
        String deleteRouteSql = "delete from ROUTES where ROUTE_ID=:routeId";
        String deleteFlagSql = "delete from FLAGS where ROUTE_ID=:routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        jdbcTemplate.update(deleteRouteSql, params);
        jdbcTemplate.update(deleteFlagSql, params);
    }


    public void deleteFlag(String flagName) {
        Integer flagId = getFlagIdByName(flagName);
        String routeName = fetchFlag(flagName).routeName;
        String userName = fetchRoute(routeName).getUserName();

        Integer userId = getUserId(userName);
        Integer routeId = getRouteIdByName(routeName);
        //price для route
        Flag flag = fetchFlag(flagName);
        Route route = fetchRoute(routeName);
        route.setPrice(route.getPrice() - flag.getPrice());
        updateRoute(userName, routeName, route);
        //price для route

        String deleteFlagSql = "delete from FLAGS where FLAG_ID=:flagId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("flagId", flagId);
        jdbcTemplate.update(deleteFlagSql, params);
    }


    @Override
    public Flag createFlag(String userName, String routeName, Flag flag) {
        Integer userId = getUserId(userName);
        Integer routeId = getRouteIdByName(routeName);
        String insertFlagSql = "insert into FLAGS (ROUTE_ID, ROUTE_NAME, FLAG_NAME, X, Y, PRICE, PREW) values" +
                "(:routeId, :routeName, :flagName, :x, :y, :price, :prew)";
        MapSqlParameterSource flagParams = new MapSqlParameterSource()
                .addValue("routeId", routeId)
                .addValue("routeName", routeName)
                .addValue("flagName", flag.getName())
                .addValue("x", flag.getX())
                .addValue("y", flag.getY())
                .addValue("price", flag.getPrice())
                .addValue("prew", flag.getPrew());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertFlagSql, flagParams, generatedKeyHolder);
        Integer flagId = Integer.parseInt(generatedKeyHolder.getKeys().get("FLAG_ID").toString());
        flag.setId(flagId);

        //price для route
        Route route = fetchRoute(routeName);
        route.setPrice(route.getPrice() + flag.getPrice());
        updateRoute(userName, routeName, route);
        //price для route

        return flag;
    }

    public Route createRoute(String userName, Route route) {
        Integer userId = getUserId(userName);
        String insertRouteSql = "insert into ROUTES (USER_ID, PRICE, ROUTE_NAME) values (:userId, :price, :routeName)";
        MapSqlParameterSource routeParams = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("price", route.getPrice())
                .addValue("routeName", route.getRouteName());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertRouteSql, routeParams, generatedKeyHolder);
        Integer routeId = Integer.parseInt(generatedKeyHolder.getKeys().get("ROUTE_ID").toString());
        route.setId(routeId);
        return route;
    }

    public User createUser(User user) {
        String insertUserSql = "insert into USERS (USER_LOGIN) values (:userLogin)";
        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("userLogin", user.getLogin());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertUserSql, userParams, generatedKeyHolder);
        Integer userId = Integer.parseInt(generatedKeyHolder.getKeys().get("USER_ID").toString());
        user.setId(userId);
        return user;
    }

    @Override
    public Flag updateFlag(String userName, String routeName, String flagName, Flag flag) {
        Integer userId = getUserId(userName);
        Integer routeId = getRouteIdByName(routeName);
        Integer flagId = getFlagIdByName(flagName);
        //price для route
        Flag flag_1 = fetchFlag(flagName);
        flag.setId(flag_1.getId());
        Route route = fetchRoute(routeName);
        route.setPrice(route.getPrice() - flag_1.getPrice() + flag.getPrice());
        updateRoute(userName, routeName, route);
        //price для route
        String updateFlagSql = "update FLAGS " +
                "set ROUTE_ID = :routeId, " +
                "ROUTE_NAME = :routeName," +
                "X = :x, " +
                "Y = :y, " +
                "PRICE = :price, " +
                "PREW = :prew " +
                "where FLAG_ID = :flagId";
        MapSqlParameterSource flagParams = new MapSqlParameterSource()
                .addValue("flagId", flagId)
                .addValue("routeId", routeId)
                .addValue("routeName", routeName)
                .addValue("x", flag.getX())
                .addValue("y", flag.getY())
                .addValue("price", flag.getPrice())
                .addValue("prew", flag.getPrew());
        jdbcTemplate.update(updateFlagSql, flagParams);
        return flag;
    }

    public Route updateRoute(String userName, String routeName, Route route) {
        Integer userId = getUserId(userName);
        Integer routeId = getRouteIdByName(routeName);
        //Route route_1 = fetchRoute(routeName);
        route.setId(routeId);
        String updateRouteSql = "update ROUTES " +
                "set USER_ID = :userId, " +
                "PRICE = :price " +
                "where ROUTE_ID = :routeId";
        MapSqlParameterSource routeParams = new MapSqlParameterSource()
                .addValue("routeId", routeId)
                .addValue("userId", userId)
                .addValue("price", route.getPrice());
        jdbcTemplate.update(updateRouteSql, routeParams);
        return route;
    }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++
