package Hotels.models;

public class Hotels {
    private String HotelLocation;
    private String HotelNumber;
    private int VacancyAmount;
    private int NightCost;
    private int HotelStars;

    public Hotels(String HotelLocation, String HotelNumber, int VacancyAmount, int NightCost, int HotelStars) {
        this.HotelLocation = HotelLocation;
        this.HotelNumber = HotelNumber;
        this.VacancyAmount = VacancyAmount;
        this.NightCost = NightCost;
        this.HotelStars = HotelStars;
    }

    public String getHotelLocation() {
        return HotelLocation;
    }

    public void setHotelLocation(String HotelLocation) {
        this.HotelLocation = HotelLocation;
    }

    public String getHotelNumber() {
        return HotelNumber;
    }

    public void setHotelNumber(String HotelNumber) {
        this.HotelNumber = HotelNumber;
    }

    public int getVacancyAmount(){
        return VacancyAmount;
    }

    public void setVacancyAmount(int VacancyAmount) {
        this.VacancyAmount = VacancyAmount;
    }

    public int getNightCost() {
        return NightCost;
    }

    public void setNightCost(int NightCost) {
        this.NightCost = NightCost;
    }

    public int getHotelStars() {
        return HotelStars;
    }

    public void setHotelStars(int HotelStars) {
        this.HotelStars = HotelStars;
    }
}
