package models;

public class Budget {
    private String Hotel;
    private int NightAmount;
    private String Flight;
    private int TotalPrice;

    public Budget(String Hotel, int NightAmount, String Flight, int TotalPrice) {
        this.Hotel = Hotel;
        this.NightAmount = NightAmount;
        this.Flight = Flight;
        this.TotalPrice = TotalPrice;
    }

    public String getHotel() {
        return Hotel;
    }

    public void setHotel(String Hotel) {
        this.Hotel = Hotel;
    }

    public int getNightAmount() {
        return NightAmount;
    }

    public void setNightAmount(int NightAmount) {
        this.NightAmount = NightAmount;
    }

    public String getFlight() {
        return Flight;
    }

    public void setFlight(String Flight) {
        this.Flight = Flight;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
}