package facturador.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class Conexion {
 private Connection conexion;
    private static Conexion instancia;
    private Statement enunciado;
    public synchronized static Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(InstantiationException ex){
           ex.printStackTrace();
        }
        catch(IllegalAccessException ex){
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://172.16.8.80:8889/umgAnalisis?user=ichic&password=ichic23!");
            enunciado = conexion.createStatement();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public ResultSet hacerConsulta(String consulta){
        ResultSet resultado = null;
            try{
                resultado = enunciado.executeQuery(consulta);
            }catch(SQLException ex){
                   ex.printStackTrace();
            }
        return resultado;
    }

     public void ejecutarSentencia(String sentencia){
            try{
              // enunciado.execute(sentencia);
               enunciado.execute(sentencia);
            }catch(SQLException ex){
                   ex.printStackTrace();
            }
    }

    public Connection getConnection(){
        return conexion;
    }
}
