package service.core;

public class HouseObj {
    public HouseObj(int price, String houseName, HouseInfo houseInfo, HistoryObj history, LocationObj location, RentObj rentInfo){
        this.price = price;
        this.houseName = houseName;
        this.houseInfo = houseInfo;
        this.history = history;
        this.location = location;
        this.rentInfo = rentInfo;
    }

    public int price;
    public String houseName;
    public HouseInfo houseInfo;
    public HistoryObj history;
    public LocationObj location;
    public RentObj rentInfo;
}
