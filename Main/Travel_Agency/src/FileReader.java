import java.util.*;

import classes.Hotel;
import classes.ProcessamentoSequencial;
import classes.Request;
import classes.Flight;

import java.io.File;
import java.io.FileNotFoundException;

public class FileReader{
    public static void main(String[] args) {
        
        List<Request> requests = ReadRequests();
        
        System.out.println("=== CLIENTES ===");
        for(int i = 0; i < requests.size(); i++){
            System.out.println("--Pedido de " + requests.get(i).getName() + "--");
            System.out.println("Saldo disponível: R$ " + requests.get(i).getMaxPrice());
            System.out.print("(" + requests.get(i).getDepartureLocation() + " -> ");
            System.out.print(requests.get(i).getDestination() + ") | ");
            System.out.print(requests.get(i).getNightAmount() + " dias | ");
            System.out.print(requests.get(i).getStars() + " estrelas ");
            System.out.println();
            System.out.println();
        }
        
        
        List<Hotel> hotels = ReadHotels();
        System.out.println("=== HOTÉIS ===");
        for(int i = 0; i < hotels.size(); i++){
            System.out.print("(" + hotels.get(i).getHotelLocation() + ") ");
            System.out.print(hotels.get(i).getHotelNumber() + " | ");
            System.out.print(hotels.get(i).getVacancyAmount() + " vagas | ");
            System.out.print("R$ " +hotels.get(i).getNightCost() + " | ");
            System.out.print(hotels.get(i).getHotelStars() + " estrelas");
            System.out.println();
        }
        System.out.println();
        
        
        List<Flight> flights = ReadFlights();
        System.out.println("=== VOOS ===");
        for(int i = 0; i < flights.size(); i++){
            System.out.print("(" + flights.get(i).getDepartureLocation() + " -> ");
            System.out.print(flights.get(i).getDestination() + ") | ");
            System.out.print(flights.get(i).getDepartureDate() + " | ");
            System.out.print(flights.get(i).getDepartureTime() + " | ");
            System.out.print(flights.get(i).getEmptySeats() + " assentos | ");
            System.out.print("R$ " + flights.get(i).getFlightPrice());
            System.out.println();
        }
        System.out.println();

        ProcessamentoSequencial p = new ProcessamentoSequencial();
        p.processaPedidos(requests, hotels, flights);
        System.out.println("Pedidos: " + p.getQuantidadeDePedidos());
        System.out.println("Clientes diferentes: " + p.getClientesDiferentes());
        System.out.println("Pedidos atendidos: " + p.getPedidosAtendidos());
        System.out.println("Valor total gasto: " + p.getGastosTotais());
        System.out.println("Valor gasto em hoteis: " + p.getGastosHoteis());
        System.out.println("Valor gasto em voos: " + p.getGastosVoos());
    }
    
        
    public static List<Request> ReadRequests(){
        try{
            Scanner Reader = new Scanner(new File("data/formato-clientes.csv"));

            List<Request> requests = new ArrayList<>();
            //Delimitador para usar o .next do Scanner
            Reader.useDelimiter(";");

            String name;
            String departureLocation;
            String Destination;
            int nightAmount;
            int stars;
            int maxPrice;
            String auxiliar;
            String[] auxiliarVector;

            while(Reader.hasNextLine()){
                name = Reader.next();

                departureLocation = Reader.next();
                
                Destination = Reader.next();
                
                auxiliar = Reader.next();
                auxiliarVector = auxiliar.split(" ");
                nightAmount = Integer.parseInt(auxiliarVector[0]);
                
                auxiliar = Reader.next();
                stars = auxiliar.charAt(0) - '0';
                
                auxiliar = Reader.nextLine();
                auxiliarVector = auxiliar.split(" ");
                maxPrice = Integer.parseInt(auxiliarVector[1]);

                requests.add(new Request(name, maxPrice, nightAmount, departureLocation, Destination, stars));
            }
            Reader.close();
            return requests;
        } catch(FileNotFoundException e){
            System.out.println("Erro na leitura do arquivo");
            return null;
        }
    }

    public static List<Hotel> ReadHotels(){
        try {
            Scanner Reader = new Scanner(new File("data/formato-hoteis.csv"));
            //Delimitador para usar o .next do Scanner
            Reader.useDelimiter(";");
            List<Hotel> hoteis = new ArrayList<>();

            String hotelLocation;
            String hotelNumber;
            int vacancyAmount;
            int nightCost;
            int stars;
            String auxiliar;
            String[] auxiliarVector;

            while(Reader.hasNextLine()){
                hotelLocation = Reader.next();
                hotelNumber = Reader.next();

                auxiliar = Reader.next();
                auxiliarVector = auxiliar.split(" ");
                vacancyAmount = Integer.parseInt(auxiliarVector[0]);

                auxiliar = Reader.next();
                auxiliarVector = auxiliar.split(" ");
                nightCost = Integer.parseInt(auxiliarVector[1]);

                auxiliar = Reader.nextLine();
                stars = auxiliar.charAt(1) - '0';

                hoteis.add(new Hotel(hotelLocation, hotelNumber, vacancyAmount, nightCost, stars));
            }
            Reader.close();
            return hoteis;
        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura do arquivo");
            return null;
        }
    }
    public static List<Flight> ReadFlights(){
        try {
            Scanner Reader = new Scanner(new File("data/formato-voos.csv"));
            //Delimitador para usar o .next do Scanner
            Reader.useDelimiter(";");
            List<Flight> voos = new ArrayList<>();

            String departureLocation;
            String destination;
            String departureDate;
            String departureTime;
            int emptySeats;
            int flightPrice;
            String auxiliar;
            String[] auxiliarVector;
            
            while(Reader.hasNextLine()){
                departureLocation = Reader.next();
                destination = Reader.next();
                departureDate = Reader.next();
                departureTime = Reader.next();
                
                auxiliar = Reader.next();
                auxiliarVector = auxiliar.split(" ");
                emptySeats = Integer.parseInt(auxiliarVector[0]);

                auxiliar = Reader.nextLine();
                auxiliarVector = auxiliar.split(" ");
                flightPrice = Integer.parseInt(auxiliarVector[1]);

                voos.add(new Flight(departureLocation, destination, departureDate, departureTime, flightPrice, emptySeats));
            }

            Reader.close();
            return voos;
        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura do arquivo");
            return null;
        }
    }
}