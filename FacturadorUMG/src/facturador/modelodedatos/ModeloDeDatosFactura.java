package facturador.modelodedatos;

import facturador.beans.DetalleFactura;
import facturador.beans.EncabezadoFactura;
import facturador.manejadores.ManejadorDeClientes;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.manejadores.ManejadorDeHeaderFactura;

public class ModeloDeDatosFactura extends AbstractTableModel {

    private ArrayList<EncabezadoFactura> listaDeFacturas;
    private ArrayList<EncabezadoFactura> listaDeFacturas2;
    public String buscar;
    private String[] encabezados = {"CODIGO", "CLIENTE",  "ESTADO", "DOCUMENTO"};

    public ModeloDeDatosFactura() {
        listaDeFacturas = ManejadorDeHeaderFactura.getInstancia().listar();
        listaDeFacturas2 = ManejadorDeHeaderFactura.getInstancia().buscar(buscar);
    }

    public int getColumnCount() {
        return encabezados.length;
    }

    public int getRowCount() {
        return listaDeFacturas.size();
    }

    public String getColumnName(int columna) {
        return encabezados[columna];
    }

    public Object getValueAt(int fila, int columna) {
        String resultado = "";
        EncabezadoFactura factura = listaDeFacturas.get(fila);
        switch (columna) {
            case 0:
                resultado = String.valueOf(factura.getIdFactura());
                break;
            case 1:
                resultado = factura.getCliente().getNombre();
                break;
            case 2:
                resultado = factura.getEstado();
                break;
            case 3:
                resultado = factura.getDocumentoFiscal();
                break;
        }

        return resultado;
    }

    public void agregarFactura(EncabezadoFactura factura) {
        ManejadorDeHeaderFactura.getInstancia().agregar(factura);
        listaDeFacturas = ManejadorDeHeaderFactura.getInstancia().listar();
        fireTableDataChanged();
    }

    public void modificarFactura(int fila, EncabezadoFactura factura) {
        ManejadorDeHeaderFactura.getInstancia().modificar(factura);
        listaDeFacturas.set(fila, factura);
        fireTableDataChanged();
    }
    
    public void anularFactura(int fila, EncabezadoFactura factura) {
        ManejadorDeHeaderFactura.getInstancia().anular(factura);
        listaDeFacturas = ManejadorDeHeaderFactura.getInstancia().listar();
        fireTableDataChanged();
    }
    
    public void emitirFactura(int fila, EncabezadoFactura factura) {
        ManejadorDeHeaderFactura.getInstancia().emitir(factura);
        listaDeFacturas = ManejadorDeHeaderFactura.getInstancia().listar();
        fireTableDataChanged();
    }

    public ArrayList<EncabezadoFactura> obtenerListaFactura() {
        return listaDeFacturas;
    }

    public void actualizar() {
        listaDeFacturas = ManejadorDeHeaderFactura.getInstancia().listar();
    }
    
    public void agregarDetalle(ArrayList<DetalleFactura> lista) {
        int correlativo = 0;
        EncabezadoFactura factura = new EncabezadoFactura();
        factura.setIdFactura(ManejadorDeHeaderFactura.getInstancia().correlativoFac);
        for(DetalleFactura detalle: lista){
           correlativo++;
           detalle.setFactura(factura);
           detalle.setCorrelativo(correlativo);
           ManejadorDeHeaderFactura.getInstancia().agregar(detalle); 
        }
    }
    
    
}
