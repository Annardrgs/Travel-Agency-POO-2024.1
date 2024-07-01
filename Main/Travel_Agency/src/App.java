import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Flight;
import classes.Hotel;
import classes.ProcessamentoSequencial;
import classes.Request;

public class App {
    public static void main(String[] args) throws Exception {
       String arq_flight, arq_hotel, arq_requests; //declarando paths dos arquivos

       arq_requests = "Main\\Travel_Agency\\src\\data\\formato-clientes10000.csv";
       arq_flight = "Main\\Travel_Agency\\src\\data\\formato-voos.csv";
       arq_hotel = "Main\\Travel_Agency\\src\\data\\formato-hoteis.csv";
       /* Descomente caso queira adicionar os paths manualmente
       Scanner teclado = new Scanner(System.in);


       System.out.println("digite o caminho para o arquivo de voos:"); 
       arq_flight = teclado.nextLine(); // "data/formato-voos.csv"
       
       System.out.println("digite o caminho para o arquivo de hoteis:");
       arq_hotel = teclado.nextLine(); // "data/formato-hoteis.csv"

       System.out.println("digite o caminho para o arquivo de pedidos:"); 
       arq_requests = teclado.nextLine(); // "data/formato-clientes.csv"
       //teclado.close();

       */
         
       long startTimeThread = System.currentTimeMillis();
       Sistema sis = new Sistema(arq_requests, arq_flight, arq_hotel); // iniciando sistema
       sis.get_threads(); // função para gerar conjuntos de 10 ou menos requests
       int t_size = sis.tValues.size();
       for(int i = 0; i<t_size; i++){ // iniciando as threads
        Thread t = new Thread(sis);
        t.start();

       }
       





       // Saidas
       System.out.println("==============SAIDA==============");
       System.out.println("1. Numero total de pedidos: " + sis.requests.size());
       System.out.println("2. Numero total de clientes distintos: " + sis.clients.size());
       System.out.println("3. Numero de pedidos atendidos: " + sis.budgetsApproved.size());
       
       
       System.out.println("4. Valor total gasto pelos clientes: " + (sis.flightExpenses + sis.hotelExpenses));
       System.out.println("5. Valor total gasto em hoteis: " + sis.hotelExpenses);
       System.out.println("6. Valor total gasto em voos: " + sis.flightExpenses);
       long endTimeThread = System.currentTimeMillis();
        
       
       //=====================================================================Processamento Sequencial============================================================
       
       long startTimeSequencial= System.currentTimeMillis();
        List<Request> requests = FileReader.ReadRequests(arq_requests);
        
        /*System.out.println("=== CLIENTES ===");
        for(int i = 0; i < requests.size(); i++){
            System.out.println("--Pedido de " + requests.get(i).getName() + "--");
            System.out.println("Saldo disponível: R$ " + requests.get(i).getMaxPrice());
            System.out.print("(" + requests.get(i).getDepartureLocation() + " -> ");
            System.out.print(requests.get(i).getDestination() + ") | ");
            System.out.print(requests.get(i).getNightAmount() + " dias | ");
            System.out.print(requests.get(i).getStars() + " estrelas ");
            System.out.println();
            System.out.println();
        }*/
        
        
        List<Hotel> hotels = FileReader.ReadHotels(arq_hotel);
        /* System.out.println("=== HOTÉIS ===");
        for(int i = 0; i < hotels.size(); i++){
            System.out.print("(" + hotels.get(i).getHotelLocation() + ") ");
            System.out.print(hotels.get(i).getHotelNumber() + " | ");
            System.out.print(hotels.get(i).getVacancyAmount() + " vagas | ");
            System.out.print("R$ " +hotels.get(i).getNightCost() + " | ");
            System.out.print(hotels.get(i).getHotelStars() + " estrelas");
            System.out.println();
        }
        System.out.println();*/
        
        
        List<Flight> flights = FileReader.ReadFlights(arq_flight);
        /* System.out.println("=== VOOS ===");
        for(int i = 0; i < flights.size(); i++){
            System.out.print("(" + flights.get(i).getDepartureLocation() + " -> ");
            System.out.print(flights.get(i).getDestination() + ") | ");
            System.out.print(flights.get(i).getDepartureDate() + " | ");
            System.out.print(flights.get(i).getDepartureTime() + " | ");
            System.out.print(flights.get(i).getEmptySeats() + " assentos | ");
            System.out.print("R$ " + flights.get(i).getFlightPrice());
            System.out.println();
        }
        System.out.println();*/
 
        ProcessamentoSequencial p = new ProcessamentoSequencial();
        p.processaPedidos(requests, hotels, flights);
        System.out.println("Pedidos: " + p.getQuantidadeDePedidos());
        System.out.println("Clientes diferentes: " + p.getClientesDiferentes());
        System.out.println("Pedidos atendidos: " + p.getPedidosAtendidos());
        System.out.println("Valor total gasto: " + p.getGastosTotais());
        System.out.println("Valor gasto em hoteis: " + p.getGastosHoteis());
        System.out.println("Valor gasto em voos: " + p.getGastosVoos());
        long endTimeSequencial= System.currentTimeMillis();
        

        
       //medidas de desempenho

        System.out.println("============Testes de Desempenho===========");
        System.out.println("tempo sequencial: " +(endTimeSequencial - startTimeSequencial) +" ms" );
        System.out.println("tempo Threads: " +(endTimeThread - startTimeThread)+ " ms");


        // funções para escrita das saidas .csv
        String saida = String.format("%d;%d;%d;R$ %d;R$ %d;R$ %d", sis.requests.size(), sis.clients.size(),sis.budgetsApproved.size(),sis.flightExpenses+ sis.hotelExpenses,sis.hotelExpenses, sis.flightExpenses);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saida-paralela.csv"))) {
            writer.write(saida);
        } catch (IOException e) {
            e.printStackTrace();
        }

        saida = String.format("%d;%d;%d;R$ %d;R$ %d;R$ %d",
        p.getQuantidadeDePedidos(),
        p.getClientesDiferentes(),
        p.getPedidosAtendidos(),
        p.getGastosTotais(),
        p.getGastosHoteis(),
        p.getGastosVoos());
        
        // Escrever no arquivo saida-paralela.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saida-sequencial.csv"))) {
            writer.write(saida);
        } catch (IOException e) {
            e.printStackTrace();
        }





        
    }
}
