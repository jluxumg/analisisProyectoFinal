package facturador.manejadores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import facturador.db.Conexion;
import facturador.beans.Recordatorios;
import facturador.ventanas.VentanaLogin;
import facturador.ventanas.VentanaPrincipal;

public class ManejadorUsuarios {

    private ArrayList<Recordatorios> listaRecordatorios;
    private PreparedStatement psModificar;
    private static ManejadorUsuarios instancia;
    public ManejadorUsuarios(){
        try{

            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update clientes set cliente=?, compañia = ?, giro=?, direccionCompleta=?,correo=?,telefono1=?,telefono2=?,fax=?,celular=?,tipoCliente=?,vendedor=? where idCliente = ?");
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static ManejadorUsuarios getInstancia(){
        if(instancia == null){
            instancia = new ManejadorUsuarios();
        }
        return instancia;
    }
    public ArrayList<Recordatorios> listaRecordatorios(){
        listaRecordatorios = new ArrayList<Recordatorios>();
        ResultSet resultado = null;
        try{
            //JOptionPane.showMessageDialog(null, VentanaPrincipal.getInstancia().lblFecha.getText());
            resultado = Conexion.getInstancia().hacerConsulta("select * from seguimiento where vendedor = '"+VentanaLogin.getInstancia().vendedor+"' and fecha= '"+VentanaPrincipal.getInstancia().lblFecha.getText()+"'");
            while(resultado.next()){
                 listaRecordatorios.add(new Recordatorios(resultado.getInt("idSeguimiento"),resultado.getInt("idCliente"),resultado.getString("fecha"),resultado.getString("observaciones"),resultado.getString("vendedor")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listaRecordatorios;
    }
   
    public void actualizar(){
        listaRecordatorios();
    }
}
