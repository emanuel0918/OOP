// javac -cp .;j3dcore.jar;j3dutils.jar;vecmath.jar SolarSis.java
// java -cp .;j3dcore.jar;j3dutils.jar;vecmath.jar SolarSis
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class SolarSis {
	public SolarSis() {
		BranchGroup group = new BranchGroup();
		Appearance appsol = new Appearance();
		Appearance appearth = new Appearance();
		Appearance appmercurio = new Appearance();//Crear la apariencia
		Appearance appvenus = new Appearance();

		TextureLoader tex = new TextureLoader("TIERRA.JPG", null);
		appearth.setTexture(tex.getTexture());
		tex = new TextureLoader("SOL.JPG", null);
		appsol.setTexture(tex.getTexture());
		tex = new TextureLoader("MERCURIO.JPG", null);
		appmercurio.setTexture(tex.getTexture());
		tex = new TextureLoader("VENUS.JPG", null);
		appvenus.setTexture(tex.getTexture());//Agregar y cambiar la textura

		Sphere earth = new Sphere(0.067f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, appearth);
		Sphere sol = new Sphere(0.4f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, appsol);
		Sphere mercurio = new Sphere(0.03f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, appmercurio);
		Sphere venus = new Sphere(0.064f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, appvenus);//Construir las esferas y definir su tamaño

		//Rotar los planetas sobre su propio eje definiendo su velocida de rotacion
		TransformGroup earthRotXformGroup = Posi.rotate(earth, new Alpha(-1, 800));//24 hrs
		TransformGroup mercurioRotXformGroup = Posi.rotate(mercurio, new Alpha(-1, 5200));//58 dias
		TransformGroup venusRotXformGroup = Posi.rotate(venus, new Alpha(-1, 10400));//116 dias
		TransformGroup solRotXformGroup = Posi.rotate(sol, new Alpha(-1, 2200));//25 dias

		TransformGroup earthTransXformGroup = Posi.translate(earthRotXformGroup, new Vector3f(0.0f, 0.0f, 0.9f));//Definir posicion del planeta
		TransformGroup earthRotGroupXformGroup = Posi.rotate(earthTransXformGroup, new Alpha(-1, 26800));//Definir velocidad de traslacion= 365 dias

		TransformGroup mercurioTransXformGroup = Posi.translate(mercurioRotXformGroup, new Vector3f(0.0f, 0.0f, 0.5f));
		TransformGroup mercurioRotGroupXformGroup = Posi.rotate(mercurioTransXformGroup, new Alpha(-1, 1900));//88 dias

		TransformGroup venusTransXformGroup = Posi.translate(venusRotXformGroup, new Vector3f(0.0f, 0.0f, 0.68f));
		TransformGroup venusRotGroupXformGroup = Posi.rotate(venusTransXformGroup, new Alpha(-1, 16900));//255 dias

		group.addChild(earthRotGroupXformGroup);
		group.addChild(mercurioRotGroupXformGroup);
		group.addChild(venusRotGroupXformGroup);
		group.addChild(solRotXformGroup);
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config); canvas.setSize(400, 400);
		SimpleUniverse universe = new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(group);
		JFrame f = new JFrame("Planetario");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.add(canvas); f.pack(); f.setVisible(true);
	}

	public static void main(String a[]) {
		SolarSis ss = new SolarSis();
	}
}