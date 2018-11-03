package facturador.manejadores;

import facturador.beans.Clientes;
import facturador.beans.DetalleFactura;
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
    private PreparedStatement psAgregarDetalle;
    private PreparedStatement psModificar;
    private PreparedStatement psEliminar;
    private PreparedStatement psAnular;
    private PreparedStatement psEmitirFactura;
    private PreparedStatement psActualizaInventario;
    private static ManejadorDeHeaderFactura instancia;
    public Integer correlativoFac = 0;

    public ManejadorDeHeaderFactura() {
        try {
            psAgregar = Conexion.getInstancia().getConnection().prepareStatement("insert into EncabezadoFactura(idFactura,cliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha)values(?,?,?,?,?,?,?,now())");
            psModificar = Conexion.getInstancia().getConnection().prepareStatement("update EncabezadoFactura set cliente=?,usuario=?,direccionFactura=?,estado=?,documentoFiscal=?,totalFactura=?,fecha=? where idFactura=?");
            psEliminar = Conexion.getInstancia().getConnection().prepareStatement("delete from EncabezadoFactura where idFactura=?");
            psAnular = Conexion.getInstancia().getConnection().prepareStatement("update EncabezadoFactura set estado = 'ANU' where idFactura=?");
            psEmitirFactura = Conexion.getInstancia().getConnection().prepareStatement("update EncabezadoFactura set estado = 'FAC',documentoFiscal=? where idFactura=?");
            psAgregarDetalle = Conexion.getInstancia().getConnection().prepareStatement("insert into DetalleFactura(numeroFactura,correlativo,idProducto,cantidad,precioUnitario,precioTotal)values(?,?,?,?,?,?)");
            psActualizaInventario = Conexion.getInstancia().getConnection().prepareStatement("update Producto set cantidadVendida=cantidadVendida+?,cantidadDisponible=cantidadDisponible-? where idProducto=?");
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
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura,cliente,CONCAT(nombre, ' ', apellido) nombreCliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha from EncabezadoFactura a left join Cliente b on a.cliente = b.idCliente");
            while (resultado.next()) {
                Clientes cliente = new Clientes();
                Usuario usuario = new Usuario();
                cliente.setIdCliente(resultado.getInt("cliente"));
                cliente.setNombre(resultado.getString("nombreCliente"));
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
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura,cliente,CONCAT(nombre, ' ', apellido) nombreCliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha from EncabezadoFactura a left join Cliente b on a.cliente = b.idCliente where idFactura =" + buscar + "");
            while (resultado.next()) {
                Clientes cliente = new Clientes();
                Usuario usuario = new Usuario();
                cliente.setIdCliente(resultado.getInt("cliente"));
                cliente.setNombre(resultado.getString("nombreCliente"));
                usuario.setUsuario(resultado.getString("usuario"));
                listaDeHeader.add(new EncabezadoFactura(resultado.getInt("idFactura"), cliente, usuario, resultado.getString("direccionFactura"), resultado.getString("estado"), resultado.getString("documentoFiscal"), resultado.getDouble("totalFactura"), resultado.getDate("fecha")));
            }
        } catch (SQLException e) {
        }
        return listaDeHeader;
    }

    public EncabezadoFactura buscar(int buscar) {
        EncabezadoFactura factura = new EncabezadoFactura();
        ResultSet resultado = null;
        try {
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura,cliente,CONCAT(nombre, ' ', apellido) nombreCliente,usuario,direccionFactura,estado,documentoFiscal,totalFactura,fecha from EncabezadoFactura a left join Cliente b on a.cliente = b.idCliente where idFactura =" + buscar + "");
            while (resultado.next()) {
                Clientes cliente = new Clientes();
                Usuario usuario = new Usuario();
                cliente.setIdCliente(resultado.getInt("cliente"));
                cliente.setNombre(resultado.getString("nombreCliente"));
                usuario.setUsuario(resultado.getString("usuario"));
                factura = new EncabezadoFactura(resultado.getInt("idFactura"), cliente, usuario, resultado.getString("direccionFactura"), resultado.getString("estado"), resultado.getString("documentoFiscal"), resultado.getDouble("totalFactura"), resultado.getDate("fecha"));
            }
        } catch (SQLException e) {
        }
        return factura;
    }

    public Integer correlativoFactura() {
        Integer correlativo = 0;
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select max(idFactura)+1 correlativo from EncabezadoFactura");
            while (resultado.next()) {
                correlativo = resultado.getInt("correlativo");
                if (correlativo == null || correlativo == 0) {
                    correlativo = 1;
                }
            }
        } catch (SQLException e) {
        }
        return correlativo;
    }

    public Integer correlativoFiscal(int fac) {
        Integer correlativo = 0;
        ResultSet resultado = null;
        try {
            //JOptionPane.showMessageDialog(null, VentanaLogin.getInstancia().vendedor);
            resultado = Conexion.getInstancia().hacerConsulta("select idFactura+1000 correlativo from EncabezadoFactura where idFactura =" + fac);
            while (resultado.next()) {
                correlativo = resultado.getInt("correlativo");
                if (correlativo == null || correlativo == 0) {
                    correlativo = 1;
                }
            }
        } catch (SQLException e) {
        }
        return correlativo;
    }

    public void agregar(EncabezadoFactura encabezadoFactura) {
        try {
            correlativoFac = correlativoFactura();
            psAgregar.setInt(1, correlativoFactura());
            psAgregar.setInt(2, encabezadoFactura.getCliente().getIdCliente());
            psAgregar.setString(3, encabezadoFactura.getUsuario().getUsuario());
            psAgregar.setString(4, encabezadoFactura.getDireccionFactura());
            psAgregar.setString(5, encabezadoFactura.getEstado());
            psAgregar.setString(6, encabezadoFactura.getEstado() == "FAC" ? String.valueOf(correlativoFactura() + 1000) : " ");
            psAgregar.setDouble(7, encabezadoFactura.getTotalFactura());
            //psAgregar.setDate(8, (Date) encabezadoFactura.getFecha());
            psAgregar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void agregar(DetalleFactura detalleFactura) {
        try {
            psAgregarDetalle.setInt(1, detalleFactura.getFactura().getIdFactura());
            psAgregarDetalle.setInt(2, detalleFactura.getCorrelativo());
            psAgregarDetalle.setInt(3, detalleFactura.getProducto().getIdProducto());
            psAgregarDetalle.setInt(4, detalleFactura.getCantidad());
            psAgregarDetalle.setDouble(5, detalleFactura.getPrecioUnitario());
            psAgregarDetalle.setDouble(6, detalleFactura.getPrecioTotal());
            psAgregarDetalle.execute();
            actualizaInventario(detalleFactura);
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

    public void anular(EncabezadoFactura encabezadoFactura) {
        try {
            psAnular.setInt(1, encabezadoFactura.getIdFactura());
            psAnular.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emitir(EncabezadoFactura encabezadoFactura) {
        try {
            psEmitirFactura.setInt(1, correlativoFiscal(encabezadoFactura.getIdFactura()));
            psEmitirFactura.setInt(2, encabezadoFactura.getIdFactura());
            psEmitirFactura.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizaInventario(DetalleFactura detalle) {
        try {
            psActualizaInventario.setInt(1, detalle.getCantidad());
            psActualizaInventario.setInt(2, detalle.getCantidad());
            psActualizaInventario.setInt(3, detalle.getProducto().getIdProducto());
            psActualizaInventario.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        listar();
    }
}
