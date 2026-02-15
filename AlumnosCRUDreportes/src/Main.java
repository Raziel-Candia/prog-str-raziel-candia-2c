import java.util.Scanner;


class Alumno {
    int id;
    String nombre;
    double promedio;
    boolean activo;

    // aqui inicualizas los datos del alumno
    Alumno(int id, String nombre, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.promedio = promedio;
        this.activo = true; // aqui se da como activo
    }
}


public class AdministrarAlumnos {
    // aqui es para que solo pueda aceptar 25
    static Alumno[] alumnos = new Alumno[25];
    static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {
        int opcion;

        do {
            // Mostrar menú
            System.out.println("menu");
            System.out.println("1) Alta alumno");
            System.out.println("2) Buscar por ID (solo activos)");
            System.out.println("3) Actualizar promedio por ID (solo activos)");
            System.out.println("4) Baja lógica por ID");
            System.out.println("5) Listar activos");
            System.out.println("6) Reportes");
            System.out.println("0) Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // qui es para limpiar


            // aqui ponemos para que funcione el menu y den la opcion
            switch(opcion) {
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
                    mostrarReportes();
                    break;
                case 0:
                    System.out.println("salir");
                    break;
                default:
                    System.out.println("error");
            }

        } while(opcion != 0);

        teclado.close();
    }


    // aqui es para dar de alta a un alumno
    static void altaAlumno() {
        System.out.println("alta de un alumno");

        // aqui es para que valide el id
        int id;
        do {
            System.out.print("coloca el ID (mayor a 0): ");
            id = teclado.nextInt();
            if(id <= 0) {
                System.out.println("lo siento tu id debe ser mayor a 0");
            } else if(existeId(id)) {
                System.out.println("este ya existe");
                id = -1; // aqui hace que vuelva a preguntar
            }
        } while(id <= 0);
        teclado.nextLine();


        // aqui vas a pedir el nombre para validarlo
        String nombre;
        do {
            System.out.print("coloca el nombre(no vacio): ");
            nombre = teclado.nextLine().trim();
            if(nombre.isEmpty()) {
                System.out.println("error");
            }
        } while(nombre.isEmpty());


        // aqui ahora pides el promedio para validarlo
        double promedio;
        do {
            System.out.print("coloca el promedio (entre 0 y 10): ");
            promedio = teclado.nextDouble();
            if(promedio < 0 || promedio > 10) {
                System.out.println("error");
            }
        } while(promedio < 0 || promedio > 10);


        // aqui para buscar espacio
        int posicion = buscarEspacioVacio();
        if(posicion != -1) {
            alumnos[posicion] = new Alumno(id, nombre, promedio);
            System.out.println("Alumno dado de alta");
        } else {
            System.out.println("No hay espacio disponible");
        }
    }


    // aqui vamos a ver si un id ya existe o no
    static boolean existeId(int id) {
        for(int i=0; i<alumnos.length; i++) {
            if(alumnos[i] != null && alumnos[i].id == id) {
                return true;
            }
        }
        return false;
    }


    // igual buscas un espacio vacio aqui
    static int buscarEspacioVacio() {
        for(int i=0; i<alumnos.length; i++) {
            if(alumnos[i] == null) {
                return i;
            }
        }
        return -1;
    }


    // aqui es para bsucar alumno que este activo pero por su id
    static void buscarPorId() {
        System.out.println("buscar alumno por ID");
        System.out.print("coloca el ID a buscar: ");
        int id = teclado.nextInt();

        Alumno alumno = encontrarAlumnoActivo(id);
        if(alumno != null) {
            mostrarDatosAlumno(alumno);
        } else {
            System.out.println("Alumno no encontrado o no activo");
        }
    }


    // lo mismo para buscar alumno por id
    static Alumno encontrarAlumnoActivo(int id) {
        for(int i=0; i<alumnos.length; i++) {
            if(alumnos[i] != null && alumnos[i].id == id && alumnos[i].activo) {
                return alumnos[i];
            }
        }
        return null;
    }


    // aqui es por si pidenm los datos del alumno
    static void mostrarDatosAlumno(Alumno alumno) {
        System.out.println("Datos del alumno: ");
        System.out.println("ID: " + alumno.id);
        System.out.println("Nombre: " + alumno.nombre);
        System.out.println("Promedio: " + alumno.promedio);
        System.out.println("Estado: " + (alumno.activo ? "Activo" : "Inactivo"));
    }


    // aqui es para actualizar el promedio el alumno
    static void actualizarPromedio() {
        System.out.println("actualizar promedio ");
        System.out.print("Ingresa ID del alumno: ");
        int id = teclado.nextInt();

        Alumno alumno = encontrarAlumnoActivo(id);
        if(alumno != null) {
            double nuevoPromedio;
            do {
                System.out.print("Ingresa nuevo promedio (0-10): ");
                nuevoPromedio = teclado.nextDouble();
                if(nuevoPromedio < 0 || nuevoPromedio > 10) {
                    System.out.println("no valido");
                }
            } while(nuevoPromedio < 0 || nuevoPromedio > 10);

            alumno.promedio = nuevoPromedio;
            System.out.println("listo ya quedo");
        } else {
            System.out.println("error");
        }
    }


    // aqui es por si se da de baja
    static void bajaLogica() {
        System.out.println("baja logica ");
        System.out.print("coloca el ID del alumno: ");
        int id = teclado.nextInt();

        // aqui buscas si esta aunque no este activo
        Alumno alumno = null;
        for(int i=0; i<alumnos.length; i++) {
            if(alumnos[i] != null && alumnos[i].id == id) {
                alumno = alumnos[i];
                break;
            }
        }

        if(alumno != null) {
            if(alumno.activo) {
                alumno.activo = false;
                System.out.println("Alumno dado de baja");
            } else {
                System.out.println("El alumno ya esta inactivo");
            }
        } else {
            System.out.println("Alumno no encontrado");
        }
    }


    // aqqui listas los que estan inactivos
    static void listarActivos() {
        System.out.println("lista de alumnos inactivos");
        boolean hayActivos = false;

        for(int i=0; i<alumnos.length; i++) {
            if(alumnos[i] != null && alumnos[i].activo) {
                mostrarDatosAlumno(alumnos[i]);
                System.out.println("********************");
                hayActivos = true;
            }
        }

        if(!hayActivos) {
            System.out.println("No hay alumnos activos registrados");
        }
    }


    // aqui es para hacer reportes
    static void mostrarReportes() {
        System.out.println("reportes");
        int totalActivos = 0;
        double sumaPromedios = 0;
        double promedioMayor = -1;
        double promedioMenor = 11;
        Alumno alumnoMejor = null;
        Alumno alumnoPeor = null;

        // aqui es para que calcules tdos los datos
        for(int i=0; i<alumnos.length; i++) {
            if(alumnos[i] != null && alumnos[i].activo) {
                totalActivos++;
                sumaPromedios += alumnos[i].promedio;

                // promedio mayor
                if(alumnos[i].promedio > promedioMayor) {
                    promedioMayor = alumnos[i].promedio;
                    alumnoMejor = alumnos[i];
                }

                // y aqui el promedio menor
                if(alumnos[i].promedio < promedioMenor) {
                    promedioMenor = alumnos[i].promedio;
                    alumnoPeor = alumnos[i];
                }
            }
        }

        // aqui es para poner el reporte de todo
        System.out.println("Total de alumnos activos: " + totalActivos);
        if(totalActivos > 0) {
            double promedioGeneral = sumaPromedios / totalActivos;
            System.out.println("Promedio general de activos: " + String.format("%.2f", promedioGeneral));
            System.out.println("Promedio mayor: " + promedioMayor + " (Alumno: " + alumnoMejor.nombre + ")");
            System.out.println("Promedio menor: " + promedioMenor + " (Alumno: " + alumnoPeor.nombre + ")");
        } else {
            System.out.println("error");
        }
    }
}