package classes;
import java.util.*;


public class ProcessamentoSequencial extends ReservaService {

    private int gastosTotais = 0;
    private int gastosHoteis = 0;
    private int gastosVoos = 0;
    private int quantidadeDePedidos = 0;
    private int clientesDiferentes = 0;
    private int pedidosAtendidos = 0;

    public void contaClientes(List<Request> r){
        Set<String> clientes = new HashSet<>();
        for(Request c : r){
            clientes.add(c.getName());
        }
        this.clientesDiferentes = clientes.size();
    }
    
    public void contaPedidos(List<Request> r){
        this.quantidadeDePedidos = r.size();
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
        return this.gastosTotais;
    }

    public int getGastosHoteis(){
        return this.gastosHoteis;
    }
    
    public int getGastosVoos(){
        return this.gastosVoos;
    }

    public int getQuantidadeDePedidos(){
        return this.quantidadeDePedidos;
    }

    public int getClientesDiferentes(){
        return this.clientesDiferentes;
    }

    public int getPedidosAtendidos(){
        return this.pedidosAtendidos;
    }

    public void atualizaSaldo(Request c, Hotel h){
        c.setMaxPrice(c.getMaxPrice() - h.getNightCost() * c.getNightAmount());
    }

    public void atualizaVagasHotel(Hotel h){
        h.setVacancyAmount(h.getVacancyAmount() - 1);
    }

    public void atualizaAssentosVoos(List<Flight> voos){
        for(Flight voo : voos){
            voo.setEmptySeats(voo.getEmptySeats() - 1);
        }
    }


    public void processaPedidos(List<Request> requests, List<Hotel> hoteis, List<Flight> voos){
        for(Request c : requests){
            Hotel h = encontraMelhorHotel(hoteis, c.getDestination(), c.getStars(), c.getMaxPrice());
            if(h != null){
                atualizaSaldo(c, h);
            }
            List<Flight> v = encontrarMelhorVoo(voos, c.getDepartureLocation(), c.getDestination(), c.getMaxPrice());
            if(h != null && v.size() > 0){
                gastoHoteis(h, c.getNightAmount());
                atualizaVagasHotel(h);
                gastoVoos(v);
                atualizaAssentosVoos(v);
                contaPedidosAtendidos();
            }
        }
        contaClientes(requests);
        contaPedidos(requests);
        gastoTotal();
    }

    //Ainda precisa ser feita uma função para liberar o hotel quando a reserva do cliente acabar
    //Tbm precisa ser feita uma função para contar o tempo para saber quando liberar a vaga no hotel


}
