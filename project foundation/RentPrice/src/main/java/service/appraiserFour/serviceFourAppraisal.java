package service.appraiserFour;

import service.core.HouseObj;
import service.core.HouseInfo;
import service.core.HistoryObj;
import service.core.LocationObj;
import service.core.RentObj;

public class serviceFourAppraisal{

    public static final String APPRAISER = "appraiserFour";

    public HouseObj generateRentInfo(HouseObj house){
        
        int price = house.price;

        // use rentObj and other information to generate a reasonable rent for the house

        return new HouseObj(price, house.houseName, house.houseInfo, house.history, house.location, house.rentInfo);
    }
}
