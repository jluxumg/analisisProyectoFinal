package facturador.catalogos.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class CatalogoDetallesCliente extends JDialog implements ActionListener {

    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblCliente;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblTipoDocumento;
    private JLabel lblDocumento;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblCorreo;

    public JTextField txtCliente;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtTipoDocumento;
    public JTextField txtDireccion;
    public JTextField txtTelefono;
    public JTextField txtCorreo;

    private JTextField cmbTipoDocumento;

    private JButton cmdCancelar;

    private static CatalogoDetallesCliente instancia;

    //Constructor
    public CatalogoDetallesCliente() {
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/clientes128.png")));
        imagen.setBounds(15, 10, 130, 130);

        lblCliente = new JLabel("Cliente");
        lblCliente.setFont(new Font("Serif", Font.BOLD, 16));
        lblCliente.setForeground(Color.darkGray);
        lblCliente.setBounds(175, 10, 100, 20);

        txtCliente = new JTextField();
        txtCliente.setBounds(175, 35, 100, 20);
        txtCliente.setEditable(false);

        lblTipoDocumento = new JLabel("Tipo Documento");
        lblTipoDocumento.setFont(new Font("Serif", Font.BOLD, 16));
        lblTipoDocumento.setForeground(Color.darkGray);
        lblTipoDocumento.setBounds(175, 60, 250, 20);

        cmbTipoDocumento = new JTextField();
        cmbTipoDocumento.setBounds(175, 85, 175, 20);
        cmbTipoDocumento.setEditable(false);

        lblDocumento = new JLabel("Documento");
        lblDocumento.setFont(new Font("Serif", Font.BOLD, 16));
        lblDocumento.setForeground(Color.darkGray);
        lblDocumento.setBounds(400, 60, 250, 20);

        txtTipoDocumento = new JTextField();
        txtTipoDocumento.setBounds(400, 85, 175, 20);
        txtTipoDocumento.setEditable(false);

        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Serif", Font.BOLD, 16));
        lblNombre.setForeground(Color.darkGray);
        lblNombre.setBounds(175, 110, 250, 20);

        txtNombre = new JTextField();
        txtNombre.setBounds(175, 135, 175, 20);
        txtNombre.setEditable(false);

        lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Serif", Font.BOLD, 16));
        lblApellido.setForeground(Color.darkGray);
        lblApellido.setBounds(400, 110, 250, 20);

        txtApellido = new JTextField();
        txtApellido.setBounds(400, 135, 175, 20);
        txtApellido.setEditable(false);

        lblTelefono = new JLabel("Telefono");
        lblTelefono.setFont(new Font("Serif", Font.BOLD, 16));
        lblTelefono.setForeground(Color.darkGray);
        lblTelefono.setBounds(175, 160, 250, 20);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(175, 185, 175, 20);
        txtTelefono.setEditable(false);

        lblCorreo = new JLabel("Correo");
        lblCorreo.setFont(new Font("Serif", Font.BOLD, 16));
        lblCorreo.setForeground(Color.darkGray);
        lblCorreo.setBounds(400, 160, 250, 20);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(400, 185, 175, 20);
        txtCorreo.setEditable(false);

        lblDireccion = new JLabel("Direccion");
        lblDireccion.setFont(new Font("Serif", Font.BOLD, 16));
        lblDireccion.setForeground(Color.darkGray);
        lblDireccion.setBounds(75, 210, 250, 20);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(75, 235, 500, 20);
        txtDireccion.setEditable(false);

        cmdCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(400, 315, 110, 60);
        cmdCancelar.addActionListener(this);

        panelVentana.add(imagen);

        panelVentana.add(lblCliente);
        panelVentana.add(lblNombre);
        panelVentana.add(lblApellido);
        panelVentana.add(lblTipoDocumento);
        panelVentana.add(lblDocumento);
        panelVentana.add(lblDireccion);
        panelVentana.add(lblTelefono);
        panelVentana.add(lblCorreo);

        panelVentana.add(txtCliente);
        panelVentana.add(txtApellido);
        panelVentana.add(txtTipoDocumento);
        panelVentana.add(txtDireccion);
        panelVentana.add(txtTelefono);
        panelVentana.add(txtCorreo);
        panelVentana.add(txtNombre);

        panelVentana.add(cmbTipoDocumento);

        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Detalles De Cliente");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            this.setVisible(false);
        }
    }

    public static CatalogoDetallesCliente getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoDetallesCliente();
        }
        return instancia;
    }

    public void llenar() {

        txtCliente.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 0).toString());
        txtNombre.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 1).toString());
        txtApellido.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 2).toString());
        cmbTipoDocumento.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 3).toString());
        txtTipoDocumento.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 4).toString());
        txtDireccion.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 5).toString());
        txtCorreo.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 6).toString());
        txtTelefono.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 7).toString());
    }
}
