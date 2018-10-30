package facturador.modelodedatos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.beans.Producto;
import facturador.manejadores.ManejadorDeProductos;

public class ModeloDeDatosProducto extends AbstractTableModel {

    private ArrayList<Producto> listaDeProductos;
    private ArrayList<Producto> listaDeProductos2;
    public String buscar;
    private String[] encabezados = {"CODIGO", "NOMBRE", "PRECIO", "CANTIDAD-TOTAL", "CANTIDAD-DISPONIBLE", "DESCRIPCION", "CANTIDAD-VENDIDA"};

    public ModeloDeDatosProducto() {
        listaDeProductos = ManejadorDeProductos.getInstancia().listar();
        listaDeProductos2 = ManejadorDeProductos.getInstancia().buscar(buscar);
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
            case 5:
                resultado = String.valueOf(producto.getDescripcion());
                break;
            case 6:
                resultado = String.valueOf(producto.getCantidadVendida());
                break;
        }

        return resultado;
    }

    public void agregarProducto(Producto producto) {
        ManejadorDeProductos.getInstancia().agregar(producto);
        listaDeProductos = ManejadorDeProductos.getInstancia().listar();
        fireTableDataChanged();
    }

    public void eliminarProducto(int fila) {
        ManejadorDeProductos.getInstancia().eliminar(listaDeProductos.get(fila));
        listaDeProductos.remove(fila);
        fireTableRowsDeleted(fila, fila);

    }

    public void modificarProducto(int fila, Producto producto) {
        ManejadorDeProductos.getInstancia().modificar(producto);
        listaDeProductos.set(fila, producto);
        fireTableDataChanged();
    }

    public ArrayList<Producto> obtenerListaProductos() {
        return listaDeProductos;
    }

    public void actualizar() {
        listaDeProductos = ManejadorDeProductos.getInstancia().listar();
    }
}
