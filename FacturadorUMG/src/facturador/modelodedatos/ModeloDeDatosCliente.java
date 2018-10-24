package facturador.modelodedatos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.beans.Clientes;
import facturador.manejadores.ManejadorDeClientes;

public class ModeloDeDatosCliente extends AbstractTableModel {

    private ArrayList<Clientes> listaDeClientes;
    private ArrayList<Clientes> listaDeClientes2;
    public String buscar;
    private String[] encabezados = {"CODIGO", "NOMBRE", "APELLIDO", "TIPO-DOCUMENTO", "DOCUMENTO", "DIRECCION", "CORREO", "TELEFONO"};

    public ModeloDeDatosCliente() {
        listaDeClientes = ManejadorDeClientes.getInstancia().listaClientes();
        listaDeClientes2 = ManejadorDeClientes.getInstancia().BuscarCliente(buscar);

    }

    public int getColumnCount() {
        return encabezados.length;
    }

    public int getRowCount() {
        return listaDeClientes.size();
    }

    public String getColumnName(int columna) {
        return encabezados[columna];
    }

    public Object getValueAt(int fila, int columna) {
        String resultado = "";
        Clientes clientes = listaDeClientes.get(fila);
        switch (columna) {
            case 0:
                resultado = String.valueOf(clientes.getIdCliente());
                break;
            case 1:
                resultado = clientes.getNombre();
                break;
            case 2:
                resultado = clientes.getApellido();
                break;
            case 3:
                resultado = clientes.getTipoDocumento();
                break;
            case 4:
                resultado = clientes.getDocumento();
                break;
            case 5:
                resultado = clientes.getDireccion();
                break;
            case 6:
                resultado = clientes.getCorreo();
                break;
            case 7:
                resultado = clientes.getTelefono();
                break;
        }

        return resultado;
    }

    public void agregarCliente(Clientes clientes) {
        ManejadorDeClientes.getInstancia().agregarCliente(clientes);
        listaDeClientes = ManejadorDeClientes.getInstancia().listaClientes();
        fireTableDataChanged();
    }

    public void eliminarCliente(int fila) {
        ManejadorDeClientes.getInstancia().eliminarCliente(listaDeClientes.get(fila));
        listaDeClientes.remove(fila);
        fireTableRowsDeleted(fila, fila);

    }

    public void modificarCliente(int fila, Clientes clientes) {
        ManejadorDeClientes.getInstancia().modificarCliente(clientes);
        listaDeClientes.set(fila, clientes);
        fireTableDataChanged();
    }

    public ArrayList<Clientes> obtenerListaClientes() {
        return listaDeClientes;
    }

    public void actualizar() {
        listaDeClientes = ManejadorDeClientes.getInstancia().listaClientes();
    }
}
