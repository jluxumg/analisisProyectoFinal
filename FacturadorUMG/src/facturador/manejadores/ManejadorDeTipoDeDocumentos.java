package facturador.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import facturador.db.Conexion;
import facturador.beans.TipoDocumento;

public class ManejadorDeTipoDeDocumentos {

    private ArrayList<TipoDocumento> listaDeTiposDocumentos;

    private static ManejadorDeTipoDeDocumentos instancia;

    public ManejadorDeTipoDeDocumentos() {

    }

    public static ManejadorDeTipoDeDocumentos getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeTipoDeDocumentos();
        }
        return instancia;
    }

    public ArrayList<TipoDocumento> listar() {
        listaDeTiposDocumentos = new ArrayList<TipoDocumento>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idTipoDocumento,descripcion from TipoDocumento ");
            while (resultado.next()) {
                listaDeTiposDocumentos.add(new TipoDocumento(resultado.getString("idTipoDocumento"), resultado.getString("descripcion")));
            }
        } catch (SQLException e) {
        }
        return listaDeTiposDocumentos;
    }

    public ArrayList<TipoDocumento> buscar(String buscar) {
        listaDeTiposDocumentos = new ArrayList<TipoDocumento>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select dTipoDocumento,descripcion from TipoDocumento where descripcion like'%" + buscar + "%'");
            while (resultado.next()) {
                listaDeTiposDocumentos.add(new TipoDocumento(resultado.getString("idTipoDocumento"), resultado.getString("descripcion")));
            }
        } catch (SQLException e) {
        }
        return listaDeTiposDocumentos;
    }

    public void actualizar() {
        listar();
    }
}
