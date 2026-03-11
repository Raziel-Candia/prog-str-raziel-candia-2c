package utez.edu.mx.ATM.CYCLES;

import java.util.Scanner;

public class Cylces {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cycleWhite();
    }
    /*bucle que se puede repetir de 0 a cantidad de veces*/
     private static void cycleWhite(){
       final int hidden =4;// Bandera que sirvre para mantener el bucle activo
         int answer = 0;


        while(answer != hidden){
            System.out.println("Adivina el numero");
            answer= sc.nextInt();


         if(answer == hidden){
             System.out.println("Excelente, adivinaste el numero");
         }else {
             System.out.println("Intenta de nuevo...");
         }
         }
     }
}
