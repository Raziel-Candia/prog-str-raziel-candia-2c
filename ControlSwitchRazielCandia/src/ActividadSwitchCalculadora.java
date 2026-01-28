import java.util.Scanner;
public class ActividadSwitchCalculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //menu
        System.out.println ("Menú de operaciones: ");
        System.out.println ("1)suma");
        System.out.println ("2)Resta");
        System.out.println ("3)multiplicación");
        System.out.println ("4)división");

        //pedir datos
        System.out.println ("elige tu opcion del (1-4): ");
        int opcion=scanner.nextInt();
        System.out.println ("ingrese el valor de a: ");
        double a=scanner.nextDouble();
        System.out.println ("ingrese el valor de b: ");
        double b= scanner.nextDouble();
        double resultado;
        //el switch para la opción de la operación
        switch (opcion) {
            case 1:
                resultado = a+ b;
                System.out.println ("elegiste suma");
                System.out.println ("los valores elegidos son: a = " +a + " b = " +b);
                System.out.println ("tu resultado es: " + resultado);
                break;
            case 2:
                resultado = a - b;
                System.out.println ("elegiste resta");
                System.out.println ("los valores elegidos son: a = " +a + " b = " +b);
                System.out.println ("tu resultado es: " + resultado);
                break;
            case 3:
                resultado = a * b;
                System.out.println ("elegiste multiplicación");
                System.out.println ("los valores elegidos son: a = " +a + " b = " +b);
                System.out.println ("tu resultado es: " + resultado);
                break;

            case 4:
                if (b==0){
                    System.out.println ("elegiste división");
                    System.out.println ("los valores elegidos son: a = " +a + " b = " +b);
                    System.out.println ("no se puede entre 0");
                }else {
                    resultado = a / b;
                    System.out.println("elegiste división");
                    System.out.println("los valores elegidos son: a = " + a + " b = " + b);
                    System.out.println("tu resultado es: " + resultado);
                }
                break;

            default:
                System.out.println("error");
                break;

        }

        scanner.close();
    }
}