import java.util.Scanner;

public class ActividadIfElseTarifa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("coloca la edad: ");
        int edad = scanner.nextInt();
        System.out.print("¿Es estudiante? (true/false): ");
        boolean esEstudiante = scanner.nextBoolean();

        if (edad < 0 || edad > 120) {
            System.out.println("Edad no valida");
            scanner.close();
            return;
        }

        int tarifa = 0;
        if (edad < 12) {
            tarifa = 50;
        } else if (edad >= 12 && edad <= 17) {
            if (esEstudiante) {
                tarifa = 60;
            } else {
                tarifa = 80;
            }
        } else if (edad >= 18) {
            if (esEstudiante) {
                tarifa = 90;
            } else {
                tarifa = 120;
            }
        }
        System.out.println("\nEdad ingresada: " + edad);
        System.out.println("Es estudiante: " + (esEstudiante ? "Sí" : "No"));
        System.out.println("Tarifa final: " + tarifa);

        scanner.close();
    }
}