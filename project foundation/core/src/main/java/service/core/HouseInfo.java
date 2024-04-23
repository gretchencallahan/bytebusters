package service.core;

public class HouseInfo{
    
    public HouseInfo(){}

    public HouseInfo(int stories, int bedrooms, int bathrooms, String ecoRating, boolean basement, boolean attic){
        this.stories = stories;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.ecoRating = ecoRating;
        this.basement = basement;
        this.attic = attic;
    }

    public int stories;
    public int bedrooms;
    public int bathrooms;
    public String ecoRating;
    public boolean basement;
    public boolean attic;
}