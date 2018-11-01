package facturador.catalogos;

import com.toedter.calendar.JDateChooser;
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
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;

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

    private JButton cmdGuardar;
    private JButton cmdCancelar;

    private static Facturador instancia;

    //Constructor
    public Facturador() {
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
        cmbCliente.addItem("DPI");
        
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
        
        String fecha ="05/12/2008";
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      //Date fechaDate = formato.parse(new Date());
        
        
        txtFecha = new JDateChooser();
        txtFecha.setBounds(250,140, 150, 20);
        txtFecha.setDate(new Date());
        txtFecha.setEnabled(false);
        
        /*txt = new JTextField();
        txtNombre.setBounds(175, 85, 200, 20);

        lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Serif", Font.BOLD, 16));
        lblApellido.setForeground(Color.darkGray);
        lblApellido.setBounds(400, 60, 250, 20);

        txtApellido = new JTextField();
        txtApellido.setBounds(400, 85, 150, 20);

        lblTipoDocumento = new JLabel("Tipo De Documento");
        lblTipoDocumento.setFont(new Font("Serif", Font.BOLD, 16));
        lblTipoDocumento.setForeground(Color.darkGray);
        lblTipoDocumento.setBounds(175, 10, 250, 20);

        cmbTipoDoc = new JComboBox();
        cmbTipoDoc.setBounds(175, 35, 150, 20);
        cmbTipoDoc.addItem("DPI");
        cmbTipoDoc.addItem("Licencia");
        cmbTipoDoc.addItem("Pasaporte");

        lblDocumento = new JLabel("Documento");
        lblDocumento.setFont(new Font("Serif", Font.BOLD, 16));
        lblDocumento.setForeground(Color.darkGray);
        lblDocumento.setBounds(400, 10, 250, 20);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(400, 25, 175, 20);

        

        lblCorreo = new JLabel("Correo");
        lblCorreo.setFont(new Font("Serif", Font.BOLD, 16));
        lblCorreo.setForeground(Color.darkGray);
        lblCorreo.setBounds(15, 190, 250, 20);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(15, 215, 175, 20);

        lblTelefono = new JLabel("Telefono");
        lblTelefono.setFont(new Font("Serif", Font.BOLD, 16));
        lblTelefono.setForeground(Color.darkGray);
        lblTelefono.setBounds(215, 190, 250, 20);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(215, 215, 190, 20);*/

        cmdGuardar = new JButton("Guardar", new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Cliente");
        cmdGuardar.setBounds(280, 315, 110, 60);
        cmdGuardar.addActionListener(this);

        cmdCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(400, 315, 110, 60);
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

        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Nuevo Cliente");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            int prueba = 0;
            if (txtDireccion.getText().length() == 0
                    || cmbCliente.getSelectedItem().toString().length() == 0 || cmbEstado.getSelectedItem().toString().length() == 0
                    || txtFecha.getDate() == null ) {
                    JOptionPane.showMessageDialog(null, "Hay Datos En Blanco, por favor revisar","ADVERTENCIA", 1);
            } else {
                prueba = 1;
            }
            if (prueba == 1) {
                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                /*Clientes cl = new Clientes();
                cl.setIdCliente(0);
                cl.setNombre(txtNombre.getText().trim());
                cl.setApellido(txtApellido.getText().trim());
                cl.setTipoDocumento(cmbTipoDoc.getSelectedItem().toString());
                cl.setDocumento(txtDocumento.getText().trim());
                cl.setDireccion(txtDireccion.getText().trim());
                cl.setTelefono(txtTelefono.getText());
                cl.setCorreo(txtCorreo.getText());

                CatalogoClientes.getInstancia().getModelo().agregarCliente(cl);

                instancia.dispose();
                txtNombre.setText("");
                txtApellido.setText("");
                txtDireccion.setText("");
                txtCorreo.setText("");
                txtDocumento.setText("");
                txtTelefono.setText("");
                cmbTipoDoc.setSelectedItem(null);*/

            }
        }

        if (objeto.getSource() == cmdCancelar) {
            /*instancia.dispose();
            txtNombre.setText("");
            txtApellido.setText("");
            txtDireccion.setText("");
            txtCorreo.setText("");
            txtDocumento.setText("");
            txtTelefono.setText("");
            cmbTipoDoc.setSelectedItem(null);
            this.setVisible(false);*/
        }
    }

    public static Facturador getInstancia() {
        if (instancia == null) {
            instancia = new Facturador();
        }
        return instancia;
    }

}
