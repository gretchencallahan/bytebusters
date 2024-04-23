package service.appraiserOne;

import service.core.HouseObj;
import service.core.HouseInfo;

import java.util.List;
import java.util.Arrays;

public class serviceOneAppraisal{

    public List<String> ratingList = Arrays.asList("A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "E1", "E2", "F", "G");

    public HouseObj generateBasePrice(HouseObj house){
        
        int price = house.price;

        HouseInfo houseInfo = house.houseInfo;

        if (houseInfo != null){
            // increase price for number of bedrooms
            price *= (houseInfo.bedrooms * 1.25);
            // increase price for number of bathrooms
            price *= (houseInfo.bathrooms * 1.333);
            // increase price for number of stories
            price *= (houseInfo.stories * 1.125);

            if (houseInfo.attic == true){
                price += 1500;
            }
            if (houseInfo.basement == true){
                price += 2500;
            }

            for (int i = 0; i < ratingList.size(); i++){
                if (houseInfo.ecoRating == ratingList.get(i)){
                    // decrease the price of the house based on its eco rating
                    // the lower the eco rating, the higher index it has in the list, so
                    // you can use the index as an increment for how much to decrease the house price
                    price -= (i * 750);
                }
            }   
        }

        return new HouseObj(price, house.houseName, house.houseInfo, house.history, house.location, house.rentInfo);
    }
}
