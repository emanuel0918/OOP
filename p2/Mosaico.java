import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Mosaico extends JFrame implements ActionListener {
    private ArrayList<String> imgs;
    private JButton btns[];
    private JLabel imglbl,img_lbl;
    
    public Mosaico(){
        imgs = new ArrayList(Arrays.asList(new String[]{
            "aguila","avestruz","ballena","caballo","camello",
            "cebra","cerdo","cocodrilo","conejo","delfin",
            "elefante","foca","gallina","gallo","gorila",
            "hipopotamo","iguana","jirafa","koala","leon",
            "lobo","mono","morsa","murcielago","nutria","oso",
            "oveja","pato","pinguino","rana","raton","raya",
            "rinoceronte","serpiente","tigre","toro","tortuga",
            "vaca","venado","zorro"
        }));
        //Interfaz
        setTitle("Mosaico");
        setLocation(80, 15);
        setSize(900,700);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Componentes
        btns=new JButton[40];
        imglbl=new JLabel();
        img_lbl=new JLabel();
        Panel p1 = new Panel();
        Panel p2 = new Panel();
        add("North",p1);
        p1.setLayout(null);
        p1.setSize(getWidth(), 150);
        p1.add(imglbl);
        imglbl.setBounds(385, 5, 120, 120);
        ImageIcon imageIcon = escalarImagen(imgs.get(0),imglbl.getWidth(),
                imglbl.getHeight());
        imglbl.setIcon(imageIcon);
        p1.add(img_lbl);
        img_lbl.setBounds(396, 130, 100, 15);
        img_lbl.setHorizontalAlignment(JLabel.CENTER);
        img_lbl.setText("aguila");
        add("Center",p2);
        p2.setLayout(new GridLayout(5,8));
        for(int i=0;i<40;i++){
            btns[i]=new JButton();
            btns[i].setMnemonic(i);
            btns[i].addActionListener(this);
            p2.add(btns[i]);
            imageIcon = escalarImagen(imgs.get(i),107,100);
            btns[i].setIcon(imageIcon);
        }
    }
    private ImageIcon escalarImagen(String imagen_path,int width,int height){
        String ruta="";
        String formato=".jpg";
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(ruta+imagen_path+formato));
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        Image image_scaled = bufferedImage.getScaledInstance(width,height,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image_scaled);
        return imageIcon;
    }
	
    public void actionPerformed(ActionEvent e) {
        JButton btn =(JButton)e.getSource();
        img_lbl.setText(imgs.get(btn.getMnemonic()));
        ImageIcon imageIcon = escalarImagen(imgs.get(btn.getMnemonic()),
                imglbl.getWidth(),imglbl.getHeight());
        imglbl.setIcon(imageIcon);
    }
    public static void main(String[] args) {
        Mosaico m = new Mosaico();
        m.setVisible(true);
    }
}
