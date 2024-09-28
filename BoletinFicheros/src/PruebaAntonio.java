import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PruebaAntonio {

    private static final int SLEEP_DURATION = 1000; // milliseconds
    private static final int ASCII_ART_ITERATIONS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] cumplidos = {
            "¡Eres el mejor, Antonio!",
            "¡Tus chistes son legendarios!",
            "¡Nos enseñas con una sonrisa cada día!",
            "¡Aún dormidos te esfuerzas porque aprendamos!",
        };

        String[] felicitaciones = {
            "¡Felicidades por ser un año más sabio!",
            "¡Recuerda que cada día estás más cerca de la jubilación!",
            "¡Antonio, te mereces lo mejor!",
            "¡Que tengas un día fantástico!"
        };

        System.out.println("¡Bienvenido al programa de Felicitaciones por tu cumpleaños!");

        // Bucle para generar cumplidos y felicitaciones
        boolean continuar = true;
        while (continuar) {
            System.out.println("Presiona ENTER para recibir un cumplido aleatorio...");
            if (scanner.hasNextLine()) {
                scanner.nextLine();

                // Generar cumplido al azar
                String cumplido = cumplidos[random.nextInt(cumplidos.length)];
                System.out.println("Cumplido: " + cumplido);

                System.out.println("¿Te gustaría otro cumplido? (sí/no)");
                if (scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("no")) {
                    continuar = false;
                }
            } else {
                System.out.println("No se ha introducido ningún valor. Saliendo...");
                break;
            }
        }

        continuar = true;  // Reset para las felicitaciones
        while (continuar) {
            System.out.println("Presiona ENTER para recibir una felicitación sorpresa...");
            if (scanner.hasNextLine()) {
                scanner.nextLine();

                // Generar felicitación al azar
                String felicitacion = felicitaciones[random.nextInt(felicitaciones.length)];
                System.out.println("Felicitación: " + felicitacion);

                System.out.println("¿Te gustaría otra felicitación? (sí/no)");
                if (scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("no")) {
                    continuar = false;
                }
            } else {
                System.out.println("No se ha introducido ningún valor. Saliendo...");
                break;
            }
        }

        // Sorpresa especial con arte ASCII
        System.out.println("\nAhora una sorpresa especial para el profesor Antonio...");
        for (int i = 0; i < ASCII_ART_ITERATIONS; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);  // Pausa de 1 segundo
                System.out.println("🎉🎉🎉");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Arte ASCII que representa "FELICIDADES"
        System.out.println("Aquí tienes tu chiste especial, Antonio:");
        System.out.println("¿Qué hace un programador cuando está triste?\r\n");
        System.out.println("Toma un \"break\" y compila sus emociones.");
        System.out.println("\n🎊🎊 ¡Gracias, Antonio, por todo tu esfuerzo y dedicación! 🎊🎊");
        System.out.println("¡Te apreciamos mucho! Que tengas un día maravilloso.");
        
        scanner.close();
    }
}
