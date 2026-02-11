import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Se crea el objeto Scanner para leer datos del usuario
        Scanner sc = new Scanner(System.in);
        // Datos de entrada
        System.out.print("Ingresa el peso del paquete: ");
        double pesoKg = sc.nextDouble();
        System.out.print("Ingresa la distancia en km: ");
        int distanciaKm = sc.nextInt();
        System.out.print("Tipo de servicio (1=Estandar, 2=Rapido): ");
        int tipoServicio = sc.nextInt();
        System.out.print("¿Es zona remota? (true/false): ");
        boolean zonaRemota = sc.nextBoolean();
        // Se crea el objeto que realiza los cálculos
        ShippingCalculator calc = new ShippingCalculator();
        // Llamada a los métodos de cálculo
        double subtotal = calc.calcularSubtotal(pesoKg, distanciaKm, tipoServicio, zonaRemota);
        double iva = calc.calcularIVA(subtotal);
        double total = calc.calcularTotal(subtotal, iva);
        // Determinar el texto del servicio para mostrarlo
        String servicio;
        if (tipoServicio == 1) {
            servicio = "Estandar";
        } else {
            servicio = "Express";
        }
        // Impresión del ticket
        System.out.println("\n--- TICKET ---");
        System.out.println("Servicio: " + servicio);
        System.out.println("Peso: " + pesoKg + " kg");
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Zona remota: " + zonaRemota);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("IVA: $" + iva);
        System.out.println("Total: $" + total);
    }
}