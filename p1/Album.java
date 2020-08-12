import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Album extends JFrame implements ActionListener {

    private int indice;
    private int imgs;
    private JButton ant, sig;
    private JLabel img_label;

    public Album() {
        indice = 1;
	imgs=15;
        setLayout(null);
        setLocation(50, 80);
        setTitle("Album");
        setResizable(false);
        setSize(295, 280);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        ant = new JButton("Anterior");
        sig = new JButton("Siguiente");
        img_label = new JLabel();
        add(img_label);
        img_label.setBounds(15, 15, 255, 170);
        add(ant);
        ant.setBounds(15, 200, 120, 20);
        ant.addActionListener(this);
        add(sig);
        sig.setBounds(150, 200, 120, 20);
        sig.addActionListener(this);
        setImagen();
    }
	
	public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == ant) {
            if (indice == 1) {
                indice = imgs;
            } else {
                indice--;
            }
        }
        if (btn == sig) {
            if (indice == imgs) {
                indice = 1;
            } else {
                indice++;
            }
        }
        setImagen();
    }
    
    private void setImagen(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(
                    indice+".jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        java.awt.Image image_scaled = img.getScaledInstance(img_label.getWidth(),
                img_label.getHeight(),
                java.awt.Image.SCALE_SMOOTH);
        javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(image_scaled);
        img_label.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        Album miAlbum = new Album();
        miAlbum.setVisible(true);
    }
}
