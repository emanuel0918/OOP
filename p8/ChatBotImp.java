import java.rmi.*;
import java.rmi.server.*;

public class ChatBotImp extends UnicastRemoteObject implements ChatBotI {

    private String preguntas[] = {
        "Como te llamas?",
        "En que ciudad vives?",
        "Cuantos anios tines?",
        "En que escuela estudias?",
        "Que carrera estudias?",
        "Cuantas materias llevas?",
        "Donde creciste?",
        "Que idiomas entiendes?",
        "Estas trabajando?",
        "Que deporte te gusta?"
    };

    private String respuestas[] = {
        "Lenovo G35-20",
        "Ciudad de Mexico",
        "2",
        "ESCOM",
        "Ingenieria en Sistemas Computacionales",
        "7",
        "En Ecatepec",
        "Ingles y Español",
        "No",
        "Futbol"
    };

    public ChatBotImp() throws RemoteException {
        super();
    }

    public String responde(String pregunta) throws RemoteException {
        String respuesta = "";
        for (int i = 0; i < preguntas.length; i++) {
            if (pregunta.equals(preguntas[i])) {
                respuesta = respuestas[i];
                break;
            } else
                respuesta = "No te puedo contestar eso";
        }
        return respuesta;
    }
}