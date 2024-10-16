import java.io.*;
import java.util.Scanner;

public class EjercicioInmobiliariaAleatorios {
    private static final String FILE_NAME = "Inmuebles.dat";
    private static final int RECORD_SIZE = 193; // Tamaño de registro corregido

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Usamos try-with-resources para asegurar el cierre automático del archivo
        try (RandomAccessFile file = new RandomAccessFile(GestionFicheros.seleccionFichero(), "rw")) {
            int option;
            do {
                mostrarMenu();
                System.out.print("Seleccione una opción: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número entre 1 y 5.");
                    scanner.next(); // Descartar la entrada no válida
                    System.out.print("Seleccione una opción: ");
                }
                option = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (option) {
                    case 1:
                        altaInmueble(file, scanner);
                        break;
                    case 2:
                        listadoCompleto(file);
                        break;
                    case 3:
                        consultaDisponibles(file);
                        break;
                    case 4:
                        modificarInmueble(file, scanner);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione entre 1 y 5.");
                        break;
                }
            } while (option != 5);
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se pudo encontrar o crear el archivo " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error al acceder al fichero: " + e.getMessage());
        } finally {
            scanner.close(); // Cerramos el scanner en el bloque finally
        }
    }

    /**
     * Muestra el menú de opciones al usuario.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== Gestión de Inmuebles ===");
        System.out.println("1. Alta de inmueble");
        System.out.println("2. Listado del fichero completo");
        System.out.println("3. Consultas de inmuebles disponibles");
        System.out.println("4. Modificaciones de registros");
        System.out.println("5. Salir");
    }

    /**
     * Agrega un nuevo inmueble al archivo.
     *
     * @param file    El archivo de acceso aleatorio.
     * @param scanner El objeto Scanner para leer entradas del usuario.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void altaInmueble(RandomAccessFile file, Scanner scanner) throws IOException {
        System.out.print("Código del inmueble (entero): ");
        int codigo = leerEntero(scanner);

        // Verificar si el código ya existe
        if (existeCodigo(file, codigo)) {
            System.out.println("El código ya existe. Por favor, elija un código único.");
            return;
        }

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Código Postal (entero): ");
        int codPostal = leerEntero(scanner);
        System.out.print("Nombre del propietario: ");
        String nbPropietario = scanner.nextLine();
        System.out.print("Precio de alquiler (0-10): ");
        int precioAlquiler = leerEntero(scanner);

        // Validar el precio de alquiler
        if (precioAlquiler < 0 || precioAlquiler > 10) {
            System.out.println("Precio de alquiler no válido. Debe estar entre 0 y 10.");
            return;
        }

        boolean disponible = true; // Por defecto, el inmueble está disponible

        // Mover el puntero al final del archivo para agregar el nuevo registro
        file.seek(file.length());

        // Escribir los datos en el archivo
        file.writeInt(codigo);
        writeFixedLengthChars(file, direccion, 30);
        writeFixedLengthChars(file, ciudad, 30);
        file.writeInt(codPostal);
        writeFixedLengthChars(file, nbPropietario, 30);
        file.writeInt(precioAlquiler);
        file.writeBoolean(disponible);

        System.out.println("Inmueble agregado correctamente.");
    }

    /**
     * Lista todos los inmuebles almacenados en el archivo.
     *
     * @param file El archivo de acceso aleatorio.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void listadoCompleto(RandomAccessFile file) throws IOException {
        file.seek(0); // Mover el puntero al inicio del archivo
        System.out.println("\n=== Listado Completo de Inmuebles ===");
        boolean hayRegistros = false;

        while (file.getFilePointer() < file.length()) {
            try {
                int codigo = file.readInt();
                String direccion = readFixedLengthChars(file, 30).trim();
                String ciudad = readFixedLengthChars(file, 30).trim();
                int codPostal = file.readInt();
                String nbPropietario = readFixedLengthChars(file, 30).trim();
                int precioAlquiler = file.readInt();
                boolean disponible = file.readBoolean();

                System.out.println("Código: " + codigo +
                        ", Dirección: " + direccion +
                        ", Ciudad: " + ciudad +
                        ", Código Postal: " + codPostal +
                        ", Propietario: " + nbPropietario +
                        ", Precio: " + precioAlquiler +
                        ", Disponible: " + disponible);
                hayRegistros = true;
            } catch (EOFException e) {
                break; // Fin del archivo
            }
        }

        if (!hayRegistros) {
            System.out.println("No hay inmuebles registrados.");
        }
    }

    /**
     * Consulta y muestra los inmuebles que están disponibles.
     *
     * @param file El archivo de acceso aleatorio.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void consultaDisponibles(RandomAccessFile file) throws IOException {
        file.seek(0); // Mover el puntero al inicio del archivo
        System.out.println("\n=== Inmuebles Disponibles ===");
        boolean hayDisponibles = false;

        while (file.getFilePointer() < file.length()) {
            try {
                int codigo = file.readInt();
                String direccion = readFixedLengthChars(file, 30).trim();
                String ciudad = readFixedLengthChars(file, 30).trim();
                int codPostal = file.readInt();
                String nbPropietario = readFixedLengthChars(file, 30).trim();
                int precioAlquiler = file.readInt();
                boolean disponible = file.readBoolean();

                if (disponible) {
                    System.out.println("Código: " + codigo +
                            ", Dirección: " + direccion +
                            ", Ciudad: " + ciudad +
                            ", Código Postal: " + codPostal +
                            ", Propietario: " + nbPropietario +
                            ", Precio: " + precioAlquiler +
                            ", Disponible: " + disponible);
                    hayDisponibles = true;
                }
            } catch (EOFException e) {
                break; // Fin del archivo
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay inmuebles disponibles en este momento.");
        }
    }

    /**
     * Modifica un inmueble existente en el archivo.
     *
     * @param file    El archivo de acceso aleatorio.
     * @param scanner El objeto Scanner para leer entradas del usuario.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void modificarInmueble(RandomAccessFile file, Scanner scanner) throws IOException {
        if (file.length() == 0) {
            System.out.println("El fichero está vacío. No hay inmuebles para modificar.");
            return;
        }

        System.out.print("Código del inmueble a modificar: ");
        int codigo = leerEntero(scanner);

        long posicion = buscarPosicionPorCodigo(file, codigo);
        if (posicion == -1) {
            System.out.println("El código no existe. No se puede modificar un inmueble inexistente.");
            return;
        }

        // Mover el puntero a la posición del registro a modificar
        file.seek(posicion);

        System.out.print("Nueva dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Nueva ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Nuevo código postal (entero): ");
        int codPostal = leerEntero(scanner);
        System.out.print("Nuevo nombre del propietario: ");
        String nbPropietario = scanner.nextLine();
        System.out.print("Nuevo precio de alquiler (0-10): ");
        int precioAlquiler = leerEntero(scanner);
        System.out.print("¿Disponible? (true/false): ");
        boolean disponible = leerBoolean(scanner);

        // Validar el precio de alquiler
        if (precioAlquiler < 0 || precioAlquiler > 10) {
            System.out.println("Precio de alquiler no válido. Debe estar entre 0 y 10.");
            return;
        }

        // Escribir los nuevos datos en el archivo
        file.writeInt(codigo);
        writeFixedLengthChars(file, direccion, 30);
        writeFixedLengthChars(file, ciudad, 30);
        file.writeInt(codPostal);
        writeFixedLengthChars(file, nbPropietario, 30);
        file.writeInt(precioAlquiler);
        file.writeBoolean(disponible);

        System.out.println("Inmueble modificado correctamente.");
    }

    /**
     * Verifica si un código de inmueble ya existe en el archivo.
     *
     * @param file   El archivo de acceso aleatorio.
     * @param codigo El código a verificar.
     * @return true si el código existe, false de lo contrario.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static boolean existeCodigo(RandomAccessFile file, int codigo) throws IOException {
        file.seek(0); // Mover el puntero al inicio del archivo

        while (file.getFilePointer() < file.length()) {
            try {
                int currentCodigo = file.readInt();
                if (currentCodigo == codigo) {
                    return true;
                }
                file.seek(file.getFilePointer() + RECORD_SIZE - 4); // Salta al siguiente registro
            } catch (EOFException e) {
                break; // Fin del archivo
            }
        }
        return false;
    }

    /**
     * Busca la posición de un registro basado en su código.
     *
     * @param file   El archivo de acceso aleatorio.
     * @param codigo El código del inmueble a buscar.
     * @return La posición en bytes del inicio del registro si se encuentra, -1 de lo contrario.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static long buscarPosicionPorCodigo(RandomAccessFile file, int codigo) throws IOException {
        file.seek(0); // Mover el puntero al inicio del archivo

        while (file.getFilePointer() < file.length()) {
            long currentPos = file.getFilePointer();
            try {
                int currentCodigo = file.readInt();
                if (currentCodigo == codigo) {
                    return currentPos;
                }
                file.seek(currentPos + RECORD_SIZE); // Salta al siguiente registro
            } catch (EOFException e) {
                break; // Fin del archivo
            }
        }
        return -1;
    }

    /**
     * Escribe una cadena de caracteres de longitud fija en el archivo.
     *
     * @param file   El archivo de acceso aleatorio.
     * @param str    La cadena a escribir.
     * @param length La longitud fija en caracteres.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void writeFixedLengthChars(RandomAccessFile file, String str, int length) throws IOException {
        String padded = padString(str, length);
        for (int i = 0; i < padded.length(); i++) {
            file.writeChar(padded.charAt(i));
        }
    }

    /**
     * Lee una cadena de caracteres de longitud fija desde el archivo.
     *
     * @param file   El archivo de acceso aleatorio.
     * @param length La longitud fija en caracteres.
     * @return La cadena leída.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static String readFixedLengthChars(RandomAccessFile file, int length) throws IOException {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(file.readChar());
        }
        return sb.toString();
    }

    /**
     * Rellena una cadena con espacios para alcanzar una longitud fija.
     *
     * @param str    La cadena original.
     * @param length La longitud deseada.
     * @return La cadena rellena.
     */
    private static String padString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }

    /**
     * Lee un entero desde el Scanner con validación.
     *
     * @param scanner El objeto Scanner.
     * @return El entero leído.
     */
    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            scanner.next(); // Descartar la entrada no válida
            System.out.print("Inténtelo de nuevo: ");
        }
        int value = scanner.nextInt();
		scanner.nextLine(); // Consumir el salto de línea
		return value;
	

    }

    /**
     * Lee un valor booleano desde el Scanner con validación.
     *
     * @param scanner El objeto Scanner.
     * @return El valor booleano leído.
     */
    private static boolean leerBoolean(Scanner scanner) {
        while (!scanner.hasNextBoolean()) {
            System.out.println("Entrada no válida. Por favor, ingrese 'true' o 'false'.");
            scanner.next(); // Descartar la entrada no válida
            System.out.print("Inténtelo de nuevo: ");
        }
        return scanner.nextBoolean();
    }
}
