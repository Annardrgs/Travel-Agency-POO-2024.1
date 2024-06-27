package models;

public class Orcamento {
    private String hotelEscolhido;
    private int estrelas;
    private String vooSelecionado;
    private int valorTotal;

    public Orcamento(String hotelEscolhido, int estrelas, String vooSelecionado, int valorTotal) {
        this.hotelEscolhido = hotelEscolhido;
        this.estrelas = estrelas;
        this.vooSelecionado = vooSelecionado;
        this.valorTotal = valorTotal;
    }

    public String getHotelEscolhido() {
        return hotelEscolhido;
    }

    public void setHotelEscolhido(String hotelEscolhido) {
        this.hotelEscolhido = hotelEscolhido;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getVooSelecionado() {
        return vooSelecionado;
    }

    public void setVooSelecionado(String vooSelecionado) {
        this.vooSelecionado = vooSelecionado;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }
}