package facturador.modelodedatos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.beans.Recordatorios;
import facturador.manejadores.ManejadorRecordatorios;

public class ModeloDeDatosRecordatorios extends AbstractTableModel {
    private ArrayList<Recordatorios> listaDeClientes;

    private String[] encabezados = {"CODIGO CLIENTE","FECHA","OBSERVACIONES","VENDEDOR"};
    public ModeloDeDatosRecordatorios(){
        listaDeClientes = ManejadorRecordatorios.getInstancia().listaRecordatorios();

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
        Recordatorios recordatorios = listaDeClientes.get(fila);
        switch(columna){
            case 0:
                resultado = String.valueOf(recordatorios.getIdCliente());
                break;
            case 1:
                resultado = recordatorios.getFecha();
                break;
            case 2:
                resultado = recordatorios.getObservaciones();
                break;
            case 3:
                resultado = recordatorios.getVendedor();
                break;
            
        }

        return resultado;
    }
    
    public ArrayList<Recordatorios> obtenerListaRecordatorios(){
        return listaDeClientes;
    }
    public void actualizar(){
        listaDeClientes = ManejadorRecordatorios.getInstancia().listaRecordatorios();
    }

}
