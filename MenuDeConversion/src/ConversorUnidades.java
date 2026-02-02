import java.util.Scanner;

public class ConversorUnidades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        // Contadores por tipo de conversión
        int contadorCtoF = 0;
        int contadorFtoC = 0;
        int contadorKmtoMillas = 0;
        int contadorMillastoKm = 0;

        do {
            // Mostrar menú
            System.out.println("\n=== CONVERSOR DE UNIDADES ===");
            System.out.println("1) °C a °F");
            System.out.println("2) °F a °C");
            System.out.println("3) Km a Millas");
            System.out.println("4) Millas a Km");
            System.out.println("5) Salir");
            System.out.print("Selecciona una opción: ");

            // Validar que la opción sea numérica
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Opción debe ser un número entre 1 y 5.");
                scanner.next(); // Limpiar entrada inválida
                System.out.print("Selecciona una opción válida: ");
            }
            opcion = scanner.nextInt();

            // Validar rango de la opción
            if (opcion < 1 || opcion > 5) {
                System.out.println("Error: Opción no válida. Debe ser entre 1 y 5.");
                continue;
            }

            // Procesar opción seleccionada
            if (opcion != 5) {
                double valor, resultado;
                System.out.print("Ingresa el valor a convertir: ");

                // Validar que el valor sea numérico
                while (!scanner.hasNextDouble()) {
                    System.out.println("Error: Valor debe ser numérico.");
                    scanner.next(); // Limpiar entrada inválida
                    System.out.print("Ingresa un valor válido: ");
                }
                valor = scanner.nextDouble();

                // Realizar conversión y actualizar contador
                switch (opcion) {
                    case 1:
                        resultado = (valor * 9/5) + 32;
                        System.out.printf("%.2f °C = %.2f °F%n", valor, resultado);
                        contadorCtoF++;
                        break;
                    case 2:
                        resultado = (valor - 32) * 5/9;
                        System.out.printf("%.2f °F = %.2f °C%n", valor, resultado);
                        contadorFtoC++;
                        break;
                    case 3:
                        resultado = valor * 0.621371;
                        System.out.printf("%.2f Km = %.2f Millas%n", valor, resultado);
                        contadorKmtoMillas++;
                        break;
                    case 4:
                        resultado = valor / 0.621371;
                        System.out.printf("%.2f Millas = %.2f Km%n", valor, resultado);
                        contadorMillastoKm++;
                        break;
                }
            }

        } while (opcion != 5);

        // Mostrar resumen al salir
        int totalConversiones = contadorCtoF + contadorFtoC + contadorKmtoMillas + contadorMillastoKm;
        System.out.println("\n=== RESUMEN DE CONVERSIONES ===");
        System.out.println("Total de conversiones realizadas: " + totalConversiones);
        System.out.println("°C a °F: " + contadorCtoF);
        System.out.println("°F a °C: " + contadorFtoC);
        System.out.println("Km a Millas: " + contadorKmtoMillas);
        System.out.println("Millas a Km: " + contadorMillastoKm);

        scanner.close();
    }
}

