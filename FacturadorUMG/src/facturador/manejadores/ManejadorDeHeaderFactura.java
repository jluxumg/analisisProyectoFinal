package facturador.manejadores;

import facturador.beans.Clientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import facturador.db.Conexion;
import facturador.beans.EncabezadoFactura;
import facturador.beans.Usuario;
import java.sql.Date;

public class ManejadorDeHeaderFactura {

    private ArrayList<EncabezadoFactura> listaDeHeader;
    private PreparedStatement psAgregar;
    private PreparedStatement psModificar;
    private PreparedStatement psEliminar;
    private static ManejadorDeHeaderFactura instancia;

    public ManejadorDeHeaderFactura() {
        try {
            psAgregar = Conexion.getInstancia().getConnection().prepareStatement("insert into encabezadoFactura(idFactura,cliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha)values(?,?,?,?,?,?,?,?)");
            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update encabezadoFactura set cliente=?,usuario=?,direccionFactura=?,estado=?,documentoFiscal=?,totalFactura=?,fecha=? where idFactura=?");
            psEliminar = Conexion.getInstancia().getConnection().prepareStatement("delete from encabezadoFactura where idFactura=?");
        } catch (SQLException e) {
        }
    }

    public static ManejadorDeHeaderFactura getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeHeaderFactura();
        }
        return instancia;
    }

    public ArrayList<EncabezadoFactura> listar() {
        listaDeHeader = new ArrayList<EncabezadoFactura>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura,cliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha from headerFactura ");
            while (resultado.next()) {
                Clientes cliente = new Clientes();
                Usuario usuario = new Usuario();
                cliente.setIdCliente(resultado.getInt("cliente"));
                usuario.setUsuario(resultado.getString("usuario"));
                listaDeHeader.add(new EncabezadoFactura(resultado.getInt("idFactura"), cliente, usuario, resultado.getString("direccionFactura"), resultado.getString("estado"), resultado.getString("documentoFiscal"), resultado.getDouble("totalFactura"), resultado.getDate("fecha")));
            }
        } catch (SQLException e) {
        }
        return listaDeHeader;
    }

    public ArrayList<EncabezadoFactura> buscar(String buscar) {
        listaDeHeader = new ArrayList<EncabezadoFactura>();
        ResultSet resultado = null;
        try {
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura,cliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha from headerFactura where idFactura ='" + buscar + "'");
            while (resultado.next()) {
                Clientes cliente = new Clientes();
                Usuario usuario = new Usuario();
                cliente.setIdCliente(resultado.getInt("cliente"));
                usuario.setUsuario(resultado.getString("usuario"));
                listaDeHeader.add(new EncabezadoFactura(resultado.getInt("idFactura"), cliente, usuario, resultado.getString("direccionFactura"), resultado.getString("estado"), resultado.getString("documentoFiscal"), resultado.getDouble("totalFactura"), resultado.getDate("fecha")));
            }
        } catch (SQLException e) {
        }
        return listaDeHeader;
    }

    public void agregar(EncabezadoFactura encabezadoFactura) {
        try {
            psAgregar.setInt(1, encabezadoFactura.getIdFactura());
            psAgregar.setInt(2, encabezadoFactura.getCliente().getIdCliente());
            psAgregar.setString(3, encabezadoFactura.getUsuario().getUsuario());
            psAgregar.setString(4, encabezadoFactura.getDireccionFactura());
            psAgregar.setString(5, encabezadoFactura.getEstado());
            psAgregar.setString(6, encabezadoFactura.getDocumentoFiscal());
            psAgregar.setDouble(7, encabezadoFactura.getTotalFactura());
            psAgregar.setDate(8, (Date) encabezadoFactura.getFecha());
            psAgregar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(EncabezadoFactura encabezadoFactura) {
        try {
            psEliminar.setInt(1, encabezadoFactura.getIdFactura());
            psEliminar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(EncabezadoFactura encabezadoFactura) {
        try {
            psModificar.setInt(1, encabezadoFactura.getCliente().getIdCliente());
            psModificar.setString(2, encabezadoFactura.getUsuario().getUsuario());
            psModificar.setString(3, encabezadoFactura.getDireccionFactura());
            psModificar.setString(4, encabezadoFactura.getEstado());
            psModificar.setString(5, encabezadoFactura.getDocumentoFiscal());
            psModificar.setDouble(6, encabezadoFactura.getTotalFactura());
            psModificar.setDate(7, (Date) encabezadoFactura.getFecha());
            psModificar.setInt(8, encabezadoFactura.getIdFactura());
            psModificar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        listar();
    }
}
