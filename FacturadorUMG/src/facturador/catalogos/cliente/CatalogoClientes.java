package facturador.catalogos.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import facturador.modelodedatos.ModeloDeDatosCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CatalogoClientes extends JInternalFrame implements ActionListener, KeyListener {

    //Cracion de objetos
    private static CatalogoClientes instancia;
    private JPanel panelBotones;
    private JPanel panelVentana;
    private JButton cmdNuevo;
    private JButton cmdEliminar;
    private JButton cmdModificar;
    private JButton cmdDetalles;

    public JTable tblClientes;
    private JScrollPane scrollTablaClientes;
    private ModeloDeDatosCliente modeloDeDatos;
    private JLabel espacio;
    private JTextField buscar;
    private TableRowSorter<TableModel> modeloOrdenado;
    private JLabel lblBuscar;
    private JLabel imagenBuscar;
    //Creacion del constructor

    public CatalogoClientes() {
        modeloDeDatos = new ModeloDeDatosCliente();
        modeloOrdenado = new TableRowSorter<TableModel>(modeloDeDatos);
        modeloDeDatos.buscar = "";
        panelBotones = new JPanel();

        cmdNuevo = new JButton("Nuevo", new ImageIcon(getClass().getResource("/Imagenes/agregar.png")));
        cmdNuevo.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdNuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdNuevo.setMaximumSize(new Dimension(100, 55));
        cmdNuevo.setToolTipText("Agregar Cliente");
        cmdNuevo.addActionListener(this);
        //Boton Eliminar
        cmdEliminar = new JButton("Eliminar", new ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        cmdEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdEliminar.setMaximumSize(new Dimension(100, 55));
        cmdEliminar.setToolTipText("Eliminar Cliente");
        cmdEliminar.addActionListener(this);
        //Boton Modificar
        cmdModificar = new JButton("Modificar", new ImageIcon(getClass().getResource("/Imagenes/editar.png")));
        cmdModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdModificar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdModificar.setMaximumSize(new Dimension(100, 55));
        cmdModificar.setToolTipText("Modificar Cliente");
        cmdModificar.addActionListener(this);

        cmdDetalles = new JButton("Ver Detalles", new ImageIcon(getClass().getResource("/Imagenes/verCliente.png")));
        cmdDetalles.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdDetalles.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdDetalles.setMaximumSize(new Dimension(100, 55));
        cmdDetalles.setToolTipText("Ver Cliente");
        cmdDetalles.addActionListener(this);

        buscar = new JTextField(125);
        buscar.setBounds(50, 35, 125, 20);
        buscar.addKeyListener(this);

        lblBuscar = new JLabel("Buscar Nombre De Cliente");
        lblBuscar.setBounds(50, 5, 200, 20);
        lblBuscar.setFont(new Font("Serif", Font.BOLD, 14));
        lblBuscar.setForeground(Color.darkGray);

        espacio = new JLabel("                                         ");
        espacio.setBounds(10, 5, 200, 20);
        espacio.setFont(new Font("Serif", Font.BOLD, 14));
        espacio.setForeground(Color.darkGray);

        imagenBuscar = new JLabel();
        imagenBuscar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/buscar.png")));
        imagenBuscar.setBounds(190, -20, 130, 130);
        //Agregar Botones al panel
        panelBotones.add(espacio);
        panelBotones.add(cmdNuevo);
        panelBotones.add(cmdModificar);
        panelBotones.add(cmdEliminar);
        panelBotones.add(cmdDetalles);

        //Parametros de configuracion de JTable
        //Parametros de vista de la ventana Usuario
        this.setTitle("Listado de Clientes");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Imagenes/iconoClientes.png")));
        this.setSize(800, 300);
        this.setLocation(100, 100);
        this.setClosable(true);
        this.setResizable(false);
        this.setMaximizable(false);
        this.add(buscar);
        this.add(lblBuscar);
        this.add(imagenBuscar);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Agrego el panel de botones al panel de la ventana
        this.getContentPane().add(panelBotones, BorderLayout.NORTH);
        //Agrego el panel de la tabla usuario
        this.getContentPane().add(getJPanelVentana(), BorderLayout.CENTER);
//        this.setVisible(true);

        //panelVentana.setBackground(Color.lightGray);
        // panelBotones.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdNuevo) {
            AgregarCliente agregarCliente = AgregarCliente.getInstancia();
            agregarCliente.setVisible(true);

        }
        if (objeto.getSource() == cmdEliminar) {
            if (tblClientes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Cliente en la Tabla");
            } else {
                int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el Cliente!", "Eliminar Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == 0) {
                    modeloDeDatos.eliminarCliente(tblClientes.getSelectedRow());
                }
            }
        }
        if (objeto.getSource() == cmdModificar) {
            if (tblClientes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Cliente en la Tabla");
            } else {
                ModificarCliente modificarCliente = ModificarCliente.getInstancia(modeloDeDatos.obtenerListaClientes().get(tblClientes.getSelectedRow()), tblClientes.getSelectedRow());
                modificarCliente.setVisible(true);
            }

        }

        if (objeto.getSource() == cmdDetalles) {
            if (tblClientes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Cliente Para Ver los Detalles");
            } else {
                CatalogoDetallesCliente.getInstancia().llenar();
                CatalogoDetallesCliente catalogoDetallesCliente = CatalogoDetallesCliente.getInstancia();
                catalogoDetallesCliente.setVisible(true);

            }

        }

    }

    //Agregar el ScroollPane al panel de ventana
    private JPanel getJPanelVentana() {
        if (panelVentana == null) {
            panelVentana = new JPanel();
            panelVentana.setLayout(null);
            panelVentana.add(getJScrollPane());
        }
        return panelVentana;
    }

    //Creacion del Scroll Pane
    public JScrollPane getJScrollPane() {
        if (scrollTablaClientes == null) {
            scrollTablaClientes = new JScrollPane();
            scrollTablaClientes.setBounds(new Rectangle(20, 27, 746, 150));
            scrollTablaClientes.setViewportView(getTablaClientes());
        }
        return scrollTablaClientes;
    }
    //Metodo para crear JTable e inicializar el modelo de datos

    public JTable getTablaClientes() {
        if (tblClientes == null) {
            tblClientes = new JTable();
            tblClientes.setModel(modeloDeDatos);
        }
        return tblClientes;
    }

    public static CatalogoClientes getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoClientes();
        }
        return instancia;
    }

    public ModeloDeDatosCliente getModelo() {
        return modeloDeDatos;
    }

    public void actualizarModelo() {
        modeloDeDatos = new ModeloDeDatosCliente();
    }

    public void keyTyped(KeyEvent e) {
        //JOptionPane.showMessageDialog(null,);
        /*this.getModelo().Buscar(buscar.getText());
        this.modeloDeDatos = new ModeloDeDatosCliente();
        this.updateUI();*/
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        modeloOrdenado.setRowFilter(RowFilter.regexFilter(buscar.getText(), 1));
        tblClientes.setRowSorter(modeloOrdenado);
    }
}
