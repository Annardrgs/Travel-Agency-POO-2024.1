import java.util.Scanner;

import classes.Request;

public class App {
    public static void main(String[] args) throws Exception {
       String arq_flight, arq_hotel, arq_requests; //declarando paths dos arquivos

       arq_requests = "Main\\Travel_Agency\\src\\data\\formato-clientes.csv";
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
       Sistema sis = new Sistema(arq_requests, arq_flight, arq_hotel); // iniciando sistema
       sis.get_threads(); // função para gerar conjuntos de 10 ou menos requests

       
       for(int i = 0; i<sis.tValues.size(); i++){ // iniciando as threads
        Thread t = new Thread(sis);
        t.start();
       }





       // Saidas
       System.out.println("==============SAIDA==============");
       System.out.println("1. Numero total de pedidos: " + sis.requests.size());
       System.out.println("2. Numero total de clientes distintos: " + sis.clients.size());
       System.out.println("3. Numero de pedidos atendidos: " + sis.budgetsApproved.size());
       System.out.println("===========Pedidos Aceitos===========");
       for(Request req :sis.budgetsApproved){
        System.out.println(req.getName()+";"+req.getDepartureLocation()+"-->"+req.getDestination()+";"+req.getNightAmount()+";"+req.getStars());
       }
       System.out.println("===========Pedidos Negados===========");
       for(Request req :sis.budgetsRefused){
        System.out.println(req.getName()+";"+req.getDepartureLocation()+"-->"+req.getDestination()+";"+req.getNightAmount()+";"+req.getStars());
       }
       
       System.out.println("4. Valor total gasto pelos clientes: " + (sis.flightExpenses + sis.hotelExpenses));
       System.out.println("5. Valor total gasto em hoteis: " + sis.hotelExpenses);
       System.out.println("6. Valor total gasto em voos: " + sis.flightExpenses);
    }
}
