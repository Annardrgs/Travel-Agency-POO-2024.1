package classes;
import java.util.*;

public class Costumer{
  private String Name;
  private int MaxPrice;
  private List<Request> requests;
  
  public Costumer(String Name, int MaxPrice){
    this.Name = Name;
    this.MaxPrice = MaxPrice;
    this.requests = new ArrayList<>();
  }

  public String getName(){
    return Name;
  }
  
  public void setName(String Name){
    this.Name = Name;
  }
  
  public int getMaxPrice(){
    return MaxPrice;
  }

  public void setMaxPrice(int MaxPrice){
    this.MaxPrice = MaxPrice;
  }

  public List<Request> getRequests() {
      return requests;
  }

  public void addRequest(Request r){
    this.requests.add(r);
  }
}
