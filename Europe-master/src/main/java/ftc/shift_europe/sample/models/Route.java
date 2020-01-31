package ftc.shift_europe.sample.models;

import java.util.Collection;
import java.util.List;

public class Route {
    private Integer routeId;//номер в базе данных (используется только в поиске)
    private Integer userId;//номер в базе данных (используется только в поиске)
    private String userName;//login
    private Integer price;//цена
    private String routeName;//route_name
    private List<Flag> flagList;

    //МЕТОДЫ****************************************************
    public Route(){};

    public Route(String userName, String routeName){
        this.userId = userId;
        this.routeName = routeName;
        this.price = 0;
    }

    public Integer getRouteId(){
        return this.routeId;
    }

    public void setId(Integer routeId){
        this.routeId = routeId;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public Integer getPrice(){
        return this.price;
    }

    public String getRouteName(){return this.routeName;}
    public void setRouteName(String name){
        this.routeName = name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public String getUserName(){return this.userName; }
    public void setUserName(){this.userName = userName; }

    public void setCollection(List<Flag> listFlag){
        this.flagList = listFlag;
    }
    //что нужно вызывать при созданиее - метод у маршрута или метод у флага? если у флага, как обновлять маршрут? если у маршрута, что и как возвращать?
    //и что возвращать при изменении стимости? ведь тогда обновляется и у маршрута,как вернуть и флаг и маршрут?
    //наверное будет вызываться у флага и просто обновлять потом маршрут


}


