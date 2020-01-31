package ftc.shift_europe.sample.services;

import ftc.shift_europe.sample.models.Flag;
import ftc.shift_europe.sample.models.Route;


import ftc.shift_europe.sample.repositories.FlagRepository;
import ftc.shift_europe.sample.repositories.RouteRepository;
import ftc.shift_europe.sample.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.List;

//какая-то хрень которая просто вызывает другие методы, типо вспомогательный класс
@org.springframework.stereotype.Service
public class Service {
    private final FlagRepository flagRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;


    @Autowired
    public Service(FlagRepository flagRepository,RouteRepository routeRepository,UserRepository userRepository) {
        this.flagRepository = flagRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
    }
    public Flag createFlag(String userName, String routeName,  Flag flag) {
        return flagRepository.createFlag(userName,routeName, flag);
    }
    public Flag provideFlag(String flagname) {
        return flagRepository.fetchFlag(flagname);
    }
    public List<Flag> provideFlags(String routeName) {
        return flagRepository.getAllFlags(routeName);
    }

    public Flag updateFlag(String userName, String routeName, String flagname, Flag flag) {
        return flagRepository.updateFlag(userName,routeName, flagname, flag);
    }

//    public Flag updateFlagName(String userId,String routeId, String flagId, String flagName) {
//        return flagRepository.updateFlagName(userId,routeId, flagId, flagName);
//    }
//
//    public Flag updateFlagCost(String userId,String routeId, String flagId, String flagCost) {
//        return flagRepository.updateFlagCost(userId, routeId, flagId, flagCost);
//    }
//
//    public void updateFlagPositionUp(String userId,String routeId, String flagId) {
//        flagRepository.goupFlag(userId,routeId, flagId);
//    }
//
//    public void updateFlagPositionDown(String userId,String routeId, String flagId) {
//        flagRepository.godownFlag(userId,routeId, flagId);
//    }

    public void deleteFlag(String flagName) {
        flagRepository.deleteFlag(flagName);//route.java
    }

//    public Collection<Flag> provideFlags(String userId,String routeId) {
//        return flagRepository.getAllFlags(userId,routeId);//route.java
//    }
//ROUTE*************************************************************************
    public Route createRoute(String userName, Route route) {
        return routeRepository.createRoute(userName, route);
    }

    public Route provideRoute(String routeId) {
        return routeRepository.fetchRoute(routeId);
    }

    public Route updateRoute(String userName, String routeName, Route route) {
        return routeRepository.updateRoute(userName, routeName, route);
    }

//        public Route updateRouteName(String userId, String routeId, String routeName) {
//            return routeRepository.updateRouteName(userId, routeId, routeName);
//        }
//
//        public Route updateRouteCost(String userId, String routeId, String routeCost) {
//            return routeRepository.updateRouteCost(userId, routeId, routeCost);
//        }
//
//
//
//        public Route updateRoutePositionUp(String userId, String routeId) {
//            return routeRepository.moveRouteUp(userId, routeId);
//        }
//
//        public Route updateRoutePositionDown(String userId, String routeId) {
//            return routeRepository.moveRouteDown(userId, routeId);
//        }
//
    public void deleteRoute(String routeName) {
        routeRepository.deleteRoute(routeName);
    }

//        public void deleteFlags(String userId, String routeId) {
//            routeRepository.deleteRouteFlags(userId, routeId);
//        }
        //USER***************************************************************************

    public Collection<Route> provideRoutes(String userId) {
        return routeRepository.getAllRoutes(userId);
    }

    public Boolean checkUser(String userLogin) {
        return userRepository.checkUser(userLogin);
    }

       // public void deleteRoutes(String userId) {
        //    routeRepository.deleteUserRoutes(userId);
        //}
}
