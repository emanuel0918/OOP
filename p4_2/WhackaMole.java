import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
  import java.util.Random;
public class WhackaMole extends JFrame implements Runnable,ActionListener{
    JButton botones[]; //Botones para la cuadricula
    ImageIcon imagenes[]; // Imagenes
    JLabel score; //Label para el puntaje
    int s = 0; //Puntaje en entero
    Thread t;
    public WhackaMole(){
        Panel panel1 = new Panel(); //Panel 1 para agregar el label de score
        Panel panel2 = new Panel();//Panel 2 para agregar el GridLayout
        double n = 0; //Variable para calcular si el topo sale o no.
        //Label Score
        score = new JLabel("Score: "+s);
        score.setFont(score.getFont().deriveFont(64f));
        panel1.add(score);

        //Cargamos las imagenes
        imagenes = new ImageIcon[3];
        imagenes[0] = new ImageIcon("Pasto.png");
        imagenes[1] = new ImageIcon("Topo.png");
        imagenes[2] = new ImageIcon("TopoMuerto.png");

        //Creamos los botones
        botones = new JButton[20];
        panel2.setLayout(new GridLayout(4,5)); //Grid para los botones
        for(int i=0;i<botones.length;i++){
            n = Math.random();
            if(n>0.5){//si el numero aleatorio es mayor a 0.5 se pon la imagen del topo
                panel2.add(botones[i]=new JButton(imagenes[1]));
                botones[i].addActionListener(this);//Añadimos el ActionListener para los botones
            }
            else{
                panel2.add(botones[i]=new JButton(imagenes[0]));//No se añade actionlistener
            }
        }
        add("North",panel1);//Añadimos los paneles al Frame
        add("Center",panel2);
        t = new Thread(this);//Creamos el thread y lo iniciamos
        t.start();
    }
    public void run(){//En este metodo estara el Thread para cambiar las imagenes
      //t = new Thread(this);
        System.out.println("Pasando una vez por Runnable");
        double n;
        while(true){
         try{
                for(int i=0;i<botones.length;i++){//Por cada boton que tenemos vamos a cambiar las imagenes de los botones
                    n = Math.random();
                    botones[i].removeActionListener(this);//Añadimos el ActionListener para los botones
                    if(n>0.8){
                        botones[i].setIcon(imagenes[1]);
                        botones[i].addActionListener(this);
                    }
                    else{
                        botones[i].setIcon(imagenes[0]);
                    }
                }
                t.sleep(2000);//El tiempo entre cada que cambian las imagenes en milisegundos
            //}
         }catch(Exception e){
           System.out.println(e);
         }}
    }
    public void actionPerformed(ActionEvent e){//Como los botones siempre estan escuchando, pero no siempre estan habilitados
      JButton bt = (JButton)e.getSource();
        bt.setIcon(imagenes[2]);
        bt.removeActionListener(this);//Si se apreto el boton removemos el ActionListener
        s = s+1;//Aumentamos la cuenta
        score.setText("Score: "+s); //Actualizamos el label
    }
    public static void main(String s[]){
        WhackaMole w = new WhackaMole();
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(1024,720);
        w.setVisible(true);
    }
}
