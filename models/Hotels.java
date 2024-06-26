package Hotels.models;

public class Hotel {
    private String nomeHotel;
    private int estrelas;
    private String local;
    private int valorNoite;

    public Hotel(String nomeHotel, int estrelas, String local, int valorNoite) {
        this.nomeHotel = nomeHotel;
        this.estrelas = estrelas;
        this.local = local;
        this.valorNoite = valorNoite;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getValorNoite() {
        return valorNoite;
    }

    public void setValorNoite(int valorNoite) {
        this.valorNoite = valorNoite;
    }
}
