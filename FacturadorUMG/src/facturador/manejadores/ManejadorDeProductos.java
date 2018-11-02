package facturador.manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import facturador.db.Conexion;
import facturador.beans.Producto;

public class ManejadorDeProductos {

    private ArrayList<Producto> listaDeProductos;
    private PreparedStatement psAgregar;
    private PreparedStatement psModificar;
    private PreparedStatement psEliminar;
    private static ManejadorDeProductos instancia;

    public ManejadorDeProductos() {
        try {
            psAgregar = Conexion.getInstancia().getConnection().prepareStatement("insert into Producto(idProducto,nombre,descripcion,precio,cantidadTotal,cantidadVendida,cantidadDisponible)values(?,?,?,?,?,?,?)");
            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update Producto set nombre=?,descripcion=?,precio=?,cantidadTotal=?,cantidadVendida=?,cantidadDisponible=? where idProducto=?");
            psEliminar = Conexion.getInstancia().getConnection().prepareStatement("delete from Producto where idProducto=?");
        } catch (SQLException e) {
        }
    }

    public static ManejadorDeProductos getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeProductos();
        }
        return instancia;
    }

    public ArrayList<Producto> listar() {
        listaDeProductos = new ArrayList<Producto>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idProducto,nombre,descripcion,precio,cantidadTotal,cantidadVendida,cantidadDisponible from Producto ");
            while (resultado.next()) {
                listaDeProductos.add(new Producto(resultado.getInt("idProducto"), resultado.getString("nombre"), resultado.getString("descripcion"), resultado.getDouble("precio"), resultado.getInt("cantidadTotal"), resultado.getInt("cantidadVendida"), resultado.getInt("cantidadDisponible")));
            }
        } catch (SQLException e) {
        }
        return listaDeProductos;
    }

    public ArrayList<Producto> buscar(String buscar) {
        listaDeProductos = new ArrayList<Producto>();
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idProducto,nombre,descripcion,precio,cantidadTotal,cantidadVendida,cantidadDisponible from Producto where UPPER(nombre) like'%" + (buscar == null ? "" : buscar.toUpperCase()) + "%'");
            while (resultado.next()) {
                listaDeProductos.add(new Producto(resultado.getInt("idProducto"), resultado.getString("nombre"), resultado.getString("descripcion"), resultado.getDouble("precio"), resultado.getInt("cantidadTotal"), resultado.getInt("cantidadVendida"), resultado.getInt("cantidadDisponible")));
            }
        } catch (SQLException e) {
        }
        return listaDeProductos;
    }

    public void agregar(Producto producto) {
        try {

            ResultSet resultado = null;
            int codigo = 1;
            try {
                resultado = Conexion.getInstancia().hacerConsulta("select Max(idProducto) from Producto ");
                if (resultado.next()) {
                    if (resultado.getInt(1) > 0) {
                        codigo = resultado.getInt(1) + 1;
                    }
                }
            } catch (SQLException e) {
            }
            psAgregar.setInt(1, codigo);

            psAgregar.setString(2, producto.getNombre());
            psAgregar.setString(3, producto.getDescripcion());
            psAgregar.setDouble(4, producto.getPrecio());
            psAgregar.setInt(5, producto.getCantidadTotal());
            psAgregar.setInt(6, producto.getCantidadVendida());
            psAgregar.setInt(7, producto.getCantidadDisponible());
            psAgregar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Producto producto) {
        try {
            psEliminar.setInt(1, producto.getIdProducto());
            psEliminar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(Producto producto) {
        try {

            psModificar.setString(1, producto.getNombre());
            psModificar.setString(2, producto.getDescripcion());
            psModificar.setDouble(3, producto.getPrecio());
            psModificar.setInt(4, producto.getCantidadTotal());
            psModificar.setInt(5, producto.getCantidadVendida());
            psModificar.setInt(6, producto.getCantidadDisponible());
            psModificar.setInt(7, producto.getIdProducto());
            psModificar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        listar();
    }
}
