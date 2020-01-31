package ftc.shift_europe.sample.models;

public class Flag {
    private Integer flagId;
    private String flagName;
    private Double x;
    private Double y;
    private Integer price;
    private Integer prewFlagId;

    //МЕТОДЫ*******************************
    public String routeName;

    public Flag(String name, Double x, Double y, Integer price, Integer prewFlagId) {
        this.flagName = name;
        this.flagId = flagId;
        this.x = x;
        this.y = y;
        this.price = price;
        //this.nextFlagId = flagId; //значит, что он конечный, если надо в середину, то передвинем через goup и godown
        this.prewFlagId = prewFlagId; //если это первый, то пусть у него будет предыдущим его же id
    }

    //базовый конструктор (заполняет поля недопустимыми значениями), наверное пусть будет так, чем никак
    public Flag() {
        this.flagId = -1;
        this.x = 0.0;
        this.y = 0.0;
        this.price = 0;
        this.prewFlagId = -1;
    }

    //возвращаем номер флага
    public Integer getId() {
        return this.flagId;
    }

    //устанавливаем номер флага (не представляю где это может пригодиться)
    public void setId(Integer newFlagId) {
        this.flagId = newFlagId;
    }

    public String getFlagName(){
        return this.flagName;
    }
    public void setFlagName(String flagName){
        this.flagName = flagName;
    }

    public Double getX() {
        return this.x;
    }

    public void setX(Double newX) {
        this.x = newX;
    }

    public Double getY() {
        return this.y;
    }

    public void setY(Double newY) {
        this.y = newY;
    }

    public Integer getPrice() {
        return this.price;
    }

    //уставливаем стоимость посещения этого места
    public void setPrice(Integer newPrice) {
        this.price = newPrice;
    }

    public void setName(String name) {
        this.flagName = name;
    }

    public String getName() {
        return this.flagName;
    }

    public Integer getPrew() {
        return this.prewFlagId;
    }

    public void setPrew(Integer newPrew) {
        this.prewFlagId = newPrew;
    }

}