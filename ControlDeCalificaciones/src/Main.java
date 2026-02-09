import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // aqui ponemos lo que vamos a leer y necesitar
        Scanner scanner = new Scanner(System.in);
        GradeService servicioCalificaciones = new GradeService();

        // aqui es para pedir todos los datos de los alumnos
        String nombreAlumno = leerTextoNoVacio(scanner, "Nombre del alumno: ");
        double parcial1 = leerDoubleEnRango(scanner, "Calificacion del parcial 1 (0-100): ", 0, 100);
        double parcial2 = leerDoubleEnRango(scanner, "Calificacion del parcial 2 (0-100): ", 0, 100);
        double parcial3 = leerDoubleEnRango(scanner, "Calificacion del parcial 3 (0-100): ", 0, 100);
        int porcentajeAsistencia = leerIntEnRango(scanner, "Asistencia (0-100): ", 0, 100);
        boolean proyectoEntregado = leerBoolean(scanner, "Entrego el proyecto final? (true o false): ");

        // aqui hacemos los calculos
        double promedioParciales = servicioCalificaciones.calcularPromedio(parcial1, parcial2, parcial3);
        double calificacionFinal = servicioCalificaciones.calcularFinal(promedioParciales, porcentajeAsistencia);
        String estadoFinal = servicioCalificaciones.determinarEstado(
                calificacionFinal,
                porcentajeAsistencia,
                proyectoEntregado
        );

        // aqui imprimimos los datos completos que pedimos arriba
        imprimirReporte(
                nombreAlumno,
                parcial1,
                parcial2,
                parcial3,
                promedioParciales,
                porcentajeAsistencia,
                proyectoEntregado,
                calificacionFinal,
                estadoFinal
        );

        // cerramos el scanner
        scanner.close();
    }

    // aqui se lee un texto y se asegura que no este vacio
    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine();
        } while (texto.trim().isEmpty());
        return texto;
    }

    // aqui se lee un double dentro de un rango valido
    private static double leerDoubleEnRango(Scanner scanner, String mensaje, double min, double max) {
        double valor;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                scanner.nextLine(); // limpiar buffer
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                scanner.nextLine(); // descartar entrada invalida
            }
            System.out.println("Valor invalido. Intenta otra vez.");
        }
    }

    // aqui se lee un entero dentro de un rango valido
    private static int leerIntEnRango(Scanner scanner, String mensaje, int min, int max) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                scanner.nextLine(); // descartar entrada invalida
            }
            System.out.println("Valor invalido. Intenta otra vez.");
        }
    }

    // aqui se lee un valor booleano (true o false)
    private static boolean leerBoolean(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextBoolean();
    }

    // aqui se imprime el reporte final con todos los datos del alumno
    private static void imprimirReporte(
            String nombre,
            double p1,
            double p2,
            double p3,
            double promedio,
            int asistencia,
            boolean proyecto,
            double calificacionFinal,
            String estado
    ) {
        System.out.println("\n**********REPORTE************");
        System.out.println("Nombre del alumno: " + nombre);
        System.out.println("Parcial 1: " + p1);
        System.out.println("Parcial 2: " + p2);
        System.out.println("Parcial 3: " + p3);
        System.out.println("Promedio de parciales: " + promedio);
        System.out.println("Asistencia: " + asistencia + "%");
        System.out.println("Proyecto entregado: " + proyecto);
        System.out.println("Calificacion final: " + calificacionFinal);
        System.out.println("Estado final: " + estado);
    }
}
