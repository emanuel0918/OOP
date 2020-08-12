import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;
public class MarcoDig extends JFrame implements Runnable, ActionListener {
  Vector <String> nombres = new Vector<String>();
  Panel p;
  ImageIcon iconos[];
  JLabel jlimg;
  JComboBox<String> combo;
  JButton b;
  Thread hilo;
  int retardo;

  public MarcoDig() {
    p = new Panel();
    hilo = new Thread(this);
    for (int i = 0; i < 40; i++)
      nombres.addElement((i + 1) + ".jpg");

    combo = new JComboBox<String>();
    combo.addItem("1"); combo.addItem("2"); combo.addItem("3"); combo.addItem("4"); combo.addItem("5");
    b = new JButton("Cambia");
    b.addActionListener(this);

    iconos = new ImageIcon[nombres.size()];
    for (int i = 0; i < 40; i++){
      iconos[i] = escalarImagen(nombres.elementAt(i),200,200);
    }
    jlimg = new JLabel();
    p.add(b);
    p.add(jlimg);
    jlimg.setIcon(iconos[0]);

    Container content = getContentPane();
    content.add(combo, BorderLayout.NORTH);
    content.add(p, BorderLayout.CENTER);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(400, 300);
    setVisible(true);
  }

  public void run() {
    int i=0;
    while(true){
      jlimg.setIcon(iconos[i%40]);
      try {
        hilo.sleep(retardo);
      } catch (Exception k) {
        return;

      }
      i++;
    }
  }
  public void actionPerformed(ActionEvent e) {
    hilo = new Thread(this);
      String s = (String)combo.getSelectedItem();
	    int seg =Integer.parseInt(s);
	    retardo=(seg%5)*1000+1000;
      hilo.start();
  }
    private ImageIcon escalarImagen(String imagen_path,int width,int height){
        String ruta="";
        String formato=".jpg";
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imagen_path));
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        Image image_scaled = bufferedImage.getScaledInstance(width,height,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image_scaled);
        return imageIcon;
    }

  public static void main(String[] args) { new MarcoDig(); }
}
