import java.util.*;

import classes.Costumer;
import classes.Hotel;
import classes.Request;
import classes.Flight;

import java.io.File;
import java.io.FileNotFoundException;

public class FileReader{
    public static void main(String[] args) {
        List<Costumer> clients = ReadCostumers();
        System.out.println("=== CLIENTES ===");
        for(int i = 0; i < clients.size(); i++){
            System.out.println("--Pedidos de " + clients.get(i).getName() + "--");
            System.out.println("Saldo disponível: R$ " + clients.get(i).getMaxPrice());
            for(int j = 0; j < clients.get(i).getRequests().size(); j++){
                System.out.print("(" + clients.get(i).getRequests().get(j).getDepartureLocation() + " -> ");
                System.out.print(clients.get(i).getRequests().get(j).getDestination() + ") | ");
                System.out.print(clients.get(i).getRequests().get(j).getNightAmount() + " dias | ");
                System.out.print(clients.get(i).getRequests().get(j).getStars() + " estrelas ");
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("=== HOTÉIS ===");
        List<Hotel> hotels = ReadHotels();
        for(int i = 0; i < hotels.size(); i++){
            System.out.print("(" + hotels.get(i).getHotelLocation() + ") ");
            System.out.print(hotels.get(i).getHotelNumber() + " | ");
            System.out.print(hotels.get(i).getVacancyAmount() + " vagas | ");
            System.out.print("R$ " +hotels.get(i).getNightCost() + " | ");
            System.out.print(hotels.get(i).getHotelStars() + " estrelas");
            System.out.println();
        }
        System.out.println();

        System.out.println("=== VOOS ===");
        List<Flight> flights = ReadFlights();
        for(int i = 0; i < flights.size(); i++){
            System.out.print("(" + flights.get(i).getDepartureLocation() + " -> ");
            System.out.print(flights.get(i).getDestination() + ") | ");
            System.out.print(flights.get(i).getDepartureDate() + " | ");
            System.out.print(flights.get(i).getDepartureTime() + " | ");
            System.out.print(flights.get(i).getEmptySeats() + " assentos | ");
            System.out.print("R$ " + flights.get(i).getFlightPrice());
            System.out.println();
        }
    }
    

    public static List<Costumer> ReadCostumers(){
        try{
            Scanner Reader = new Scanner(new File("data/formato-clientes.csv"));

            List<Costumer> clients = new ArrayList<>();
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
            Request request;

            while(Reader.hasNextLine()){
                name = Reader.next();

                departureLocation = Reader.next();
                
                Destination = Reader.next();
                
                auxiliar = Reader.next();
                auxiliarVector = auxiliar.split(" ");
                nightAmount = Integer.parseInt(auxiliarVector[0]);
                
                auxiliar = Reader.next();
                stars = auxiliar.charAt(0) - '0';

                request = new Request(nightAmount, departureLocation, Destination, stars);
                
                Boolean exists = false;
                for(Costumer c: clients){
                    if(c.getName().equals(name)){
                        c.addRequest(request);
                        exists = true;
                    }
                }

                auxiliar = Reader.nextLine();
                auxiliarVector = auxiliar.split(" ");
                maxPrice = Integer.parseInt(auxiliarVector[1]);
                if(!exists){                    
                    clients.add(new Costumer(name, maxPrice));
                    clients.getLast().addRequest(request);
                }
            }
            Reader.close();
            return clients;
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