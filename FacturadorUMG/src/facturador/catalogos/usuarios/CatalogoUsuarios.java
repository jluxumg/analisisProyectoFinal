package facturador.catalogos.usuarios;

import facturador.catalogos.cliente.*;
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
import facturador.modelodedatos.ModeloDeDatosUsuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CatalogoUsuarios extends JInternalFrame implements ActionListener, KeyListener {

    //Cracion de objetos
    private static CatalogoUsuarios instancia;
    private JPanel panelBotones;
    private JPanel panelVentana;
    private JButton cmdNuevo;
    private JButton cmdEliminar;
    private JButton cmdModificar;
    private JButton cmdDetalles;

    public JTable tblUsuarios;
    private JScrollPane scrollTablaUsuarios;
    private ModeloDeDatosUsuario modeloDeDatos;
    private JLabel espacio;
    private JTextField buscar;
    private TableRowSorter<TableModel> modeloOrdenado;
    private JLabel lblBuscar;
    private JLabel imagenBuscar;
    //Creacion del constructor

    public CatalogoUsuarios() {
        modeloDeDatos = new ModeloDeDatosUsuario();
        modeloOrdenado = new TableRowSorter<TableModel>(modeloDeDatos);
        modeloDeDatos.buscar = "";
        panelBotones = new JPanel();

        cmdNuevo = new JButton("Nuevo", new ImageIcon(getClass().getResource("/Imagenes/agregar.png")));
        cmdNuevo.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdNuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdNuevo.setMaximumSize(new Dimension(100, 55));
        cmdNuevo.setToolTipText("Agregar Usuario");
        cmdNuevo.addActionListener(this);
        //Boton Eliminar
        cmdEliminar = new JButton("Eliminar", new ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        cmdEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdEliminar.setMaximumSize(new Dimension(100, 55));
        cmdEliminar.setToolTipText("Eliminar Usuario");
        cmdEliminar.addActionListener(this);
        //Boton Modificar
        cmdModificar = new JButton("Modificar", new ImageIcon(getClass().getResource("/Imagenes/editar.png")));
        cmdModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdModificar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdModificar.setMaximumSize(new Dimension(100, 55));
        cmdModificar.setToolTipText("Modificar Usuario");
        cmdModificar.addActionListener(this);

        cmdDetalles = new JButton("Ver Detalles", new ImageIcon(getClass().getResource("/Imagenes/verCliente.png")));
        cmdDetalles.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdDetalles.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdDetalles.setMaximumSize(new Dimension(100, 55));
        cmdDetalles.setToolTipText("Ver Usuario");
        cmdDetalles.addActionListener(this);

        buscar = new JTextField(125);
        buscar.setBounds(50, 35, 125, 20);
        buscar.addKeyListener(this);

        lblBuscar = new JLabel("Buscar Nombre De Usuario");
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

        //Parametros de configuracion de JTable
        //Parametros de vista de la ventana Usuario
        this.setTitle("Listado de Usuarios");
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
            AgregarUsuario au = AgregarUsuario.getInstancia();
            au.setVisible(true);
        }
        if (objeto.getSource() == cmdEliminar) {
            if (tblUsuarios.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Usuario en la Tabla");
            } else {
                int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el Usuario!", "Eliminar Usuario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == 0) {
                    modeloDeDatos.eliminarUsuario(tblUsuarios.getSelectedRow());
                }
            }
        }
        if (objeto.getSource() == cmdModificar) {
            if (tblUsuarios.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Usuario en la Tabla");
            } else {
                ModificarUsuario mu = ModificarUsuario.getInstancia(modeloDeDatos.obtenerListaUsuarios().get(tblUsuarios.getSelectedRow()), tblUsuarios.getSelectedRow());
                mu.setVisible(true);

            }

        }

        if (objeto.getSource() == cmdDetalles) {
            if (tblUsuarios.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Usuario Para Ver los Detalles");
            } else {
//                CatalogoDetallesCliente.getInstancia().llenar();
//                CatalogoDetallesCliente catalogoDetallesCliente = CatalogoDetallesCliente.getInstancia();
//                catalogoDetallesCliente.setVisible(true);

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
        if (scrollTablaUsuarios == null) {
            scrollTablaUsuarios = new JScrollPane();
            scrollTablaUsuarios.setBounds(new Rectangle(20, 27, 746, 150));
            scrollTablaUsuarios.setViewportView(getTablaClientes());
        }
        return scrollTablaUsuarios;
    }
    //Metodo para crear JTable e inicializar el modelo de datos

    public JTable getTablaClientes() {
        if (tblUsuarios == null) {
            tblUsuarios = new JTable();
            tblUsuarios.setModel(modeloDeDatos);
        }
        return tblUsuarios;
    }

    public static CatalogoUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoUsuarios();
        }
        return instancia;
    }

    public ModeloDeDatosUsuario getModelo() {
        return modeloDeDatos;
    }

    public void actualizarModelo() {
        modeloDeDatos = new ModeloDeDatosUsuario();
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
        tblUsuarios.setRowSorter(modeloOrdenado);
    }
}
