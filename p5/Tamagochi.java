// javac -cp .:j3dcore.jar:j3dutils.jar:vecmath.jar Tamagochi.java
// java -cp .:j3dcore.jar:j3dutils.jar:vecmath.jar Tamagochi
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Tamagochi extends JFrame  implements LeeRed {
	static String preguntas [] = { "COMO TE LLAMAS", "DONDE VIVES"};
	static String respuestas [] = {"MI NOMBRE ES TAMAGOCHI", "EN LA COMPU"};
	private Canvas3D canvas3D;
	private Appearance ap;
	private static Texture texture;
	private JPanel jp1, jp2;
	private JButton bfeliz, btriste, benfermo, benojado, btranquilo;
	private EventHandler eh;
	private String nombres[] = {"feliz.jpg", "enojado.jpg", "triste.jpg", "tranquilo.jpg", "enfermo.jpg"};
	private int turno;
	private MicroChat microChat;
	private Body body;
	private Red r;


	public Tamagochi() {
		super("Tamagochi 3D");
		turno = 0;
		//setResizable(false);
		setSize(400, 500);
		GraphicsConfiguration config =
		    SimpleUniverse.getPreferredConfiguration();
		canvas3D = new Canvas3D(config);
		canvas3D.setSize(300, 400);
		eh = new EventHandler();

		bfeliz = new JButton("Feliz");
		bfeliz.addActionListener(eh);

		btriste = new JButton("Triste");
		btriste.addActionListener(eh);

		benfermo = new JButton("Enfermo");
		benfermo.addActionListener(eh);

		benojado = new JButton("Enojado");
		benojado.addActionListener(eh);

		btranquilo = new JButton("Tranquilo");
		btranquilo.addActionListener(eh);


		jp1 = new JPanel();
		jp1.add(bfeliz);
		jp1.add(benojado);
		jp1.add(btriste);
		jp1.add(benfermo);
		jp1.add(btranquilo);

		add("North", jp1);
		add("Center", canvas3D);

		setup3DGraphics();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		r = new Red(this); //LLama al costructor de la clase Red
		/*
			Se le manda el objeto "r"
			Con el constructor se pide el IP del servidor

		*/
		microChat = new MicroChat(r);
		//add("South", microChat);
	}

	void setup3DGraphics() {
		//Se encarga de la parte 3D
		DirectionalLight light1;
		SimpleUniverse universe = new SimpleUniverse(canvas3D);
		universe.getViewingPlatform().setNominalViewingTransform();
		body = new Body(-0.4f, 0.0f, 0.0f, "", true, this, "Avatar1");
		body.changeTextureCab(texture, "feliz.jpg");
		BranchGroup group = body.mybody();
		Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		universe.addBranchGraph(group);
	}


	public static void main(String[] args) {
		new Tamagochi();
	}


	class EventHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj instanceof JButton) {
				JButton btn = (JButton)e.getSource();
				if (btn == bfeliz) {
					turno = btn.getMnemonic();
				}
				body.changeTextureCab(texture, nombres[turno]);
				r.escribeRed(new Icono("Tamagochi", turno));
			}
		}
	}
	public void leeRed(Object obj) {
		if (obj instanceof Icono) {
			Icono i = (Icono)obj;
			System.out.println("Recibi" + nombres[i.getTurno()]);
			body.changeTextureCab(texture, nombres[i.getTurno()]);
		}
		if (obj instanceof Mensaje) {
			System.out.println("Recibi " + (Mensaje)obj);
			microChat.recibir((Mensaje)obj);
		}
	}
	static String findMatch(String str) {
		String result = "";
		for (int i = 0; i < preguntas.length; ++i) {
			if (preguntas[i].equalsIgnoreCase(str)) {
				result = respuestas[i];
				break;
			}
		}
		return result;
	}
}
