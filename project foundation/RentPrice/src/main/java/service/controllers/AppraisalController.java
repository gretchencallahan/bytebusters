package service.controllers;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

import service.core.HouseObj;
import service.appraiserFour.serviceFourAppraisal;

@RestController
public class AppraisalController {

    @Value("${server.port}")
    private int port;

    private Map<String, HouseObj> appraisals = new TreeMap<>();
    private serviceFourAppraisal appraisalService = new serviceFourAppraisal();

    @GetMapping(value="/appraisals", produces="application/json")
    public ResponseEntity<ArrayList<String>> getAppraisals() {
        ArrayList<String> list = new ArrayList<>();
        for (HouseObj houseObj : appraisals.values()){
            list.add("http://localhost:5053/appraisals/" + houseObj.houseName);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping(value="/appraisals", consumes="application/json")
    public ResponseEntity<HouseObj> createBasePrice(@RequestBody HouseObj house) {
            HouseObj houseObj = appraisalService.generateRentInfo(house);
            appraisals.put(houseObj.houseName, houseObj);
            String url = "http://" + "localhost:5053/appraisals/" + houseObj.houseName;
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", url)
                .header("Content-Location", url)
                .body(houseObj);
    }
        
    @GetMapping(value="/appraisals/{id}", produces={"application/json"})
    public ResponseEntity<HouseObj> getHouse(@PathVariable String refID){
        HouseObj houseObj = appraisals.get(refID);
        if (houseObj == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(houseObj);
    }
}   
