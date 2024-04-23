package service.core;
public class HistoryObj{
    public HistoryObj(){}

    public HistoryObj(int price,int numberOfOwners,int ageOfTheProperty,boolean waterDamage, boolean electricalDamage){
      this.price=price;
      this.numberOfOwners=numberOfOwners;
      this.ageOfTheProperty=ageOfTheProperty;
      this.waterDamage=waterDamage;
      this.electricalDamage=electricalDamage;
    }
    public int price;
    public int numberOfOwners;
    public int ageOfTheProperty;
    public boolean waterDamage;
    public boolean electricalDamage;
}