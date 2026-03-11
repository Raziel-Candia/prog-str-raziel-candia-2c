import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Persona[] personas = new Persona[20];
        int opcion;

        do {
            System.out.println(" Menú (mínimo):");
            System.out.println("1) Alta");
            System.out.println("2) Buscar por ID (solo activas)");
            System.out.println("3) Baja lógica por ID");
            System.out.println("4) Listar activas");
            System.out.println("5) Actualizar nombre por ID (solo activas)");
            System.out.println("0) Salir");

            // para validar el menu
            while (!scanner.hasNextInt()) {
                System.out.println("pon un número entre 0 y 5");
                scanner.next(); //lo de siempre, si pone una respuesta mal la borras y pone otra
                System.out.print("selecciona tu opcion a realizar");
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); //para limpiar las respuestas

            switch (opcion) {
                case 1:
                    // Alta de personas
                    System.out.print("coloca el id");
                    int idAlta;
                    while (!scanner.hasNextInt()) {
                        System.out.println("debe ser numero entero");
                        scanner.next();
                        System.out.print("coloca el id: ");
                    }
                    idAlta = scanner.nextInt();
                    scanner.nextLine();

                    if (idAlta <= 0) {
                        System.out.println("ID debe ser mayor a 0");
                        break;
                    }

                    // por si se repite el ID
                    boolean idRepetido = false;
                    for (Persona p : personas) {
                        if (p != null && p.getId() == idAlta) {
                            idRepetido = true;
                            break;
                        }
                    }
                    if (idRepetido) {
                        System.out.println("este ID ya existe");
                        break;
                    }

                    // Vaqui es para que no haya un nombre vacio
                    System.out.print("ingresa nombre: ");
                    String nombreAlta = scanner.nextLine().trim();
                    if (nombreAlta.isEmpty()) {
                        System.out.println("error, el nombre no puede estar vacio");
                        break;
                    }

                    // aqui se agrega
                    boolean espacioEncontrado = false;
                    for (int i = 0; i < personas.length; i++) {
                        if (personas[i] == null) {
                            personas[i] = new Persona(idAlta, nombreAlta);
                            System.out.println("la persona esta dada de alta:))");
                            espacioEncontrado = true;
                            break;
                        }
                    }
                    if (!espacioEncontrado) {
                        System.out.println("lo siento, no hay espacio:(");
                    }
                    break;

                case 2:
                    // aqui buscas a las personas activas por su id
                    System.out.print("pon tu ID para ver si esta: ");
                    int idBuscar;
                    while (!scanner.hasNextInt()) {
                        System.out.println("error, deben de ser numeros enteros");
                        scanner.next();
                        System.out.print("Ingresalo: ");
                    }
                    idBuscar = scanner.nextInt();
                    scanner.nextLine();

                    boolean encontrada = false;
                    for (Persona p : personas) {
                        if (p != null && p.getId() == idBuscar && p.isActiva()) {
                            System.out.println("Persona encontrada: ");
                            System.out.println("ID: " + p.getId());
                            System.out.println("Nombre: " + p.getNombre());
                            encontrada = true;
                            break;
                        }
                    }
                    if (!encontrada) {
                        System.out.println("no existe, o no fue encontrada");
                    }
                    break;

                case 3:
                    // aqui es para la baja
                    System.out.print("coloca el ID para baja logica: ");
                    int idBaja;
                    while (!scanner.hasNextInt()) {
                        System.out.println("ID no valido");
                        scanner.next();
                        System.out.print("coloca Id para baja logica ");
                    }
                    idBaja = scanner.nextInt();
                    scanner.nextLine();

                    boolean bajaExitosa = false;
                    for (Persona p : personas) {
                        if (p != null && p.getId() == idBaja) {
                            p.setActiva(false);
                            System.out.println("ya esta dado de baja");
                            bajaExitosa = true;
                            break;
                        }
                    }
                    if (!bajaExitosa) {
                        System.out.println("error");
                    }
                    break;

                case 4:
                    //  aqui colocas la lista de las personas activas
                    System.out.println("********personas activas*******");
                    boolean hayActivas = false;
                    for (Persona p : personas) {
                        if (p != null && p.isActiva()) {
                            System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombre());
                            hayActivas = true;
                        }
                    }
                    if (!hayActivas) {
                        System.out.println("error, no hay nadie");
                    }
                    break;

                case 5:
                    // aqui es para actualizar el nombre solo si estas activo
                    System.out.print("coloca el ID de la persona que va a actualizar: ");
                    int idActualizar;
                    while (!scanner.hasNextInt()) {
                        System.out.println("error, tiene que ser numero entero");
                        scanner.next();
                        System.out.print("coloca el ID de la persona que va a actualizar: ");
                    }
                    idActualizar = scanner.nextInt();
                    scanner.nextLine();

                    boolean actualizada = false;
                    for (Persona p : personas) {
                        if (p != null && p.getId() == idActualizar && p.isActiva()) {
                            System.out.print("coloca el nuevo nombre");
                            String nuevoNombre = scanner.nextLine().trim();
                            if (nuevoNombre.isEmpty()) {
                                System.out.println("Error");
                                actualizada = true; //este es para evitar los mensajes de entrada
                                break;
                            }
                            p.setNombre(nuevoNombre);
                            System.out.println("listo ya se actualizo el nombre:)");
                            actualizada = true;
                            break;
                        }
                    }
                    if (!actualizada) {
                        System.out.println("no existe la persona ");
                    }
                    break;

                case 0:
                    System.out.println("saliste del programa ");
                    break;

                default:
                    System.out.println("error");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }
}
