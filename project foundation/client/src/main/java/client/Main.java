package client;

import service.core.HouseInfo;
import service.core.HouseObj;
import service.appraiserOne.serviceOneAppraisal;
import service.appraiserTwo.serviceTwoAppraisal;
import service.appraiserThree.serviceThreeAppraisal;
import service.appraiserFour.serviceFourAppraisal;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {

    private static final String url = "http://localhost:5050/appraisals";

    serviceOneAppraisal serviceOne = new serviceOneAppraisal();
    serviceTwoAppraisal serviceTwo = new serviceTwoAppraisal();
    serviceThreeAppraisal serviceThree = new serviceThreeAppraisal();
    serviceFourAppraisal serviceFour = new serviceFourAppraisal();

    public HouseObj appraiseHouse(HouseObj house){
        
        // generate the base price using the first service
        HouseObj houseAfterFirstService = serviceOne.generateBasePrice(house);

        // augment the price based on the history of the property
        HouseObj houseAfterHistoryService = serviceTwo.checkHistory(houseAfterFirstService);

        // check the location of the house and change the price accordingly
        HouseObj houseAfterLocationService = serviceThree. checkLocation(houseAfterHistoryService);

        return houseAfterLocationService;
    }



    // create a function to generate rent information


    

    public static void main(String[] args){
        Main main = new Main(); // Create an instance of Main to invoke methods

        // Invoke the invokePost method for each client
        for (HouseObj house : houses) {
            try {
                main.invokePost(house);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Invoke the invoke method to perform GET request
        main.invoke();
    }

    public void invokePost(HouseObj house) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .build();

        String requestBody = prepareRequest(house);

        RequestBody body = RequestBody.create(
                requestBody,
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public String prepareRequest(HouseObj house) throws JsonProcessingException {
        var values = new HashMap<String, String>() {
            {
                put("price", String.valueOf(house.price));
                put("houseName", String.valueOf(house.houseName));
                put("houseInfo", String.valueOf(house.houseInfo));
                put("history", String.valueOf(house.history));
                put("location", String.valueOf(house.location));
                put("rentInfo", String.valueOf(house.rentInfo));
            }
        };

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);
        return requestBody;
    }

    public void invoke() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }

            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static final HouseObj[] houses = {
                
    };
}
