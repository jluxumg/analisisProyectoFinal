package facturador.manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import facturador.db.Conexion;
import facturador.beans.DetalleFactura;
import facturador.beans.EncabezadoFactura;
import facturador.beans.Producto;

public class ManejadorDeDetalleFactura {

    private ArrayList<DetalleFactura> listaDeDetalle;
    private PreparedStatement psAgregar;
    private PreparedStatement psModificar;
    private PreparedStatement psEliminar;
    private static ManejadorDeDetalleFactura instancia;

    public ManejadorDeDetalleFactura() {
        try {
            psAgregar = Conexion.getInstancia().getConnection().prepareStatement("insert into detalleFactura(numeroFactura,correlativo,idProducto,cantidad,precioUnitario,precioTotal)values(?,?,?,?,?,?)");
            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update detalleFactura set correlativo=?,idProducto=?,cantidad=?,precioUnitario=?,precioTotal=? where numeroFactura=?");
            psEliminar = Conexion.getInstancia().getConnection().prepareStatement("delete from detalleFactura where numeroFactura=? and correlativo=?");
        } catch (SQLException e) {
        }
    }

    public static ManejadorDeDetalleFactura getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeDetalleFactura();
        }
        return instancia;
    }

    public ArrayList<DetalleFactura> listar() {
        listaDeDetalle = new ArrayList<DetalleFactura>();
        ResultSet resultado = null;
        try {
            resultado = Conexion.getInstancia().hacerConsulta("select numeroFactura,correlativo,idProducto,cantidad,precioUnitario,precioTotal from detalleFactura ");
            while (resultado.next()) {
                EncabezadoFactura factura = new EncabezadoFactura();
                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("idProducto"));
                factura.setIdFactura(resultado.getInt("numeroFactura"));
                listaDeDetalle.add(new DetalleFactura(factura, resultado.getInt("Correlativo"), producto, resultado.getInt("cantidad"), resultado.getDouble("precioUnitario"), resultado.getDouble("precioTotal")));
            }
        } catch (SQLException e) {
        }
        return listaDeDetalle;
    }

    public ArrayList<DetalleFactura> buscar(String buscar) {
        listaDeDetalle = new ArrayList<DetalleFactura>();
        ResultSet resultado = null;
        try {
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura,cliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha from detalleFactura where idFactura ='" + buscar + "'");
            while (resultado.next()) {
                EncabezadoFactura factura = new EncabezadoFactura();
                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("idProducto"));
                factura.setIdFactura(resultado.getInt("numeroFactura"));
                listaDeDetalle.add(new DetalleFactura(factura, resultado.getInt("Correlativo"), producto, resultado.getInt("cantidad"), resultado.getDouble("precioUnitario"), resultado.getDouble("precioTotal")));
            }
        } catch (SQLException e) {
        }
        return listaDeDetalle;
    }

    public void agregar(DetalleFactura detalleFactura) {
        try {
            psAgregar.setInt(1, detalleFactura.getFactura().getIdFactura());
            psAgregar.setInt(2, detalleFactura.getCorrelativo());
            psAgregar.setInt(3, detalleFactura.getProducto().getIdProducto());
            psAgregar.setInt(4, detalleFactura.getCantidad());
            psAgregar.setDouble(5, detalleFactura.getPrecioUnitario());
            psAgregar.setDouble(6, detalleFactura.getPrecioTotal());
            psAgregar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(DetalleFactura detalleFactura) {
        try {
            psEliminar.setInt(1, detalleFactura.getFactura().getIdFactura());
            psEliminar.setInt(2, detalleFactura.getCorrelativo());
            psEliminar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(DetalleFactura detalleFactura) {
        try {
            psModificar.setInt(1, detalleFactura.getCorrelativo());
            psModificar.setInt(2, detalleFactura.getProducto().getIdProducto());
            psModificar.setInt(3, detalleFactura.getCantidad());
            psModificar.setDouble(4, detalleFactura.getPrecioUnitario());
            psModificar.setDouble(5, detalleFactura.getPrecioTotal());
            psModificar.setInt(6, detalleFactura.getFactura().getIdFactura());
            psModificar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        listar();
    }
}
