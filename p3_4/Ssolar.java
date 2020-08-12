import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
public class Ssolar implements ActionListener{
  JButton btnX;
  JButton btnY;
  JPanel p;
  TransformGroup Camara;
  int distancia = 10;
  mBehaior Beh;
  public Ssolar(){
    // Botones
    p = new JPanel();
    p.setLayout(new GridLayout(2,1));
    btnX = new JButton("Camara superior");
    btnX.addActionListener(this);
    btnY = new JButton("Camara frontal");
    btnY.addActionListener(this);
    p.add(btnX);
    p.add(btnY);

    // Java3D
    BranchGroup group = new BranchGroup();

    //Apariencia
    Appearance appsol = new Appearance();
    Appearance appmer = new Appearance();
    Appearance appvenus = new Appearance();
    Appearance apptierra = new Appearance();
    Appearance appmarte = new Appearance();
    Appearance appjupiter = new Appearance();

    //Texturas
    TextureLoader tex = new TextureLoader("SOL.JPG",null);
    appsol.setTexture(tex.getTexture());
    tex = new TextureLoader("MERCURIO.JPG",null);
    appmer.setTexture(tex.getTexture());
    tex = new TextureLoader("VENUS.JPG",null);
    appvenus.setTexture(tex.getTexture());
    tex = new TextureLoader("TIERRA.JPG",null);
    apptierra.setTexture(tex.getTexture());
    tex = new TextureLoader("MARTE.JPG",null);
    appmarte.setTexture(tex.getTexture());
    tex = new TextureLoader("JUPITER.JPG",null);
    appjupiter.setTexture(tex.getTexture());

    //Crear esferas
    Sphere sol = new Sphere(0.3f, Primitive.GENERATE_NORMALS |
  	Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
    Sphere mercurio = new Sphere(0.015f, Primitive.GENERATE_NORMALS |
    Primitive.GENERATE_TEXTURE_COORDS, 32, appmer);
    Sphere venus = new Sphere(0.040f, Primitive.GENERATE_NORMALS |
    Primitive.GENERATE_TEXTURE_COORDS, 32, appvenus);
    Sphere tierra = new Sphere(0.045f, Primitive.GENERATE_NORMALS |
  	Primitive.GENERATE_TEXTURE_COORDS, 32, apptierra);
    Sphere marte = new Sphere(0.035f,Primitive.GENERATE_NORMALS|
  	Primitive.GENERATE_TEXTURE_COORDS,32,appmarte);
    Sphere jupiter = new Sphere(0.08f,Primitive.GENERATE_NORMALS|
  	Primitive.GENERATE_TEXTURE_COORDS,32,appjupiter);

    //Rotaciones
    TransformGroup solRotXformGroup = Posi.rotate(sol, new Alpha(-1, 10000));
    TransformGroup mercurioRotXformGroup = Posi.rotate(mercurio, new Alpha(-1, 13200));
    TransformGroup venusRotXformGroup = Posi.rotate(venus, new Alpha(-1, 50000));
    TransformGroup tierraRotXformGroup = Posi.rotate(tierra, new Alpha(-1, 2400));
  	TransformGroup marteRotXformGroup = Posi.rotate(marte,new Alpha(-1,2429));
    TransformGroup jupiterRotXformGroup = Posi.rotate(jupiter,new Alpha(-1,950));

    TransformGroup mercurioTransXformGroup = Posi.translate(mercurioRotXformGroup,
    new Vector3f(0.0f, 0.0f, 0.5f));
    TransformGroup venusTransXformGroup = Posi.translate(venusRotXformGroup,
    new Vector3f(0.0f, 0.0f, -1.0f));
    TransformGroup tierraTransXformGroup = Posi.translate(tierraRotXformGroup,
    new Vector3f(0.0f, 0.0f, 2.5f));
    TransformGroup marteTransXformGroup = Posi.translate(marteRotXformGroup,
    new Vector3f(0.0f, 0.0f, 3.0f));
    TransformGroup jupiterTransXformGroup = Posi.translate(jupiterRotXformGroup,
    new Vector3f(0.0f, 0.0f, 5.0f));

    TransformGroup mercurioRotGroupXformGroup = Posi.rotate(mercurioTransXformGroup, new Alpha(-1, 8800));
    TransformGroup venusRotGroupXformGroup = Posi.rotate(venusTransXformGroup, new Alpha(-1, 22400));
    TransformGroup tierraRotGroupXformGroup = Posi.rotate(tierraTransXformGroup, new Alpha(-1, 36500));
    TransformGroup marteRotGroupXformGroup = Posi.rotate(marteTransXformGroup, new Alpha(-1, 68700));
    TransformGroup jupiterRotGroupXformGroup = Posi.rotate(jupiterTransXformGroup, new Alpha(-1, 100300));

    //Añadimos al grupo
    group.addChild(solRotXformGroup);
    group.addChild(mercurioRotGroupXformGroup);
    group.addChild(venusRotGroupXformGroup);
    group.addChild(tierraRotGroupXformGroup);
    group.addChild(marteRotGroupXformGroup);
    group.addChild(jupiterRotGroupXformGroup);

    //configuraciones
   GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
   Canvas3D canvas = new Canvas3D(config); canvas.setSize(1024, 720);
   SimpleUniverse universe = new SimpleUniverse(canvas);
   universe.getViewingPlatform().setNominalViewingTransform();
   Camara = universe.getViewingPlatform().getViewPlatformTransform();

   //Vista de la Camara
   Transform3D tr1 = new Transform3D();
   tr1.set(new Vector3d(0.0f,0.0f,distancia));
   Camara.setTransform(tr1);

   TransformGroup trans=new TransformGroup ();
   Beh=new mBehaior(universe, trans);
   group.addChild(Beh);
   universe.addBranchGraph(group);
    // Frame
    JPanel u = new JPanel();
    u.add(canvas);
    JFrame f = new JFrame("Planetario");
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setLayout(new BorderLayout());
    f.setSize(1024,720);
    f.add("Center",u);
    f.add("East",p); f.setVisible(true);
  }
  public void actionPerformed(ActionEvent e){
    JButton btn = (JButton)e.getSource();
    Transform3D tr1 = new Transform3D();
    Transform3D tr2 = new Transform3D();
    if(btn == btnX){
      tr1.set(new Vector3d(0.0f,-(distancia+5),0.0f));
      tr2.rotX(Math.PI/180*90);
      tr1.mul(tr2);
      Camara.setTransform(tr1);
    }
    if(btn == btnY){
      tr1.set(new Vector3d(0.0f,0.0f,distancia));
      Camara.setTransform(tr1);
    }
 }
  public static void main(String a[]) { new Ssolar();}
}
