import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Código hecho con comandos Linux
 */

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca la ruta del fichero:");
        String rutaFichero = sc.nextLine();

        File fichero = new File(rutaFichero);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe!!");
        }
        else {
            if (fichero.isDirectory()) {
                System.out.println("Introduzca la ruta de un archivo, no de un directorio!!");
            }
            else {
                ProcessBuilder[] builders = {
                        new ProcessBuilder().command("cat", rutaFichero),
                        new ProcessBuilder().command("wc", "-l", rutaFichero)
                };

                Process p1 = null;
                Process p2 = null;

                try{
                    p1 = builders[0].start();
                    p1.waitFor();

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(p1.getInputStream(), "UTF-8"));
                    String result1 = br1.lines().collect(Collectors.joining("\n"));

                    p2 = builders[1].start();
                    p2.waitFor();

                    BufferedReader br2 = new BufferedReader(new InputStreamReader(p2.getInputStream(), "UTF-8"));
                    String result2 = br2.lines().collect(Collectors.joining("\n"));

                    System.out.println(result1 + "\n\n");
                    System.out.println(result2);
                }
                catch (IOException | InterruptedException e) {
                    System.out.println("Error al lanzar el proceso");
                }
            }
        }
    }
}