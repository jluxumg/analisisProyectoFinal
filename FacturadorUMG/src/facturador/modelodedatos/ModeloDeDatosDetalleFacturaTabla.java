package facturador.modelodedatos;

import facturador.beans.DetalleFactura;
import facturador.beans.EncabezadoFactura;
import facturador.catalogos.factura.Facturador;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.manejadores.ManejadorDeHeaderFactura;
import java.text.DecimalFormat;

public class ModeloDeDatosDetalleFacturaTabla extends AbstractTableModel {

    private ArrayList<DetalleFactura> listaDetalle;
    public String buscar;
    private String[] encabezados = { "PRODUCTO",  "CANTIDAD", "PRECIO UNITARIO","PRECIO TOTAL"};
    public DecimalFormat formateador = new DecimalFormat("0.00");

    public ModeloDeDatosDetalleFacturaTabla() {
        listaDetalle = new ArrayList<DetalleFactura>();
    }

    public int getColumnCount() {
        return encabezados.length;
    }

    public int getRowCount() {
        return listaDetalle.size();
    }

    public String getColumnName(int columna) {
        return encabezados[columna];
    }

    public Object getValueAt(int fila, int columna) {
        String resultado = "";
        DetalleFactura datelle = listaDetalle.get(fila);
        switch (columna) {
            case 0:
                resultado = datelle.getProducto().getNombre();
                break;
            case 1:
                resultado = datelle.getCantidad().toString();
                break;
            case 2:
                resultado = formateador.format(datelle.getPrecioUnitario());
                break;
            case 3:
                resultado = formateador.format(datelle.getPrecioTotal());
                break;
        }
        return resultado;
    }

    public void agregar(DetalleFactura detalle) {
        listaDetalle.add(detalle);
        fireTableDataChanged();
    }
    
    public void eliminarProducto(int fila) {
        listaDetalle.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }

    public ArrayList<DetalleFactura> obtenerListaFactura() {
        return listaDetalle;
    }

    public void actualizar() {
        listaDetalle = Facturador.getInstancia().getListDetalle();
    }

    public ArrayList<DetalleFactura> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(ArrayList<DetalleFactura> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    
}
