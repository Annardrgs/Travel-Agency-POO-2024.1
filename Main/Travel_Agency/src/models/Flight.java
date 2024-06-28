
package models;

public class Flight{
  private String DepartureLocation;
  private String Destination;
  private String DepartureDate;
  private String DepartureTime;
  private int FlightPrice;
  private int EmptySeats;
  
  public Flight(String DepartureLocation, String Destination, String DepartureDate, String DepartureTime, int FlightPrice, int EmptySeats){
    this.DepartureLocation = DepartureLocation;
    this.Destination = Destination;
    this.DepartureDate = DepartureDate;
    this.DepartureTime = DepartureTime;
    this.FlightPrice = FlightPrice;
    this.EmptySeats = EmptySeats;
  }
  
  public String getDepartureLocation(){
    return DepartureLocation;
  }

  public void setDepartureLocation(String DepartureLocation){
    this.DepartureLocation = DepartureLocation;
  }
  
  public String getDestination(String Destination){
    return Destination;
  }
  
  public void setDestination(String Destination){
    this.Destination = Destination;
  }

  public String getDepartureDate(String DepartureDate){
    return DepartureDate;
  }
  
  public void setDepartureDate(String DepartureDate){
    this.DepartureDate = DepartureDate;
  }

  public String getDepartureTime(String DepartureTime){
    return DepartureTime;
  }
  
  public void setDepartureTime(String DepartureTime){
    this.DepartureTime = DepartureTime;
  }
  
  public int getFlightPrice(){
    return FlightPrice;
  }
  
  public void setFlightPrice(int FlightPrice){
    this.FlightPrice = FlightPrice;
  }
  
  public int getEmptySeats{
    return EmptySeats;
  }
  
  public void setEmptySeats(int EmptySeats){
    this.EmptySeats = EmptySeats;
  }
}