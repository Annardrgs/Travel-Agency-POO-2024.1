package classes;

public class Request{
    private String DepartureLocation;
    private String Destination;
    private int NightAmount;
    private int Stars;
    private Boolean Accepted;

    public Request(int NightAmount, String DepartureLocation, String Destination, int Stars){
        this.DepartureLocation = DepartureLocation;
        this.Destination = Destination;
        this.Stars = Stars;
        this.NightAmount = NightAmount;
        this.Accepted = false;
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

    
    public void setNightAmount(int nightAmount) {
        NightAmount = nightAmount;
    }

    public int getNightAmount() {
        return NightAmount;
    }

    public Boolean getAccepted() {
        return Accepted;
    }

    public void setAccepted(){
        this.Accepted = true;
    }
}
