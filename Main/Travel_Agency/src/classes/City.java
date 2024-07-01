package classes;

import java.util.ArrayList;
import java.util.List;

public class City {
    public List<Flight> flights = new ArrayList<>(); // Lista criada no metodo de inicialização da classe
    public List<Hotel> hotels = new ArrayList<>(); // Lista criada no metodo de inicialização da classe

    public void add_hotel(Hotel e){
        this.hotels.add(e);
    }

    public void add_flight(Flight e){
        this.flights.add(e);
    }
}
