package models;

public class Costumer{
  private String Name;
  private String DepartureLocation;
  private String Destination;
  private int NightAmount;
  private int Stars;
  
  public Costumer(String Name, int NightAmount, String DepartureLocation, String Destination, int Stars){
    this.Name = Name;
    this.DepartureLocation = DepartureLocation;
    this.Destination = Destination;
    this.Stars = Stars;
  }
  
  public String getName(){
    return Name;
  }
  
  public void setName(String Name){
    this.Name = Name;
  }
  
  public String getDepartureLocation(){
    return DepartureLocation;
  }
  
  public void setDepartureLocation(String DepartureLocation){
    this.DepartureLocation = DepartureLocation;
  }
  
  public String getDestination(){
    return Destination;
  }
  
  public void setDestination(String Destination){
    this.Destination = Destination;
  }
  
  public int getStars(){
    return Stars;
  }
  
  public void setStars(int Stars){
    this.Stars = Stars;
  }
}