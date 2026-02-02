import java.util.Random;
import java.util.Scanner;

public class AdivinaNumero {
    public static void main(String[] args) {
        // Generar número secreto aleatorio del 1 al 100
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1;

        // Variables del programa
        int intentosMaximos = 7;
        int intentosRealizados = 0;
        boolean gano = false;
        int contadorRango = 0;
        int contadorNoNumerico = 0;
        Scanner scanner = new Scanner(System.in);

        // Ciclo de intentos
        while (intentosRealizados < intentosMaximos && !gano) {
            System.out.printf("Intento %d/%d: Adivina el número (1-100): ",
                    intentosRealizados + 1, intentosMaximos);

            // Validar entrada no numérica
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida: debe ser un número.");
                contadorNoNumerico++;
                scanner.next(); // Limpiar entrada inválida
                continue;
            }

            int numeroUsuario = scanner.nextInt();

            // Validar rango
            if (numeroUsuario < 1 || numeroUsuario > 100) {
                System.out.println("Número fuera del rango 1-100.");
                contadorRango++;
                continue;
            }

            // Incrementar intentos válidos
            intentosRealizados++;

            // Comparar con número secreto
            if (numeroUsuario == numeroSecreto) {
                gano = true;
                System.out.println("¡Ganaste!");
            } else if (numeroUsuario < numeroSecreto) {
                System.out.println("El número secreto es mayor.");
            } else {
                System.out.println("El número secreto es menor.");
            }
        }

        // Mensaje final si pierde
        if (!gano) {
            System.out.println("\nPerdiste. El número secreto era " + numeroSecreto + ".");
            System.out.println("Veces fuera de rango: " + contadorRango);
            System.out.println("Veces entrada no numérica: " + contadorNoNumerico);
        }

        scanner.close();
    }
}
