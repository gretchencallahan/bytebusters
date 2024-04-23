package service.appraiserTwo;

import service.core.HouseObj;
import service.core.HistoryObj;

public class serviceTwoAppraisal{

    public HouseObj checkHistory(HouseObj house){
        
        int price = house.price;

        // Adjust price based on historical factors
        HistoryObj history = house.history;
        if (history != null) {
            // Decrease price for each previous owner
            price -= history.numberOfOwners * 50;

            // Decrease price based on age of the property
            price -= history.ageOfTheProperty * 30;

            // Further adjust price if there is water or electrical damage
            if (history.waterDamage) {
                price -= 200;
            }
            if (history.electricalDamage) {
                price -= 150;
            }
        }

        return new HouseObj(price, house.houseName, house.houseInfo, house.history, house.location, house.rentInfo);
    }
}
