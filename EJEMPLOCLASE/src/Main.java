import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ticket ticket = new ticket();//Declaracion un objeto
        InputValidator inputValidator= new InputValidator();
        Scanner sc = new Scanner(System.in);
//I-P-O

//Input
        int cantidad = inputValidator.getValidInt("Ingresa la cantidad de articulos",sc);
//Process
        ticket.process(cantidad);

//Output
        ticket.imprimirTicket(cantidad);

    }
}