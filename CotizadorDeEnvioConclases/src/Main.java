import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner scannerEntrada = new Scanner(System.in);

        double pesoPaquete = leerDoubleEnRango(scannerEntrada, "Ingresa el peso del paquete en kg (0.1 a 50.0): ", 0.1, 50.0);
        int distanciaEnvio = leerIntEnRango(scannerEntrada, "Ingresa la distancia del envío en km (1 a 2000): ", 1, 2000);
        int tipoServicio = leerIntEnRango(scannerEntrada, "¿Qué servicio quieres? (1=Estándar, 2=Express): ", 1, 2);
        boolean zonaRemota = leerBoolean(scannerEntrada, "¿El destino es zona remota? (escribe true o false): ");

        ShippingCalculator calculadoraEnvios = new ShippingCalculator();
        double subtotalSinIVA = calculadoraEnvios.calcularSubtotal(pesoPaquete, distanciaEnvio, tipoServicio, zonaRemota);
        double impuestoIVA = calculadoraEnvios.calcularIVA(subtotalSinIVA);
        double precioFinal = calculadoraEnvios.calcularTotal(subtotalSinIVA, impuestoIVA);

        mostrarTicket(tipoServicio, pesoPaquete, distanciaEnvio, zonaRemota, subtotalSinIVA, impuestoIVA, precioFinal);

        scannerEntrada.close();
    }


    // para los decimaales
    public static double leerDoubleEnRango(Scanner sc, String mensaje, double limiteMin, double limiteMax) {
        double valorIngresado;

        do {
            System.out.print(mensaje);

            // Primero me aseguro de que sea un número decimal
            while (!sc.hasNextDouble()) {
                System.out.println("¡Uy! Eso no es un número decimal válido.");
                sc.next(); // Descarto lo que el usuario escribió mal
                System.out.print(mensaje); // Vuelvo a pedir el dato
            }

            valorIngresado = sc.nextDouble();

            // Checo aver si esta dentro de las validaciones
            if (!Validaciones.esDoubleEnRango(valorIngresado, limiteMin, limiteMax)) {
                System.out.printf("Error: El valor tiene que estar entre %.1f y %.1f kg.%n", limiteMin, limiteMax);
            }

        } while (!Validaciones.esDoubleEnRango(valorIngresado, limiteMin, limiteMax));

        return valorIngresado;
    }


    public static int leerIntEnRango(Scanner sc, String mensaje, int min, int max) {
        int valor;

        do {
            System.out.print(mensaje);

            // numeros enteros
            while (!sc.hasNextInt()) {
                System.out.println("Error: Eso no es un número entero.");
                sc.next();
                System.out.print(mensaje);
            }

            valor = sc.nextInt();

            // chheco rango
            if (!Validaciones.esIntEnRango(valor, min, max)) {
                System.out.printf("No, ese valor no sirve – tiene que estar entre %d y %d.%n", min, max);
            }

        } while (!Validaciones.esIntEnRango(valor, min, max));

        return valor;
    }


    // aqui es para verdadero y falso aqui lo lee
    public static boolean leerBoolean(Scanner sc, String mensaje) {
        boolean resultado = false;
        boolean entradaEsValida = false;

        do {
            System.out.print(mensaje);
            String textoIngresado = sc.next();

            // Checo si lo que escribió es válido
            if (Validaciones.esBooleanValido(textoIngresado)) {
                // este nos dijeron que es para convertir mayusculaas y minuscukas
                resultado = Boolean.parseBoolean(textoIngresado.trim().toLowerCase());
                entradaEsValida = true;
            } else {
                System.out.println("¡Cuidado! Solo puedes escribir 'true' o 'false' (sin comillas).");
            }

        } while (!entradaEsValida);

        return resultado;
    }



    public static void mostrarTicket(int tipoServicio, double peso, int distancia, boolean esRemota,
                                     double subtotal, double iva, double total) {

        String nombreServicio;
        if (tipoServicio == 1) {
            nombreServicio = "Estándar";
        } else {
            nombreServicio = "Express";
        }

        String textoZona = esRemota ? "Sí" : "No";

        // aqui pues haces el diseño para que se vea bonito
        System.out.println("------- ticket ---------");
        System.out.printf(" Servicio elegido: %s%n", nombreServicio);
        System.out.printf(" Peso del paquete: %.2f kg%n", peso);
        System.out.printf(" Distancia a entregar: %d km%n", distancia);
        System.out.printf(" Zona remota: %s%n", textoZona);
        System.out.printf(" Subtotal (antes de IVA): $%.2f%n", subtotal);
        System.out.printf(" IVA (16%%): $%.2f%n", iva);
        System.out.printf(" TOTAL A PAGAR: $%.2f%n", total);
        System.out.println("-------------------------");
    }
}