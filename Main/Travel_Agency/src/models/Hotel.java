package models;


public class Hotel {
    private String nomeHotel;
    private int vagas;
    private int estrelas;
    private int valorNoite;

    public Hotel(String nomeHotel, int estrelas, int vagas, int valorNoite) {
        this.nomeHotel = nomeHotel;
        this.estrelas = estrelas;
        this.vagas = vagas;
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

    public int getvagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public int getValorNoite() {
        return valorNoite;
    }

    public void setValorNoite(int valorNoite) {
        this.valorNoite = valorNoite;
    }
}
