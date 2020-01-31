package ftc.shift_europe.sample.api;


import ftc.shift_europe.sample.models.Flag;
import ftc.shift_europe.sample.models.Route;
import ftc.shift_europe.sample.models.User;
import ftc.shift_europe.sample.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//controller
//сделать с флагом и другими классами вместо этого всего
//UserId - Mashnila
//у путей - имена
@RestController //контроллер - запросы
public class AllController {
    private Service service;


    @Autowired //пишем на конструкторе и полях, чтобы бут прописал зависимости сам (сам связал)
    public AllController(Service service) {
        this.service = service;
    }

    //FLAGCONTROLLER********************************************************************************************************************************************************************

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @PostMapping("flag/create") //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Flag> createFlag(
            @RequestHeader("userId") String userLog,
            @RequestHeader("routeId") String routeName,
            @RequestBody Flag flag) {
        Flag result = service.createFlag(userLog,routeName, flag);
        return ResponseEntity.ok(result);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //отвечает на запрос о получении инфы по флагу, возвращает тело флага
    @GetMapping("flag/get/{flagId}")//отвечает на гет запрос
    public ResponseEntity<Flag> readFlag(
            @PathVariable String flagId) {
        Flag flag = service.provideFlag(flagId);
        return ResponseEntity.ok(flag);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //изменение флага -стоимость, имя, мб координаты или номер(если это будет нужно)
    @PatchMapping("flag/update/{flagId}")//Отвечает на все изменения - имени, стоимости
    public ResponseEntity<Flag> updateFlag(
            @RequestHeader("userId") String userLog,
            @RequestHeader("routeId") String routeName,
            @PathVariable String flagId,
            @RequestBody Flag flag) {
        Flag updatedFlag = service.updateFlag(userLog, routeName,flagId, flag);
        return ResponseEntity.ok(updatedFlag);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @DeleteMapping("flag/delete/{flagId}")
    public ResponseEntity<Void> deleteFlag(
            @PathVariable String flagId) {
        service.deleteFlag(flagId);
        return ResponseEntity.ok().build();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++





//ROUTECONTROLLER*******************************************************************************************************************************************************************

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @GetMapping("user/get/list-of/flags/{routeName}")
    public ResponseEntity<Collection<Flag>> listFlags(
            //@RequestHeader("routeName") String routeName,
            @PathVariable String routeName){
        Collection<Flag> flags = service.provideFlags(routeName);
        return ResponseEntity.ok(flags);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @PostMapping("route/create") //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Route> createRoute(
            @RequestHeader("userId") String userLog,
            @RequestBody Route route) {
        Route result = service.createRoute(userLog,route);
        return ResponseEntity.ok(result);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //отвечает на запрос о получении инфы по флагу, возвращает тело флага
    @GetMapping("route/get/{routeId}")//отвечает на гет запрос
    public ResponseEntity<Route> readRoute(
            @RequestHeader("userId") String userId,
            @PathVariable String routeName) {
        Route route = service.provideRoute(routeName);
        return ResponseEntity.ok(route);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это
    //изменение флага -стоимость, имя, мб координаты или номер(если это будет нужно)
    @PatchMapping("route/update/{routeId}")//любой запрос
    public ResponseEntity<Route> updateRoute(
            @RequestHeader("userId") String userLog,
            @PathVariable String routeId,
            @RequestBody Route route)
    {
        Route updatedRoute = service.updateRoute(userLog, routeId, route);
        return ResponseEntity.ok(updatedRoute);
    }
    //хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это_хз_зачем_это


    @DeleteMapping("route/delete/{routeId}")
    public ResponseEntity<?> deleteRoute(
            @RequestHeader("userId") String userLog,
            @PathVariable String routeId) {
        service.deleteRoute(routeId);
        return ResponseEntity.ok().build();
    }

//USERCONTROLLER********************************************************************************************************************************************************************


    /*@GetMapping("user/check/login/{userLog}") //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Collection<Route>> checkUser(
            @PathVariable String userLog) {
        Collection<Route> routes;
        if(service.checkUser(userLog))routes = service.provideRoutes(userLog);
        else routes=null;
        //boolean result = service.checkUser(userLog);
        return ResponseEntity.ok(routes);// а здесь нужно вернуть переход на новую страничку
    }*/
    @GetMapping("user/get/list-of/routes/{userLog}")
    public ResponseEntity<Collection<Route>> checkUser(
            @PathVariable String userLog) {
        Collection<Route> routes;
        if(service.checkUser(userLog))routes = service.provideRoutes(userLog);
        else routes=null;
        //boolean result = service.checkUser(userLog);
        return ResponseEntity.ok(routes);// а здесь нужно вернуть переход на новую страничку
    }

    /*@RequestMapping("/")
    public String startPage(Model model) {
        return "index.html";
    }КАК ЗАПУСТИТЬ СТАРТОВУЮ СТРАНИЦУ
    */
}










