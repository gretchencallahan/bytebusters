package service.appraiserThree;

import service.core.HouseObj;
import service.core.HouseInfo;
import service.core.LocationObj;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class serviceThreeAppraisal{

    public HouseObj checkLocation(HouseObj house){
        
        int price = house.price;

        String uri = "mongodb+srv://gretchencallahan:QejlATmxnn2c92UM@bytebustershouselocatio.ql0dn6g.mongodb.net/";

        LocationObj locationObj = house.location;
        String homeName = house.houseName;
        //String locationString = locationObj.location;
        if (locationObj != null){

            try (MongoClient mongoClient = MongoClients.create(uri)) {
                MongoDatabase database = mongoClient.getDatabase("HouseLocations");
                MongoCollection<Document> collection = database.getCollection("houses");
                Document doc = collection.find(eq("name", homeName)).first();
                if (doc != null) {
                    // change price based on location (good or bad)
                    if (doc.county == 1){
                        price += 2000;
                    }
                    else if (doc.county == 2){
                        price -= 2500;
                    }
                    else if (doc.county == 3){
                        price *= 1.5;
                    }
                    else if (doc.county == 4){
                        price += 500;
                    }
                    else if (doc.county == 5){
                        price -= 3750;
                    }
                } else {
                    System.out.println("No matching houses found.");
                }


            }
            
        }
        return new HouseObj(price, house.houseName, house.houseInfo, house.history, house.location, house.rentInfo);
    }
}
