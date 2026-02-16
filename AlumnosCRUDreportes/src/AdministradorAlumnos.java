import java.util.Scanner;


public class AdministradorAlumnos {
    private static final int MAX_ALUMNOS = 25;
    private Alumno[] alumnos;
    private int contadorAlumnos; // aqui es para saber cuantos aumnos llevas
    private Scanner scanner;


    // aqui es el constructor
    public AdministradorAlumnos() {
        alumnos = new Alumno[MAX_ALUMNOS];
        contadorAlumnos = 0;
        scanner = new Scanner(System.in);
    }




    public void iniciar() {
        int opcion;
        do {
            System.out.println("alumno");
            System.out.println("1) Alta alumno");
            System.out.println("2) Buscar por ID (solo activos)");
            System.out.println("3) Actualizar promedio por ID (solo activos)");
            System.out.println("4) Baja lógica por ID");
            System.out.println("5) Listar activos");
            System.out.println("6) Reportes");
            System.out.println("0) Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // aqui es para limpiar


            switch (opcion) {
                case 1:
                    altaAlumno();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    actualizarPromedio();
                    break;
                case 4:
                    bajaLogica();
                    break;
                case 5:
                    listarActivos();
                    break;
                case 6:
                    generarReportes();
                    break;
                case 0:
                    System.out.println("salir");
                    break;
                default:
                    System.out.println("error");
            }
        } while (opcion != 0);
        scanner.close();
    }


    // aqui es para las altas de los alumnos
    private void altaAlumno() {
        if (contadorAlumnos >= MAX_ALUMNOS) {
            System.out.println("No hay espacio para mas alumnos");
            return;
        }


        // aqui es para que puedas validar el id
        int id;
        do {
            System.out.print("coloca el ID (mayor a 0): ");
            id = scanner.nextInt();
            if (id <= 0) System.out.println("el ID debe ser mayor a 0");
        } while (id <= 0);


        // aqui es por si se repite el id
        if (buscarIndicePorId(id) != -1) {
            System.out.println("ID ya existe");
            return;
        }
        scanner.nextLine();


        // aqui es para validar el nombre
        String nombre;
        do {
            System.out.print("pon el nombre (no vacio): ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) System.out.println("Nombre no puede estar vacio");
        } while (nombre.isEmpty());


        //aqui es para validar el promedio
        double promedio;
        do {
            System.out.print("coloca el promedio (0-10): ");
            promedio = scanner.nextDouble();
            if (promedio < 0 || promedio > 10) System.out.println("Promedio debe estar entre 0 y 10");
        } while (promedio < 0 || promedio > 10);


        // aqui es para agregar alumnos
        alumnos[contadorAlumnos] = new Alumno(id, nombre, promedio);
        contadorAlumnos++;
        System.out.println("Alumno dado de alta");
    }


    // aqui es para buscar el id pero no se permiten numeros negativos
    private int buscarIndicePorId(int id) {
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }


    // aqui es para buscar alumnos pero con su id
    private void buscarPorId() {
        System.out.print("coloca el ID a buscar: ");
        int id = scanner.nextInt();
        int indice = buscarIndicePorId(id);


        if (indice == -1 || !alumnos[indice].isActivo()) {
            System.out.println("Alumno no encontrado o no está activo");
        } else {
            Alumno a = alumnos[indice];
            System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre() + ", Promedio: " + a.getPromedio());
        }
    }


    // aqui es para actualizar promedio
    private void actualizarPromedio() {
        System.out.print("coloca el ID del alumno: ");
        int id = scanner.nextInt();
        int indice = buscarIndicePorId(id);


        if (indice == -1 || !alumnos[indice].isActivo()) {
            System.out.println("Alumno no encontrado");
            return;
        }


        // aqui es para que ya este el nuevo promedio
        double nuevoPromedio;
        do {
            System.out.print("coloca el nuevo promedio (0-10): ");
            nuevoPromedio = scanner.nextDouble();
            if (nuevoPromedio < 0 || nuevoPromedio > 10) System.out.println("Promedio debe estar entre 0 y 10");
        } while (nuevoPromedio < 0 || nuevoPromedio > 10);


        alumnos[indice].setPromedio(nuevoPromedio);
        System.out.println("Promedio actualizado");
    }


    // aqui es para dar de baja
    private void bajaLogica() {
        System.out.print("coloca el ID del alumno: ");
        int id = scanner.nextInt();
        int indice = buscarIndicePorId(id);


        if (indice == -1) {
            System.out.println("Alumno no encontrado");
        } else if (!alumnos[indice].isActivo()) {
            System.out.println("Alumno ya está inactivo");
        } else {
            alumnos[indice].setActivo(false);
            System.out.println("Alumno dado de baja");
        }
    }


    // aqui vas a listar los alumnos activos
    private void listarActivos() {
        System.out.println("alumnos activos");
        boolean hayActivos = false;
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].isActivo()) {
                Alumno a = alumnos[i];
                System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre() + ", Promedio: " + a.getPromedio());
                hayActivos = true;
            }
        }
        if (!hayActivos) System.out.println("No hay alumnos activos");
    }


    // aqui vas a generar los reportes
    private void generarReportes() {
        int totalActivos = 0;
        double sumaPromedios = 0.0;
        Alumno mayorPromedio = null;
        Alumno menorPromedio = null;
        int contadorMayoresOcho = 0;


        // aqui vas a poner a los alumnos para el reporte
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].isActivo()) {
                Alumno a = alumnos[i];
                totalActivos++;
                sumaPromedios += a.getPromedio();


                //aqui va el promedio mayor
                if (mayorPromedio == null || a.getPromedio() > mayorPromedio.getPromedio()) {
                    mayorPromedio = a;
                }


                // y aqui va el promedio menor
                if (menorPromedio == null || a.getPromedio() < menorPromedio.getPromedio()) {
                    menorPromedio = a;
                }


                // aqui es para contar el promedio
                if (a.getPromedio() >= 8.0) {
                    contadorMayoresOcho++;
                }
            }
        }


        // aqui pues ya es el reporte
        System.out.println("reportes");
        if (totalActivos == 0) {
            System.out.println("No hay alumnos activos para generar reportes");
            return;
        }


        System.out.println("Promedio general de activos: " + String.format("%.2f", sumaPromedios / totalActivos));
        System.out.println("Alumno con mayor promedio: ID " + mayorPromedio.getId() + ", Nombre " + mayorPromedio.getNombre() + ", Promedio " + mayorPromedio.getPromedio());
        System.out.println("Alumno con menor promedio: ID " + menorPromedio.getId() + ", Nombre " + menorPromedio.getNombre() + ", Promedio " + menorPromedio.getPromedio());
        System.out.println("Alumnos activos con promedio >=8.0: " + contadorMayoresOcho);
    }


    // aqui pues pones el metodo main para que se ejecute el programa
    public static void main(String[] args) {
        AdministradorAlumnos admin = new AdministradorAlumnos();
        admin.iniciar();
    }
}
