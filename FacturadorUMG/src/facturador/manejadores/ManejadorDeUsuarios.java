package facturador.manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import facturador.db.Conexion;
import facturador.beans.Usuario;
import facturador.sistema.MD5;
import java.security.MessageDigest;

public class ManejadorDeUsuarios {

    private ArrayList<Usuario> listaDeUsuario;
    private PreparedStatement psAgregar;
    private PreparedStatement psModificar;
    private PreparedStatement psEliminar;
    private static ManejadorDeUsuarios instancia;

    public ManejadorDeUsuarios() {
        try {
            psAgregar = Conexion.getInstancia().getConnection().prepareStatement("insert into Usuario(usuario,nombre,apellido,password,estado,rol)values(?,?,?,MD5(?),?,?)");
            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update Usuario set nombre=?,apellido=?,password=?,estado=?,rol=? where usuario=?");
            psEliminar = Conexion.getInstancia().getConnection().prepareStatement("delete from Usuario where usuario=?");
        } catch (SQLException e) {
        }
    }

    public static ManejadorDeUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeUsuarios();
        }
        return instancia;
    }

    public ArrayList<Usuario> listar() {
        listaDeUsuario = new ArrayList<Usuario>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select usuario,nombre,apellido,MD5(password) password,estado,rol from Usuario ");
            while (resultado.next()) {
                listaDeUsuario.add(new Usuario(resultado.getString("usuario"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("password"), resultado.getString("estado"), resultado.getString("rol")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDeUsuario;
    }

    public ArrayList<Usuario> buscar(String buscar) {
        listaDeUsuario = new ArrayList<Usuario>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select usuario,nombre,apellido,MD5(password) password,estado,rol from Usuario where nombre like'%" + buscar + "%'");
            while (resultado.next()) {
                listaDeUsuario.add(new Usuario(resultado.getString("usuario"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("password"), resultado.getString("estado"), resultado.getString("rol")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDeUsuario;
    }

    public void agregar(Usuario usuario) {
        try {
            psAgregar.setString(1, usuario.getUsuario());
            psAgregar.setString(2, usuario.getNombre());
            psAgregar.setString(3, usuario.getApellido());
            psAgregar.setString(4, usuario.getPassword());
            psAgregar.setString(5, usuario.getEstado());
            psAgregar.setString(6, usuario.getRol());
            psAgregar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Usuario usuario) {
        try {
            psEliminar.setString(1, usuario.getUsuario());
            psEliminar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(Usuario usuario) {
        try {

            String hash = MD5.getHash(usuario.getPassword().trim());
            System.out.println("Hash>>>" + hash);
            psModificar.setString(1, usuario.getNombre());
            psModificar.setString(2, usuario.getApellido());
            psModificar.setString(3, hash);
            psModificar.setString(4, usuario.getEstado());
            psModificar.setString(5, usuario.getRol());
            psModificar.setString(6, usuario.getUsuario());
            psModificar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        listar();
    }
}
