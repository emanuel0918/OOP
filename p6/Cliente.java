
import java.net.*;
import java.io.*;

public class Cliente {

    private ServerSocket servidor;
    private Socket socket;
    public BufferedReader lector;
    public PrintWriter escribe;

    public Cliente() {
        crearVentana();
    }

    public void crearVentana() {
        Thread hilop = new Thread(new Runnable() {
            public void run() {
                try {
                    socket = new Socket("localhost", 5000);
                    leer();
                    escribir();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        ;
        });
		hilop.start();
    }

    public void leer() {
        Thread hilo_leer = new Thread(new Runnable() {
            public void run() {
                try {
                    lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        String mensaje = lector.readLine();
                        System.out.println("Servidor dice: " + mensaje);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        hilo_leer.start();
    }

    public void escribir() {
        Thread hilo_contesta = new Thread(new Runnable() {
            public void run() {
                try {
                    escribe = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader mensaje = new BufferedReader(new InputStreamReader(System.in));
                    String m;
                    while (true) {
                        m = mensaje.readLine();
                        escribe.println(m);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        hilo_contesta.start();
    }

    public static void main(String[] s) {
        new Cliente();
    }
}
