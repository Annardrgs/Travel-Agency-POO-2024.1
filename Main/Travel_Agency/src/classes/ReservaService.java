package classes;
import java.util.*;

public class ReservaService {

    public static Hotel encontraMelhorHotel(List<Hotel> hoteisDisponiveis, String destino, int estrelasMinimas, int precoMax){ // recebe a lista de hoteis disponíveis no momento, a cidade
        // desejada, a quantidade mínima de estrelas e o preço máximo exigidos pelo cliente.
        int menorPreco = precoMax; //inicializamos o menor preço com o preço máximo que o cliente esta disposto a pagar
        Hotel melhorHotel = null; // inicializamos o melhor hotel como nulo 
        for(Hotel hotel:hoteisDisponiveis){ // para cada hotel disponível
            if(hotel.getHotelLocation().equals(destino) && hotel.getVacancyAmount() >= 1 && hotel.getHotelStars() >= estrelasMinimas && hotel.getNightCost() < menorPreco){ // se o hotel fica na cidade desejada, tem pelo menos o
                // mínimo de estrelas exigido e tem um preço menor que o menor preço até agora 
                menorPreco = hotel.getNightCost(); // o menor preço passa a ser o desse hotel
                melhorHotel = hotel; // esse hotel passa a ser o melhor hotel para esse cliente
            }
        }

        return melhorHotel; // por fim, retornamos o melhor hotel para o cliente no momento em que foi feita a requisição
    }

    public static List<Flight> encontrarMelhorVoo(List<Flight> voosDisponiveis, String origem, String destino, int precoMax) { // recebe a lista de voos disponíveis, a cidade de saída e a
        // cidade de destino do cliente
        Map<String, List<Flight>> mapaVoos = new HashMap<>(); // criamos um mapa com a origem do voo como a chave e uma lista de voos com essa origem como info
        for (Flight voo : voosDisponiveis) {
            mapaVoos.computeIfAbsent(voo.getDepartureLocation(), k -> new ArrayList<>()).add(voo); // preenchemos o mapa
        }

        List<Flight> resultado = new ArrayList<>(); // criamos uma lista para guardar quais voos o cliente deve fazer
        int melhorPreco = precoMax; //inicializamos o menor preço com o preço máximo que o cliente está disposto a pagar em voos

        for (Flight voo1 : mapaVoos.getOrDefault(origem, Collections.emptyList())) { // para cada voo com a origem do cliente no mapa de voos 
            // (se não existir, o valor padrão retornado é uma lista vazia, ou seja, não existe voo com essa origem)
            if (voo1.getDestination().equals(destino) && voo1.getEmptySeats() >= 1 && voo1.getFlightPrice() < melhorPreco) { // se o destino desse voo é o destino do cliente e o preço do voo é o menor até agora
                resultado = Arrays.asList(voo1); // esse passa a ser o melhor voo para o cliente
                melhorPreco = voo1.getFlightPrice(); // o menor preço passa a ser o desse voo
            } else { // senão,
                for (Flight voo2 : mapaVoos.getOrDefault(voo1.getDestination(), Collections.emptyList())) { // para cada voo com a origem sendo o destino do voo 1
                    if (voo2.getDestination().equals(destino) && voo1.getEmptySeats() >= 1 && voo2.getEmptySeats() >= 1 && (voo1.getFlightPrice() + voo2.getFlightPrice()) < melhorPreco) { // se o destino do voo 2 é o destino do cliente e os preços do voo 1 e do voo 2
                        // somados for menor que o melhor preço até o momento
                        resultado = Arrays.asList(voo1, voo2); // esses passam a ser os melhores voos para o cliente
                        melhorPreco = voo1.getFlightPrice() + voo2.getFlightPrice(); // o melhor preço passa a ser o desses dois voos somados
                    } else { // senão,
                        for (Flight voo3 : mapaVoos.getOrDefault(voo2.getDestination(), Collections.emptyList())) { // para cada voo com a origem sendo o destino do voo 2
                            if (voo3.getDestination().equals(destino) && voo1.getEmptySeats() >= 1 && voo2.getEmptySeats() >= 1 && voo3.getEmptySeats() >= 1 &&(voo1.getFlightPrice() + voo2.getFlightPrice() + voo3.getFlightPrice()) < melhorPreco) { // se o destino do voo 3 é o destino do cliente e os preços
                                // do voo 1, do voo 2 e do voo 3 somados for menor que o melhor preço até o momento
                                resultado = Arrays.asList(voo1, voo2, voo3); // esses passam a ser os melhores voos para o cliente
                                melhorPreco = voo1.getFlightPrice() + voo2.getFlightPrice() + voo3.getFlightPrice(); // o melhor preço passa a ser o desses três voos somados
                            }
                        }
                    }
                }
            }
        }

        return resultado; // retornamos qual são os melhores voos para o cliente no momento em que foi feita a requisição
    }    
}
