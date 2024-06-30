import java.util.*;

import classes.Costumer;
import classes.Hotel;
import classes.Request;
import classes.ReservaService;
import classes.Flight;


public class ProcessamentoSequencial extends ReservaService {

    private List<Costumer> clientes;
    private List<Hotel> hoteis;
    private List<Flight> voos;
    private int gastosTotais = 0;
    private int gastosHoteis = 0;
    private int gastosVoos = 0;
    private int quantidadeDePedidos = 0;
    private int clientesDiferentes = 0;
    private int pedidosAtendidos = 0;

    public ProcessamentoSequencial(List<Costumer> clientes, List<Hotel> hoteis, List<Flight> voos){
        this.clientes = clientes;
        this.hoteis = hoteis;
        this.voos = voos;
    }

    public void contaClientes(List<Costumer> clients){
        for(Costumer c : clients){
            this.clientesDiferentes += 1;
        }
    }
    
    public void contaPedidos(List<Costumer> clients){
        for(Costumer c : clientes){
            this.quantidadeDePedidos += c.getRequests().size();
        }
    }

    public void contaPedidosAtendidos(){
        this.pedidosAtendidos += 1;
    }

    public void gastoHoteis(Hotel hotel, int diasReservados){
        this.gastosHoteis += hotel.getNightCost() * diasReservados;
    }

    public void gastoVoos(List<Flight> voos){
        for(Flight voo : voos){
            this.gastosVoos += voo.getFlightPrice();
        }
    }

    public void gastoTotal(){
        this.gastosTotais = this.gastosHoteis + this.gastosVoos;
    }

    public int getGastosTotais(){
        return gastosTotais;
    }

    public int getGastosHoteis(){
        return gastosHoteis;
    }
    
    public int getGastosVoos(){
        return gastosVoos;
    }

    public int getQuantidadeDePedidos(){
        return quantidadeDePedidos;
    }

    public int getClientesDiferentes(){
        return clientesDiferentes;
    }

    public int getPedidosAtendidos(){
        return this.pedidosAtendidos;
    }

    public void atualizaSaldoHotel(Costumer c, Request r, Hotel h){
        c.setMaxPrice(c.getMaxPrice() - h.getNightCost() * r.getNightAmount());
    }

    public void atualizaVagasHotel(Hotel h){
        h.setVacancyAmount(h.getVacancyAmount() - 1);
    }

    public void atualizaSaldoVoos(Costumer c, List<Flight> voos){
        for(Flight voo : voos){
            c.setMaxPrice(c.getMaxPrice() - voo.getFlightPrice());
        }
    }

    public void atualizaAssentosVoos(List<Flight> voos){
        for(Flight voo : voos){
            voo.setEmptySeats(voo.getEmptySeats() - 1);
        }
    }


    public void processaPedidos(List<Costumer> clients, List<Hotel> hoteis, List<Flight> voos){

        for(Costumer c : clients){
            for(Request r : c.getRequests()){
                Hotel h = encontraMelhorHotel(hoteis, r.getDestination(), r.getStars(), c.getMaxPrice(), r.getNightAmount());
                List<Flight> v = encontrarMelhorVoo(voos, r.getDepartureLocation(), r.getDestination(), c.getMaxPrice(), 1);
                if(!r.getAccepted() && h != null && v != null){
                    gastoHoteis(h, r.getNightAmount());
                    atualizaSaldoHotel(c, r, h);
                    atualizaVagasHotel(h);
                    gastoVoos(v);
                    atualizaSaldoVoos(c, voos);
                    atualizaAssentosVoos(voos);
                    r.setAccepted();
                    contaPedidosAtendidos();
                }
            }
        }
    }

    //Ainda precisa ser feita uma função para liberar o hotel quando a reserva do cliente acabar
    //Tbm precisa ser feita uma função para contar o tempo para saber quando liberar a vaga no hotel


}
