package facturador.catalogos;

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
import javax.swing.JOptionPane;
import facturador.beans.Clientes;

public class ModificarCliente extends JDialog implements ActionListener {

    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblTipoDocumento;
    private JLabel lblDocumento;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblCorreo;

    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtDocumento;
    public JTextField txtDireccion;
    public JTextField txtTelefono;
    public JTextField txtCorreo;

    private JComboBox cmbTipoDoc;

    private JButton cmdGuardar;
    private JButton cmdCancelar;

    private int numeroElemento;
    private Clientes cliente;
    private int idCliente;

    private static ModificarCliente instancia;

    //Constructor
    public ModificarCliente() {
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/clientes128.png")));
        imagen.setBounds(15, 10, 130, 130);

        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Serif", Font.BOLD, 16));
        lblNombre.setForeground(Color.darkGray);
        lblNombre.setBounds(175, 60, 250, 20);

        txtNombre = new JTextField();
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

        lblDireccion = new JLabel("Direccion Completa");
        lblDireccion.setFont(new Font("Serif", Font.BOLD, 16));
        lblDireccion.setForeground(Color.darkGray);
        lblDireccion.setBounds(15, 140, 250, 20);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(15, 165, 400, 20);

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
        txtTelefono.setBounds(215, 215, 190, 20);

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

        panelVentana.add(lblNombre);
        panelVentana.add(lblApellido);
        panelVentana.add(lblDireccion);
        panelVentana.add(lblCorreo);
        panelVentana.add(lblDocumento);
        panelVentana.add(lblTelefono);
        panelVentana.add(lblTipoDocumento);

        panelVentana.add(txtDireccion);
        panelVentana.add(txtCorreo);
        panelVentana.add(txtDocumento);
        panelVentana.add(txtTelefono);
        panelVentana.add(txtApellido);
        panelVentana.add(txtNombre);

        panelVentana.add(cmbTipoDoc);

        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Editar Cliente");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            getModificarCliente();
            JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
            CatalogoClientes.getInstancia().getModelo().modificarCliente(numeroElemento, cliente);
            instancia.dispose();

        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            this.setVisible(false);
        }
    }

    public static ModificarCliente getInstancia(Clientes clientes, int elemento) {
        if (instancia == null) {
            instancia = new ModificarCliente();
        }
        instancia.setModificarCliente(clientes, elemento);
        return instancia;
    }

    public void setModificarCliente(Clientes clientes, int elemento) {
        numeroElemento = elemento;
        idCliente = clientes.getIdCliente();
        txtNombre.setText(clientes.getNombre());
        txtApellido.setText(clientes.getApellido());
        cmbTipoDoc.setSelectedItem(clientes.getTipoDocumento());
        txtDocumento.setText(clientes.getDocumento());
        txtDireccion.setText(clientes.getDireccion());
        txtCorreo.setText(clientes.getCorreo());
        txtTelefono.setText(clientes.getTelefono());

    }

    public void getModificarCliente() {
        cliente = new Clientes();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(txtNombre.getText().trim());
        cliente.setApellido(txtApellido.getText().trim());
        cliente.setDireccion(txtDireccion.getText().trim());
        cliente.setCorreo(txtCorreo.getText().trim());
        cliente.setTelefono(txtTelefono.getText().trim());
        cliente.setDocumento(txtDocumento.getText().trim());
        cliente.setTipoDocumento(String.valueOf(cmbTipoDoc.getSelectedItem()).trim());

    }

    public void llenar() {

        idCliente = Integer.parseInt(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 0).toString());
        txtNombre.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 1).toString());
        txtApellido.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 2).toString());
        cmbTipoDoc.setSelectedItem(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 3).toString());
        txtDocumento.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 4).toString());
        txtDireccion.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 5).toString());
        txtCorreo.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 6).toString());
        txtTelefono.setText(CatalogoClientes.getInstancia().tblClientes.getValueAt(CatalogoClientes.getInstancia().tblClientes.getSelectedRow(), 7).toString());

    }
}
