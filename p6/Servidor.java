
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Servidor {

    String mensaje;
    private ServerSocket servidor;
    private Socket socket;
    public BufferedReader lector;
    public PrintWriter escribe;
    private HashMap<String,String> preguntas;

    public Servidor() {
        preguntas=new HashMap();
        preguntas.put("Como te llamas?", "PC de Emanuel");
        preguntas.put("En que ciudad vives?", "D.F.");
        preguntas.put("En que escuela estudias?","ESCOM");
        preguntas.put("Que carrera estudias?","Ingenieria en sistemas computacionales");
        preguntas.put("Cuando acaba el semestre?","Julio de 2018");
        preguntas.put("A que te dedicas?","Soy un servidor");
        preguntas.put("Estas vivo?","Si");
        preguntas.put("Tienes hambre?","No");
        preguntas.put("Tienes suenio?","No");
        preguntas.put("Estas cansado?","Si");
        Thread hilop = new Thread(new Runnable() {
            public void run() {
                try {
                    servidor = new ServerSocket(5000);
                    while (true) {
                        socket = servidor.accept();
                        leer();
                    }
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
                        mensaje = lector.readLine();
                        System.out.println(socket.getInetAddress() + " dice: " + mensaje);
                        escribe = new PrintWriter(socket.getOutputStream(), true);
                        if(preguntas.containsKey(mensaje)){
                            System.out.println("Servidor contesta: "+preguntas.get(mensaje));
                            escribe.println(preguntas.get(mensaje));
                        }else{
                            escribe.println("No puedo contestar eso");
                        }
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
                    if(preguntas.containsKey(mensaje)){
                        escribe.println(preguntas.get(mensaje));
                        System.out.println("Servidor contesta: " + preguntas.get(mensaje));
                    }else{
                        System.out.println("Servidor contesta: No te puedo contestar eso");
                        escribe.println("No te puedo contestar eso");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        hilo_contesta.start();
    }

    public static void main(String[] s) {
        new Servidor();
    }
}
