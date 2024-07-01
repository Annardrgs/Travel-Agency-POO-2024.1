import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import classes.City;
import classes.Hotel;
import classes.Request;
import classes.ReservaService;
import classes.Flight;

public class Sistema implements Runnable {
    public Map<String,City> cities = new HashMap<>(); // Map criado no metodo de inicialização da classe
    public List<Request> requests = new ArrayList<>(); // Lista criada no metodo de inicialização da classe
    public List<Flight> flights = new ArrayList<>(); // Lista criada no metodo de inicialização da classe
    public List<Request> budgetsApproved = new ArrayList<>(); // Lista preenchida por orcamentos aceitos(preenchidas pelas Threads)
    public List<Request> budgetsRefused = new ArrayList<>(); // Lista preenchida por orcamentos negados(preenchidas pelas Threads)
    public Set<String> clients = new HashSet<>(); // Set que guarda clientes distintos
    public int hotelExpenses = 0; // int que guarda valor gasto em hoteis
    public int flightExpenses = 0; // int que guarda valor gasto com voos
    public List<List<Request>> tValues = new ArrayList<>(); // lista de lista de request de cada thread
    

    public Sistema(String file_requests, String file_flight, String file_hotel) { // inicialização do sistema, funções de leitura e criação de listas/sets
        this.requests = FileReader.ReadRequests(file_requests);
        create_cities(file_flight, file_hotel);
        this.flights = FileReader.ReadFlights(file_flight);
    }

    public void create_cities(String file_flight, String file_hotel){ // função que cria as cidades e insere seus voos e hoteis
        List<Flight> tempF = FileReader.ReadFlights(file_flight);
        List<Hotel> tempH = FileReader.ReadHotels(file_hotel);
        for(int i = 0; i < tempF.size(); i++){
            if(cities.containsKey(tempF.get(i).getDepartureLocation())){ // caso ja tenha a cidade(departure) do voo no hash set add voo
                City c = cities.get(tempF.get(i).getDepartureLocation());
                c.add_flight(tempF.get(i));
            } else { // caso nao tenha a cidade(departure) do voo no hash set add cidade e add voo
                cities.put(tempF.get(i).getDepartureLocation(),new City());
                City c = cities.get(tempF.get(i).getDepartureLocation());
                c.add_flight(tempF.get(i));
            }


            if(cities.containsKey(tempF.get(i).getDestination())){ // caso ja tenha a cidade(destination) do voo no hash set add voo
                City c = cities.get(tempF.get(i).getDestination());
                c.add_flight(tempF.get(i));
            } else { // caso nao tenha a cidade(destination) do voo no hash set add cidade e add voo
                cities.put(tempF.get(i).getDestination(),new City());
                City c = cities.get(tempF.get(i).getDestination());
                c.add_flight(tempF.get(i));
            }


        }
        for(int j = 0; j < tempH.size(); j++){
            if(cities.containsKey(tempH.get(j).getHotelLocation())){ // caso ja tenha a cidade no hash set add hotel
                City c = cities.get(tempH.get(j).getHotelLocation());
                c.add_hotel(tempH.get(j));
            } else {
                cities.put(tempH.get(j).getHotelLocation(),new City());// caso nao tenha a cidade no hash set add cidade e hotel
                City c = cities.get(tempH.get(j).getHotelLocation());
                c.add_hotel(tempH.get(j));
            }
        }
    }

    
    public synchronized void plus_gasto_hoteis(int val){ // função sincronizada para aumentar o valor gasto em hoteis
        this.hotelExpenses += val;
    }

    public synchronized void plus_gasto_voos(List<Flight> voos){ // função sincronizada para aumentar o valor gasto em voos
        for(Flight voo : voos){
            this.flightExpenses += voo.getFlightPrice();
        }
    }
    public synchronized void adicionar_negados(Request e){ // função sincronizada para add requests negados
        this.budgetsRefused.add(e);
    }
    public synchronized void adicionar_aceitos(Request e){ // função sincronizada para add requests aceitos
        this.budgetsApproved.add(e);
    }
    public synchronized void alterar_vagas_voo(List<Flight> e, int val){ // função sincronizada para alterar vagas dos voos
        for(int i = 0; i<e.size(); i++){
            e.get(i).setEmptySeats(e.get(i).getEmptySeats() + val);
        }
        
    }
    public synchronized void alterar_vagas_hotel(Hotel e, int i){ // função sincronizada para alterar vagas dos hoteis
        e.setVacancyAmount(e.getVacancyAmount()+i);
    }
    public synchronized  void add_client(String s){ // função sincronizada para adicionar clientes ao set
        clients.add(s);
    }

    public void get_threads() { // função para criar os conjuntos de 10 ou menos requests (1 conjunto para cada thread)
        List<Request> temp1 = new ArrayList<>();
        for(int i = 0; i < requests.size(); i++){
            if(i%10 == 0 && i != 0) {
                List<Request> temp2 = new ArrayList<>();
                temp2.addAll(temp1);
                tValues.add(temp2);
                temp1.clear();
            }
            temp1.add(requests.get(i));
        }
        if(requests.size()%10 !=0){
            tValues.add(temp1);
        }
    }

    public synchronized List<Request> get_t_list(){ // função sincronizada para cada thread pegar seu respectivo conjunto de requests
        List<Request> e = this.tValues.get(0);
        this.tValues.remove(0);
        return e;
    }

    @Override
    public void run(){
        List<Request> req = new ArrayList<>();
        req = get_t_list(); // conjunto de requests selecionado
        for(int i = 0; i< req.size(); i++){ // iteração para cada request
            Boolean k = false;
            Request e = req.get(i);
            add_client(e.getName());
            if(cities.get(e.getDestination()) == null || cities.get(e.getDepartureLocation()) == null){ //verificação para saber se as cidades estão no sistema
                continue;
            }
            Hotel h = ReservaService.encontraMelhorHotel(cities.get(e.getDestination()).hotels, e.getDestination(), e.getStars(), e.getMaxPrice()); // retorna o hotel a ser reservado
            if(h != null){
                e.setMaxPrice(e.getMaxPrice() - h.getNightCost() * e.getNightAmount()); //altera o budget do cliente
            }
            List<Flight> v = ReservaService.encontrarMelhorVoo(flights, e.getDepartureLocation(), e.getDestination(), e.getMaxPrice()); // retorna o melhor conjunto de voos para o cliente
            if(h != null && !v.isEmpty()){
                alterar_vagas_hotel(h,-1);
                if(h.getVacancyAmount() < 0){ // caso as vagas do hotel tenham sido esgotadas
                    alterar_vagas_hotel(h, 1);
                    continue;
                }                
                alterar_vagas_voo(v, -1);
                for(int j = 0; j<v.size(); j++){ // caso algum dos voos tenha se esgotado
                    if(v.get(j).getEmptySeats() < 0){
                        alterar_vagas_hotel(h, 1);
                        alterar_vagas_voo(v, 1);
                        k = true;
                        break;
                    }
                }
                if(k) continue;
                //atualização de gastos e adição do request na lista de aceitos
                plus_gasto_voos(v);
                plus_gasto_hoteis(h.getNightCost()* e.getNightAmount());
                adicionar_aceitos(e);
            }    
        }
        for(Request x : req){// adiciona recusados na lista de recusados
            if(budgetsApproved.contains(x) == false){
                adicionar_negados(x);
            }
        }
    }
}