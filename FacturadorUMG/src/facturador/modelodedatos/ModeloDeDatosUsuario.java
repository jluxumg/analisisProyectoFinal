package facturador.modelodedatos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import facturador.beans.Usuario;
import facturador.manejadores.ManejadorDeUsuarios;

public class ModeloDeDatosUsuario extends AbstractTableModel {

    private ArrayList<Usuario> listaDeUsuarios;
    private ArrayList<Usuario> listaDeUsuarios2;

    public String buscar;
    private String[] encabezados = {"USUARIO", "NOMBRE", "APELLIDO", "ESTADO"};

    public ModeloDeDatosUsuario() {
        listaDeUsuarios = ManejadorDeUsuarios.getInstancia().listar();
        listaDeUsuarios2 = ManejadorDeUsuarios.getInstancia().buscar(buscar);

    }

    public int getColumnCount() {
        return encabezados.length;
    }

    public int getRowCount() {
        return listaDeUsuarios.size();
    }

    public String getColumnName(int columna) {
        return encabezados[columna];
    }

    public Object getValueAt(int fila, int columna) {
        String resultado = "";
        Usuario usuario = listaDeUsuarios.get(fila);
        switch (columna) {
            case 0:
                resultado = usuario.getUsuario();
                break;
            case 1:
                resultado = usuario.getNombre();
                break;
            case 2:
                resultado = usuario.getApellido();
                break;
            case 3:
                resultado = usuario.getEstado();
                break;

        }

        return resultado;
    }

    public void agregarUsuario(Usuario usuario) {
        ManejadorDeUsuarios.getInstancia().agregar(usuario);
        listaDeUsuarios = ManejadorDeUsuarios.getInstancia().listar();
        fireTableDataChanged();
    }

    public void eliminarUsuario(int fila) {
        ManejadorDeUsuarios.getInstancia().eliminar(listaDeUsuarios.get(fila));
        listaDeUsuarios.remove(fila);
        fireTableRowsDeleted(fila, fila);

    }

    public void modificarUsuario(int fila, Usuario usuario) {
        ManejadorDeUsuarios.getInstancia().modificar(usuario);
        listaDeUsuarios.set(fila, usuario);
        fireTableDataChanged();
    }

    public ArrayList<Usuario> obtenerListaUsuarios() {
        return listaDeUsuarios;
    }

    public void actualizar() {
        listaDeUsuarios = ManejadorDeUsuarios.getInstancia().listar();
    }
}
