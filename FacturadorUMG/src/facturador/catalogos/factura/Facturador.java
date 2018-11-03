package facturador.catalogos.factura;

import com.toedter.calendar.JDateChooser;
import facturador.beans.Clientes;
import facturador.beans.DetalleFactura;
import facturador.beans.EncabezadoFactura;
import facturador.beans.Usuario;
import facturador.modelodedatos.ModeloDeDatosCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import facturador.manejadores.ManejadorDeClientes;
import facturador.modelodedatos.ModeloDeDatosDetalleFacturaTabla;
import facturador.ventanas.VentanaLogin;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Facturador extends JDialog implements ActionListener {

    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblCliente;
    private JLabel lblUsuario;
    private JLabel lblDireccion;
    private JLabel lblEstado;
    private JLabel lblDocumentoFiscal;
    private JLabel lblTotalFactura;
    private JLabel lblFecha;

    public JComboBox cmbCliente;
    public JComboBox cmbEstado;
    public JTextField txtUsuario;
    public JTextField txtDireccion;
    public JComboBox txtEstado;
    public JTextField txtDocumentoFiscal;
    public JTextField txtTotal;
    public JDateChooser txtFecha;
    private ModeloDeDatosCliente modeloDeDatos;

    private JButton cmdGuardar;
    private JButton cmdCancelar;

    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;

    private static Facturador instancia;
    private ArrayList<DetalleFactura> listDetalle;
    ModeloDeDatosDetalleFacturaTabla modeloDetalle;
    private TableRowSorter<TableModel> modeloOrdenado;
    
    public JTable tblDetalle;
    private JScrollPane scrollTabla;

    //Constructor
    public Facturador() {
        listDetalle = new ArrayList<DetalleFactura>();
        modeloDeDatos = new ModeloDeDatosCliente();
        
        modeloDetalle = new ModeloDeDatosDetalleFacturaTabla();
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/clientes128.png")));
        imagen.setBounds(15, 10, 130, 130);

        lblCliente = new JLabel("Cliente");
        lblCliente.setFont(new Font("Serif", Font.BOLD, 16));
        lblCliente.setForeground(Color.darkGray);
        lblCliente.setBounds(175, 35, 100, 20);

        cmbCliente = new JComboBox();
        cmbCliente.setBounds(250, 35, 700, 20);
        cmbCliente.addItem("Seleccione un cliente");

        for (Clientes cliente : modeloDeDatos.obtenerListaClientes()) {
            cmbCliente.addItem(cliente.getIdCliente() + " - " + cliente.getNombre() + " " + cliente.getApellido());
        }

        lblDireccion = new JLabel("Dirección");
        lblDireccion.setFont(new Font("Serif", Font.BOLD, 16));
        lblDireccion.setForeground(Color.darkGray);
        lblDireccion.setBounds(175, 70, 250, 20);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(250, 70, 700, 20);

        lblEstado = new JLabel("Estado");
        lblEstado.setFont(new Font("Serif", Font.BOLD, 16));
        lblEstado.setForeground(Color.darkGray);
        lblEstado.setBounds(175, 105, 250, 20);

        cmbEstado = new JComboBox();
        cmbEstado.setBounds(250, 105, 300, 20);
        cmbEstado.addItem("PRF-Pro-forma");
        cmbEstado.addItem("FAC-Factura");

        lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Serif", Font.BOLD, 16));
        lblFecha.setForeground(Color.darkGray);
        lblFecha.setBounds(175, 140, 250, 20);

        String fecha = "05/12/2008";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Date fechaDate = formato.parse(new Date());

        txtFecha = new JDateChooser();
        txtFecha.setBounds(250, 140, 150, 20);
        txtFecha.setDate(new Date());
        txtFecha.setEnabled(false);

        btnAgregar = new JButton("Agregar", new ImageIcon(getClass().getResource("/Imagenes/mas.png")));
        btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAgregar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAgregar.setToolTipText("Agregar");
        btnAgregar.setBounds(15, 175, 110, 60);
        btnAgregar.addActionListener(this);

        btnEliminar = new JButton("Eliminar", new ImageIcon(getClass().getResource("/Imagenes/menos.png")));
        btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setBounds(135, 175, 110, 60);
        btnEliminar.addActionListener(this);

        btnModificar = new JButton("Modificar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnModificar.setToolTipText("Modificar");
        btnModificar.setBounds(255, 175, 110, 60);
        btnModificar.addActionListener(this);

        cmdGuardar = new JButton("Guardar", new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Cliente");
        cmdGuardar.setBounds(15, 560, 110, 60);
        cmdGuardar.addActionListener(this);

        cmdCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(135, 560, 110, 60);
        cmdCancelar.addActionListener(this);
        
        panelVentana.add(imagen);

        panelVentana.add(lblCliente);
        panelVentana.add(lblDireccion);
        panelVentana.add(lblEstado);
        panelVentana.add(lblFecha);
        panelVentana.add(cmbCliente);
        panelVentana.add(txtDireccion);
        panelVentana.add(cmbEstado);
        panelVentana.add(txtFecha);
        panelVentana.add(btnAgregar);
        panelVentana.add(btnEliminar);
        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);
        panelVentana.add(getJScrollPane(), BorderLayout.CENTER);

        this.add(panelVentana);
        this.setTitle("Nueva Factura");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000, 675);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            int prueba = 0;
            if (txtDireccion.getText().length() == 0
                    || cmbCliente.getSelectedItem().toString().length() == 0
                    || cmbCliente.getSelectedItem().toString().equalsIgnoreCase("Seleccione un cliente")
                    || cmbEstado.getSelectedItem().toString().length() == 0
                    || txtFecha.getDate() == null
                    || Facturador.getInstancia().modeloDetalle.getListaDetalle().size() <= 0) {
                JOptionPane.showMessageDialog(null, "Hay Datos En Blanco, por favor revisar", "ADVERTENCIA", 1);
            } else {
                prueba = 1;
            }
            if (prueba == 1) {

                Clientes cliente = new Clientes();
                String clienteString = cmbCliente.getSelectedItem().toString().substring(0, cmbCliente.getSelectedItem().toString().indexOf("-"));
                cliente = ManejadorDeClientes.getInstancia().BuscarClienteID(Integer.parseInt(clienteString.trim()));

                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                EncabezadoFactura ef = new EncabezadoFactura();
                Double totalFactura = new Double("0");
                for(DetalleFactura detalle : Facturador.getInstancia().modeloDetalle.getListaDetalle()){
                    totalFactura += detalle.getPrecioTotal();
                }
                ef.setIdFactura(0);
                ef.setCliente(cliente);
                ef.setDireccionFactura(txtDireccion.getText().trim());
                ef.setDocumentoFiscal(" ");
                ef.setEstado(cmbEstado.getSelectedItem().toString().substring(0, cmbEstado.getSelectedItem().toString().indexOf("-")));
                System.out.println("Usuario " + VentanaLogin.getInstancia().userSession.getUsuario());
                ef.setUsuario(VentanaLogin.getInstancia().userSession);
                ef.setTotalFactura(totalFactura);
                CatalogoFacturas.getInstancia().getModelo().agregarFactura(ef);
                CatalogoFacturas.getInstancia().getModelo().agregarDetalle(Facturador.getInstancia().modeloDetalle.getListaDetalle());
                instancia.dispose();
                instancia.dispose();
                txtDireccion.setText("");
                cmbEstado.setSelectedItem(null);
                cmbCliente.setSelectedItem(null);
                Facturador.getInstancia().modeloDetalle.setListaDetalle(null);
            }
        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            txtDireccion.setText("");
            cmbEstado.setSelectedItem(null);
            cmbCliente.setSelectedItem(null);
            this.setVisible(false);
        }
        
        if (objeto.getSource() == btnAgregar) {
            AgregarDetalle agregarDetalle = AgregarDetalle.getInstancia();
            agregarDetalle.setVisible(true);
        }
        if (objeto.getSource() == btnEliminar) {
            if (tblDetalle.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una registro de la tabla");
            } else {
                Facturador.getInstancia().modeloDetalle.eliminarProducto(tblDetalle.getSelectedRow());
            }
        }
    }
    
    

    public static Facturador getInstancia() {
        if (instancia == null) {
            instancia = new Facturador();
        }
        return instancia;
    }
    
    public void agregarLista(DetalleFactura detalle){
        listDetalle.add(detalle);
    }

    public ArrayList<DetalleFactura> getListDetalle() {
        
        
        return listDetalle;
    }

    public void setListDetalle(ArrayList<DetalleFactura> listDetalle) {
        this.listDetalle = listDetalle;
    }
    
    //Creacion del Scroll Pane
    public JScrollPane getJScrollPane() {
        if (scrollTabla == null) {
            scrollTabla = new JScrollPane();
            scrollTabla.setBounds(new Rectangle(15, 245, 960, 300));
            scrollTabla.setViewportView(getTablaClientes());
        }
        return scrollTabla;
    }
    //Metodo para crear JTable e inicializar el modelo de datos

    public JTable getTablaClientes() {
        if (tblDetalle == null) {
            tblDetalle = new JTable();
            tblDetalle.setModel(modeloDetalle);
        }
        return tblDetalle;
    }

}
