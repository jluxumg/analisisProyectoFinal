package facturador.modelodedatos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.beans.Clientes;
import facturador.manejadores.ManejadorDeClientes;

public class ModeloDeDatosCliente extends AbstractTableModel {
    private ArrayList<Clientes> listaDeClientes;
    private ArrayList<Clientes> listaDeClientes2;
    public String buscar;
    private String[] encabezados = {"CODIGO","CLIENTE","COMPAÑIA","GIRO","DIRECCION","CORREO","TELEFONO1","TELEFONO2","FAX","CELULAR","TIPO-CLIENTE","VENDEDOR"};
    public ModeloDeDatosCliente(){
        listaDeClientes = ManejadorDeClientes.getInstancia().listaClientes();
        listaDeClientes2 = ManejadorDeClientes.getInstancia().BuscarCliente(buscar);
        
    }
    public int getColumnCount(){
        return encabezados.length;
    }
    public int getRowCount(){
        return listaDeClientes.size();
    }
    public String getColumnName(int columna){
        return encabezados[columna];
    }
    public Object getValueAt(int fila, int columna){
        String resultado = "";
        Clientes clientes = listaDeClientes.get(fila);
        switch(columna){
            case 0:
                resultado = String.valueOf(clientes.getIdCliente());
                break;
            case 1:
                resultado = clientes.getCliente();
                break;
            case 2:
                resultado = clientes.getCompañia();
                break;
            case 3:
                resultado = clientes.getGiro();
                break;
            case 4:
                resultado = clientes.getDireccionCompleta();
                break;
            case 5:
                resultado = clientes.getCorreo();
                break;
            case 6:
                resultado = clientes.getTelefono1();
                break;
            case 7:
                resultado = clientes.getTelefono2();
                break;
            case 8:
                resultado = clientes.getFax();
                break;
            case 9:
                resultado = clientes.getCelular();
                break;
            case 10:
                resultado = clientes.getTipoCliente();
                break;
            case 11:
                resultado = clientes.getVendedor();
                break;
        }

        return resultado;
    }
    public void agregarCliente(Clientes clientes){
        ManejadorDeClientes.getInstancia().agregarCliente(clientes);
        listaDeClientes = ManejadorDeClientes.getInstancia().listaClientes();
        fireTableDataChanged();
    }
    public void eliminarCliente(int fila){
        ManejadorDeClientes.getInstancia().eliminarCliente(listaDeClientes.get(fila));
        listaDeClientes.remove(fila);
        fireTableRowsDeleted(fila,fila);

    }
    public void modificarCliente(int fila, Clientes clientes){
        ManejadorDeClientes.getInstancia().modificarCliente(clientes);
        listaDeClientes.set(fila, clientes);
        fireTableDataChanged();
    }
    public ArrayList<Clientes> obtenerListaClientes(){
        return listaDeClientes;
    }
    public void actualizar(){
        listaDeClientes = ManejadorDeClientes.getInstancia().listaClientes();
    }
}
