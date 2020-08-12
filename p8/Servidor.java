import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
public class Servidor {
    private boolean existe = false;
    private BufferedReader term = new BufferedReader(new InputStreamReader(System.in));;
    private String ip;
    private int puerto;

    public Servidor() {
        try {
            ChatBotImp obj = new ChatBotImp();
            System.out.println("IP: ");
            ip = term.readLine();
            System.out.println("Puerto: ");
            puerto = Integer.parseInt(term.readLine());
            java.rmi.registry.LocateRegistry.createRegistry(puerto);
            Naming.rebind("//" + ip + ":" + puerto + "/ChatBot", obj);
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
            System.exit(0);
        }
    }

    public static void main(String[] s) {
        new Servidor();
    }
}
