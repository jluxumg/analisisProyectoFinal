package facturador.catalogos.factura;

import facturador.beans.EncabezadoFactura;
import facturador.catalogos.cliente.*;
import facturador.manejadores.ManejadorDeHeaderFactura;
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
import facturador.modelodedatos.ModeloDeDatosFactura;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import reporte.ReporteNuevo;

public class CatalogoFacturas extends JInternalFrame implements ActionListener, KeyListener {

    //Cracion de objetos
    private static CatalogoFacturas instancia;
    private JPanel panelBotones;
    private JPanel panelVentana;
    private JButton cmdNuevo;
    private JButton cmbAnular;
    private JButton cmdModificar;
    private JButton cmbEmitir;

    public JTable tblFacturas;
    private JScrollPane scrollTablaFacturas;
    private ModeloDeDatosFactura modeloDeDatos;
    private JLabel espacio;
    private JTextField buscar;
    private TableRowSorter<TableModel> modeloOrdenado;
    private JLabel lblBuscar;
    private JLabel imagenBuscar;
    //Creacion del constructor

    public CatalogoFacturas() {
        modeloDeDatos = new ModeloDeDatosFactura();
        modeloOrdenado = new TableRowSorter<TableModel>(modeloDeDatos);
        modeloDeDatos.buscar = "";
        panelBotones = new JPanel();

        cmdNuevo = new JButton("Nuevo", new ImageIcon(getClass().getResource("/Imagenes/agregar.png")));
        cmdNuevo.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdNuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdNuevo.setMaximumSize(new Dimension(100, 55));
        cmdNuevo.setToolTipText("Agregar Factura");
        cmdNuevo.addActionListener(this);
        //Boton Eliminar
        cmbAnular = new JButton("Anular", new ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        cmbAnular.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmbAnular.setHorizontalTextPosition(SwingConstants.CENTER);
        cmbAnular.setMaximumSize(new Dimension(100, 55));
        cmbAnular.setToolTipText("Anular Factura");
        cmbAnular.addActionListener(this);
        //Boton Modificar
        cmdModificar = new JButton("Modificar", new ImageIcon(getClass().getResource("/Imagenes/editar.png")));
        cmdModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdModificar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdModificar.setMaximumSize(new Dimension(100, 55));
        cmdModificar.setToolTipText("Modificar Factura");
        cmdModificar.addActionListener(this);
        //Boton Emitir Factura
        cmbEmitir = new JButton("Emitir", new ImageIcon(getClass().getResource("/Imagenes/solicitud.png")));
        cmbEmitir.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmbEmitir.setHorizontalTextPosition(SwingConstants.CENTER);
        cmbEmitir.setMaximumSize(new Dimension(100, 55));
        cmbEmitir.setToolTipText("Emitir Factura");
        cmbEmitir.addActionListener(this);

        buscar = new JTextField(125);
        buscar.setBounds(50, 35, 125, 20);
        buscar.addKeyListener(this);

        lblBuscar = new JLabel("Buscar en Número de Factura");
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
        panelBotones.add(cmbAnular);
        panelBotones.add(cmbEmitir);

        //Parametros de configuracion de JTable
        //Parametros de vista de la ventana Usuario
        this.setTitle("Listado de Facturas");
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

        getTablaFacturas().addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 1) {
                    System.out.println("Se ha hecho un click");
                }
                if (e.getClickCount() == 2) {
                    reporte();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdNuevo) {
            Facturador agregarFactura = Facturador.getInstancia();
            agregarFactura.setVisible(true);
        }
        if (objeto.getSource() == cmbAnular) {
            if (tblFacturas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Factura en la Tabla");
            } else {
                int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea anular la factura", "Anular factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == 0) {
                    modeloDeDatos.anularFactura(tblFacturas.getSelectedRow(), modeloDeDatos.obtenerListaFactura().get(tblFacturas.getSelectedRow()));
                }
            }
        }
        if (objeto.getSource() == cmdModificar) {
            if (tblFacturas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una Factura de la tabla");
            } else {
                //ModificarCliente modificarCliente = ModificarCliente.getInstancia(modeloDeDatos.obtenerListaFactura().get(tblFacturas.getSelectedRow()), tblFacturas.getSelectedRow());
                //modificarCliente.setVisible(true);
            }

        }
        if (objeto.getSource() == cmbEmitir) {
            if (tblFacturas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una Factura de la tabla");
            } else {
                EncabezadoFactura factu = ManejadorDeHeaderFactura.getInstancia().buscar(modeloDeDatos.obtenerListaFactura().get(tblFacturas.getSelectedRow()).getIdFactura());
                if (factu.getEstado().equalsIgnoreCase("PRF")) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea emitir la factura?", "Emitir factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == 0) {
                        modeloDeDatos.emitirFactura(tblFacturas.getSelectedRow(), modeloDeDatos.obtenerListaFactura().get(tblFacturas.getSelectedRow()));
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Factura ya fue emitida o se encuentra anulada");
                }
            }

        }
    }

    public void tableMenu(final JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = tablePopup(table, rowindex);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public JPopupMenu tablePopup(final JTable table, final int row) {
        JPopupMenu popup = new JPopupMenu("Popup");

        JMenuItem refreshItem = new JMenuItem("Select");
        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nombre: " + table.getValueAt(row, 0));
            }
        });
        popup.add(refreshItem);

        return popup;
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
        if (scrollTablaFacturas == null) {
            scrollTablaFacturas = new JScrollPane();
            scrollTablaFacturas.setBounds(new Rectangle(20, 27, 746, 150));
            scrollTablaFacturas.setViewportView(getTablaFacturas());
        }
        return scrollTablaFacturas;
    }
    //Metodo para crear JTable e inicializar el modelo de datos

    public JTable getTablaFacturas() {
        if (tblFacturas == null) {
            tblFacturas = new JTable();
            tblFacturas.setModel(modeloDeDatos);
        }
        return tblFacturas;
    }

    public static CatalogoFacturas getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoFacturas();
        }
        return instancia;
    }

    public ModeloDeDatosFactura getModelo() {
        return modeloDeDatos;
    }

    public void actualizarModelo() {
        modeloDeDatos = new ModeloDeDatosFactura();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        modeloOrdenado.setRowFilter(RowFilter.regexFilter(buscar.getText(), 0));
        tblFacturas.setRowSorter(modeloOrdenado);
    }

    public void reporte() {
        Map<String, String> parm = new HashMap<String, String>();
        File imagen = new File("src/reporte/jasperImages/");
        File rutaSubReporte = new File("src/reporte/subreportnormal/");
        parm.put("p_rutaimg", imagen.getAbsolutePath());
        parm.put("SUBREPORT_DIR", rutaSubReporte.getAbsolutePath() + "/");
        parm.put("pSchemaDB", "umgAnalisis");
        parm.put("pCond1", "1");
        ReporteNuevo reporte = new ReporteNuevo();
        File file = new File("src/reporte/subreportnormal/Factura.jasper");
        reporte.JReportViewParametros(file.getAbsolutePath(), parm);
    }
}
