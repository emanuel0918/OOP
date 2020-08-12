
import java.io.File;
public class Perro {
    private String nombre;
    private String raza;
    private int edad;
    private char genero;
    private File imagen;
    
    public Perro(){
        
    }
    public Perro (String nombre, String raza,
        int edad, char genero, File imagen){
        this.nombre=nombre;
        this.raza=raza;
        this.edad=edad;
        this.genero=genero;
        this.imagen=imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

}
