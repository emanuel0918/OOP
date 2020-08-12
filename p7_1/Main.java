// javac -cp .:mysql-connector.jar Main.java
// java -cp .:mysql-connector.jar Main
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Main extends JFrame implements ActionListener {

    private JLabel nombre_lbl;
    private JLabel raza_lbl;
    private JLabel edad_lbl;
    private JLabel genero_lbl;
    private JLabel imagen_lbl;
    private JTextField nombre_txtf;
    private JTextField raza_txtf;
    private JTextField edad_txtf;
    private JComboBox<String> genero_combobox;
    private JButton conectar_btn;
    private JButton consultar_btn;
    private JButton insertar_btn;
    private JButton buscarImagen_btn;
    private File imagen;
    private sql_helper s;
    private Perro p;

    public Main() {
        imagen=new File("/home/emanuel_9809/Dropbox/poo/p7_1/perros/perro.png");
        //Interfaz
        setTitle("Perro");
        setLocation(180, 70);
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        //Componentes
        nombre_lbl = new JLabel("Nombre:");
        raza_lbl = new JLabel("Raza:");
        edad_lbl = new JLabel("Edad:");
        genero_lbl = new JLabel("Genero:");
        imagen_lbl = new JLabel();

        nombre_txtf = new JTextField();
        raza_txtf = new JTextField();
        edad_txtf = new JTextField();
        genero_combobox = new JComboBox<String>();
        genero_combobox.addItem("M");
        genero_combobox.addItem("F");

        consultar_btn = new JButton("Consultar");
        conectar_btn = new JButton("Conectar");
        insertar_btn = new JButton("Insertar");
        buscarImagen_btn = new JButton("Seleccionar Imagen");

        add(nombre_lbl);
        nombre_lbl.setBounds(15, 20, 100, 15);
        add(raza_lbl);
        raza_lbl.setBounds(15, 50, 100, 15);
        add(edad_lbl);
        edad_lbl.setBounds(15, 80, 100, 15);
        add(genero_lbl);
        genero_lbl.setBounds(15, 110, 100, 15);
        add(imagen_lbl);
        imagen_lbl.setBounds(170, 140, 200, 200);

        add(nombre_txtf);
        nombre_txtf.setBounds(120, 15, 300, 20);
        add(raza_txtf);
        raza_txtf.setBounds(120, 45, 300, 20);
        add(edad_txtf);
        edad_txtf.setBounds(120, 75, 300, 20);
        add(genero_combobox);
        genero_combobox.setBounds(120, 105, 300, 20);
        add(buscarImagen_btn);
        buscarImagen_btn.setBounds(120, 355, 300, 20);
        edad_txtf.addKeyListener(
                new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!charNumerico(caracter,
                        edad_txtf.getText().contains("."))) {
                    e.consume();
                }
            }
        });

        add(conectar_btn);
        conectar_btn.setBounds(120, 385, 148, 20);
        add(consultar_btn);
        consultar_btn.setBounds(272, 415, 148, 20);
        add(insertar_btn);
        insertar_btn.setBounds(272, 385, 148, 20);
        consultar_btn.addActionListener(this);
        conectar_btn.addActionListener(this);
        insertar_btn.addActionListener(this);
        buscarImagen_btn.addActionListener(this);

        ImageIcon imageIcon = escalarImagen("perro.png",
                imagen_lbl.getWidth(), imagen_lbl.getHeight());
        imagen_lbl.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }

    
  public void conectarbd(){
    s = new sql_helper();
    if(s.conexion== null)
        s= null;
            JOptionPane.showMessageDialog(null, "Conectado");
  }
  public Perro consultarPerro(String nombre){
    Perro perro=null;
        if(s == null){
                JOptionPane.showMessageDialog(null, "Falta conectarse");
        }else{
            perro=s.consultar(nombre);
        }
        if(perro.equals(null)){
                JOptionPane.showMessageDialog(null, "Perro no encontrado");
        }
        return perro;
    }
  public void insertarperro(){
        if(s == null){
                JOptionPane.showMessageDialog(null, "Falta conectarse");
        }else{
                int edad;
                try {
                    edad = Integer.parseInt(edad_txtf.getText());
                } catch (NumberFormatException ex) {
                    edad = 0;
                }
                String genero_s = (String) genero_combobox.getSelectedItem();
                char genero = genero_s.charAt(0);
                if (genero != 'M' || genero != 'F') {
                    if (genero == 'f') {
                        genero = 'F';
                    } else {
                        genero = 'M';
                    }
                }
                p = new Perro(nombre_txtf.getText(),raza_txtf.getText(),edad,genero,imagen);
            s.insertar(p);
            JOptionPane.showMessageDialog(null, "Perro dado de alta");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        JButton btn = (JButton) ae.getSource();
        if (btn == consultar_btn) {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del perro:");
            try{
                consultaVista(consultarPerro(nombre));
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Perro no encontrado");
            }
        }
        if (btn == insertar_btn) {
            insertarperro();
        }
        if (btn == buscarImagen_btn) {
            JFileChooser jfc = new JFileChooser(
                    FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Escoge una imagen");
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "imagenes", "png", "jpeg", "jpg");
            jfc.addChoosableFileFilter(filter);
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                imagen = jfc.getSelectedFile();
                ImageIcon imageIcon = escalarImagen(imagen.getPath(),
                        imagen_lbl.getWidth(), imagen_lbl.getHeight());
                imagen_lbl.setIcon(imageIcon);
            }
        }

    if(btn==conectar_btn){
        conectarbd();
   }
    }

    private ImageIcon escalarImagen(String imagen_path, int width, int height) {
        ImageIcon imageIcon;
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagen_path));
            Image image_scaled = bufferedImage.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image_scaled);
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            imageIcon = null;
        }
        return imageIcon;
    }

    public boolean charNumerico(char c, boolean punto) {
        boolean numerico = true;
        if (!((((c == '0')
                || (c == '1'))
                || (c == '2')
                || (c == '3')
                || (c == '4')
                || (c == '5')
                || (c == '6')
                || (c == '7')
                || (c == '8')
                || (c == '9'))
                && (c != '\b'))) {
            numerico = false;
        }
        if (!punto) {
            if (c == '.') {
                numerico = true;
            }
        }
        return numerico;
    }



    private void consultaVista(Perro p){
        JFrame consulta = new JFrame();
        //Interfaz
        consulta.setTitle("Perro");
        consulta.setLocation(800, 120);
        consulta.setSize(500, 400);
        consulta.setResizable(false);
        consulta.setLayout(null);
        //Componentes
        JLabel nombre_lbl = new JLabel("Nombre:");
        JLabel raza_lbl = new JLabel("Raza:");
        JLabel edad_lbl = new JLabel("Edad:");
        JLabel genero_lbl = new JLabel("Genero:");
        JLabel imagen_lbl = new JLabel();

        JTextField nombre_txtf = new JTextField(p.getNombre());
        JTextField raza_txtf = new JTextField(p.getRaza());
        JTextField edad_txtf = new JTextField(p.getEdad()+"");
        JTextField genero_txtf = new JTextField(p.getGenero()+"");

        consulta.add(nombre_lbl);
        nombre_lbl.setBounds(15, 20, 100, 15);
        consulta.add(raza_lbl);
        raza_lbl.setBounds(15, 50, 100, 15);
        consulta.add(edad_lbl);
        edad_lbl.setBounds(15, 80, 100, 15);
        consulta.add(genero_lbl);
        genero_lbl.setBounds(15, 110, 100, 15);
        consulta.add(imagen_lbl);
        imagen_lbl.setBounds(170, 140, 200, 200);

        consulta.add(nombre_txtf);
        nombre_txtf.setBounds(120, 15, 300, 20);
        nombre_txtf.setEditable(false);
        consulta.add(raza_txtf);
        raza_txtf.setBounds(120, 45, 300, 20);
        raza_txtf.setEditable(false);
        consulta.add(edad_txtf);
        edad_txtf.setBounds(120, 75, 300, 20);
        edad_txtf.setEditable(false);
        consulta.add(genero_txtf);
        genero_txtf.setBounds(120, 105, 300, 20);
        genero_txtf.setEditable(false);

        ImageIcon imageIcon = escalarImagen(p.getImagen().getPath(),
                imagen_lbl.getWidth(), imagen_lbl.getHeight());
        imagen_lbl.setIcon(imageIcon);
        consulta.setVisible(true);
    }
}
