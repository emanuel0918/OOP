import java.util.*;
import java.text.*;
import java.sql.*;
import java.awt.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.File;
class sql_helper {
  String conexionUrl = "jdbc:mysql://localhost:3306/practica7";
  String dbUser = "root";
  String dbPwd = "n0m3l0";
  Connection conexion = null;
  PreparedStatement stmt = null;
  public sql_helper(){
    conexion = getConnection();
    }
  public Connection getConnection(){
    Connection conn;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/practica7";
      conn = DriverManager.getConnection(url,"root","n0m3l0");
    }catch (Exception e) {
      conn = null;
      System.out.println(e);
    }
    return conn;
  }
  public Perro consultar(String nombre){
    Perro perro =null;
    try{
      String queryString = "select * from perro where nombre='"+nombre+"';";
      stmt = conexion.prepareStatement(queryString);
      ResultSet r = stmt.executeQuery();
      if(r.next()){
        perro=new Perro();
        perro.setNombre(r.getString("nombre"));
        perro.setRaza(r.getString("raza"));
        perro.setGenero(r.getString("genero").charAt(0));
        int edad;
        try {
            edad = Integer.parseInt(r.getString("edad"));
        } catch (NumberFormatException ex) {
            edad = 0;
        }
        perro.setEdad(edad);

      File imagen=null;
        try{ 
          InputStream inputStream=r.getBinaryStream("imagen");
          imagen= new File(perro.getNombre()+".jpg");
          try (FileOutputStream outputStream
                            = new FileOutputStream(imagen)) {
                        int bits = inputStream.read();
                        while (bits != (-1)) {
                            outputStream.write(bits);
                            bits = inputStream.read();
                        }
                        inputStream.close();
                        outputStream.close();
                    }
        }
        catch(IOException e){
          imagen = new File("perro.png");
        }
      perro.setImagen(imagen);
      }
    
    }catch(Exception e){
      System.out.println(e);
    }
    return perro;

  }
  public void insertar(Perro p){
    try{
      String queryString = "INSERT INTO perro(nombre, raza, edad, genero,imagen) VALUES(?,?,?,?,?);";
      conexion = getConnection();
      FileInputStream foto = new FileInputStream(p.getImagen());
      stmt = conexion.prepareStatement(queryString);
      stmt.setString(1,p.getNombre());
      stmt.setString(2,p.getRaza());
      stmt.setString(3,p.getEdad()+"");
      stmt.setString(4,p.getGenero()+"");
      stmt.setBinaryStream(5, foto, (int) p.getImagen().length()); 
      stmt.executeUpdate();
    
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
