package facturador.modelodedatos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.beans.Clientes;
import facturador.beans.Producto;
import facturador.manejadores.ManejadorDeCliente;

public class ModeloDeDatosProducto extends AbstractTableModel {

    private ArrayList<Producto> listaDeProductos;
    private ArrayList<Producto> listaDeProductos2;
    public String buscar;
    private String[] encabezados = {"CODIGO", "NOMBRE", "PRECIO", "CANTIDAD-TOTAL", "CANTIDAD-DISPONIBLE"};

    public ModeloDeDatosProducto() {
        listaDeProductos = ManejadorDeProducto.getInstancia().listaProductos();
        listaDeProductos2 = ManejadorDeProducto.getInstancia().BuscarProducto(buscar);

    }

    public int getColumnCount() {
        return encabezados.length;
    }

    public int getRowCount() {
        return listaDeProductos.size();
    }

    public String getColumnName(int columna) {
        return encabezados[columna];
    }

    public Object getValueAt(int fila, int columna) {
        String resultado = "";
        Producto producto = listaDeProductos.get(fila);
        switch (columna) {
            case 0:
                resultado = String.valueOf(producto.getIdProducto());
                break;
            case 1:
                resultado = producto.getNombre();
                break;
            case 2:
                resultado = String.valueOf(producto.getPrecio());
                break;
            case 3:
                resultado = String.valueOf(producto.getCantidadTotal());
                break;
            case 4:
                resultado = String.valueOf(producto.getCantidadDisponible());
                break;
        }

        return resultado;
    }

    public void agregarProducto(Producto producto) {
        ManejadorDeProducto.getInstancia().agregarProducto(producto);
        listaDeProductos = ManejadorDeProducto.getInstancia().listaProductos();
        fireTableDataChanged();
    }

    public void eliminarProducto(int fila) {
        ManejadorDeProducto.getInstancia().eliminarProducto(listaDeProductos.get(fila));
        listaDeProductos.remove(fila);
        fireTableRowsDeleted(fila, fila);

    }

    public void modificarProducto(int fila, Producto producto) {
        ManejadorDeProducto.getInstancia().modificarProducto(producto);
        listaDeProductos.set(fila, producto);
        fireTableDataChanged();
    }

    public ArrayList<Clientes> obtenerListaProductos() {
        return listaDeProductos;
    }

    public void actualizar() {
        listaDeProductos = ManejadorDeProducto.getInstancia().listaProductos();
    }
}
