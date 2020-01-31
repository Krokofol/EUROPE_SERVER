package ftc.shift_europe.sample.repositories;

import ftc.shift_europe.sample.models.Route;

import java.util.Collection;


public interface RouteRepository {

    Route fetchRoute(String routeName);

    Route updateRoute(String userLogin,String routeName, Route route);

    void deleteRoute(String routeName);

    Route createRoute(String userLogin, Route route);

    Collection<Route> getAllRoutes(String userLogin);


}