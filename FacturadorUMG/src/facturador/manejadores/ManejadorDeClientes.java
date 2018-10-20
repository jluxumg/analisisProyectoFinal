package facturador.manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import facturador.db.Conexion;
import facturador.beans.Clientes;

public class ManejadorDeClientes {

    private ArrayList<Clientes> listaDeClientes;
    private PreparedStatement psAgregar;
    private PreparedStatement psModificar;
    private PreparedStatement psEliminar;
    private static ManejadorDeClientes instancia;

    public ManejadorDeClientes() {
        try {
            psAgregar = Conexion.getInstancia().getConnection().prepareStatement("insert into cliente(idCliente,nombre,apellido,tipoDocumento,documento,direccion,telefono,correo)values(?,?,?,?,?,?,?,?)");
            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update cliente set cliente=?, nombre = ?, apellido=?, tipoDocumento=?,documento=?,direccion=?,telefono=?,correo=? where idCliente = ?");
            psEliminar = Conexion.getInstancia().getConnection().prepareStatement("delete from cliente where idCliente = ?");
        } catch (SQLException e) {
        }
    }

    public static ManejadorDeClientes getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeClientes();
        }
        return instancia;
    }

    public ArrayList<Clientes> listaClientes() {
        listaDeClientes = new ArrayList<Clientes>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idCliente,nombre,apellido,tipoDocumento,documento,direccion,telefono,correo from cliente ");
            while (resultado.next()) {
                listaDeClientes.add(new Clientes(resultado.getInt("idCliente"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("tipoDocumento"), resultado.getString("documento"), resultado.getString("direccion"), resultado.getString("telefono"), resultado.getString("correo")));
            }
        } catch (SQLException e) {
        }
        return listaDeClientes;
    }

    public ArrayList<Clientes> BuscarCliente(String buscar) {
        listaDeClientes = new ArrayList<Clientes>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idCliente,nombre,apellido,tipoDocumento,documento,direccion,telefono,correo from clientes where nombre||' '||apellido = " + " like'%" + buscar + "%'");
            while (resultado.next()) {
                listaDeClientes.add(new Clientes(resultado.getInt("idCliente"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("tipoDocumento"), resultado.getString("documento"), resultado.getString("direccion"), resultado.getString("telefono"), resultado.getString("correo")));
            }
        } catch (SQLException e) {
        }
        return listaDeClientes;
    }

    public void agregarCliente(Clientes clientes) {
        try {
            psAgregar.setInt(1, clientes.getIdCliente());
            psAgregar.setString(2, clientes.getNombre());
            psAgregar.setString(3, clientes.getApellido());
            psAgregar.setString(4, clientes.getTipoDocumento());
            psAgregar.setString(5, clientes.getDocumento());
            psAgregar.setString(6, clientes.getDireccion());
            psAgregar.setString(7, clientes.getTelefono());
            psAgregar.setString(8, clientes.getCorreo());
            psAgregar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(Clientes clientes) {
        try {
            psEliminar.setInt(1, clientes.getIdCliente());
            psEliminar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarCliente(Clientes clientes) {
        try {

            psAgregar.setString(1, clientes.getNombre());
            psAgregar.setString(2, clientes.getApellido());
            psAgregar.setString(3, clientes.getTipoDocumento());
            psAgregar.setString(4, clientes.getDocumento());
            psAgregar.setString(5, clientes.getDireccion());
            psAgregar.setString(6, clientes.getTelefono());
            psAgregar.setString(7, clientes.getCorreo());
            psModificar.setInt(8, clientes.getIdCliente());
            psModificar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        listaClientes();
    }
}
