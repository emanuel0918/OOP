import java.util.*;
import java.io.*;
import java.rmi.*;
public class Cliente {
    private ChatBotI remoto;
    private BufferedReader term = new BufferedReader(new InputStreamReader(System.in));;
    private String ip, respuesta;
    private int puerto;

    public Cliente() {
        try {
            System.out.println("IP: ");
            ip = term.readLine();
            System.out.println("Puerto: ");
            puerto = Integer.parseInt(term.readLine());

            remoto = (ChatBotI)Naming.lookup("//" + ip + ":" + puerto + "/ChatBot");
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
        }
        try {
            while (true) {
                respuesta = remoto.responde(term.readLine());
                System.out.println("Servidor: " + respuesta);
            }
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
        }
    }

    public static void main(String[] s) {
        new Cliente();
    }
}