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
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import facturador.beans.Clientes;
import facturador.ventanas.VentanaLogin;


public class AgregarCliente extends JDialog implements ActionListener{
    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblCliente;
    private JLabel lblCompañia;
    private JLabel lblGiro;
    private JLabel lblDireccion;
    private JLabel lblCorreo;
    private JLabel lblTelefono1;
    private JLabel lblTelefono2;
    private JLabel lblFax;
    private JLabel lblCelular;
    private JLabel lblTipoCliente;
    private JLabel lblVendedor;

    public JTextField txtCliente;
    public JTextField txtCompañia;
    public JTextField txtDireccion;
    public JTextField txtCorreo;
    public JTextField txtTelefono1;
    public JTextField txtTelefono2;
    public JTextField txtFax;
    public JTextField txtCelular;

    private JComboBox cmbGiro;
    private JComboBox cmbTipoCliente;
    private JTextField cmbVendedor;
    
    private JButton cmdGuardar;
    private JButton cmdCancelar;
    
    
    private static AgregarCliente instancia;
    //Constructor
    public AgregarCliente(){
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
        txtCliente.setBounds(175, 35, 200, 20);

        lblCompañia = new JLabel("Compañia");
        lblCompañia.setFont(new Font("Serif", Font.BOLD, 16));
        lblCompañia.setForeground(Color.darkGray);
        lblCompañia.setBounds(175, 60, 250, 20);

        txtCompañia = new JTextField();
        txtCompañia.setBounds(175, 85, 200, 20);

        lblGiro = new JLabel("Giro");
        lblGiro.setFont(new Font("Serif", Font.BOLD, 16));
        lblGiro.setForeground(Color.darkGray);
        lblGiro.setBounds(400, 10, 250, 20);

        cmbGiro = new JComboBox();
        cmbGiro.setBounds(400, 35, 150, 20);
        cmbGiro.addItem("Individual");
        cmbGiro.addItem("Empresa");

        lblTipoCliente = new JLabel("Tipo De Cliente");
        lblTipoCliente.setFont(new Font("Serif", Font.BOLD, 16));
        lblTipoCliente.setForeground(Color.darkGray);
        lblTipoCliente.setBounds(400, 60, 250, 20);

        cmbTipoCliente = new JComboBox();
        cmbTipoCliente.setBounds(400, 85, 150, 20);
        cmbTipoCliente.addItem("Publico");
        cmbTipoCliente.addItem("Intermedio");
        cmbTipoCliente.addItem("Mayorista");

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

        lblTelefono1 = new JLabel("Telefono 1");
        lblTelefono1.setFont(new Font("Serif", Font.BOLD, 16));
        lblTelefono1.setForeground(Color.darkGray);
        lblTelefono1.setBounds(205, 190, 250, 20);

        txtTelefono1 = new JTextField();
        txtTelefono1.setBounds(205, 215, 175, 20);

        lblTelefono2 = new JLabel("Telefono 2");
        lblTelefono2.setFont(new Font("Serif", Font.BOLD, 16));
        lblTelefono2.setForeground(Color.darkGray);
        lblTelefono2.setBounds(390, 190, 250, 20);

        txtTelefono2 = new JTextField();
        txtTelefono2.setBounds(390, 215, 175, 20);

        lblFax = new JLabel("Fax");
        lblFax.setFont(new Font("Serif", Font.BOLD, 16));
        lblFax.setForeground(Color.darkGray);
        lblFax.setBounds(15, 240, 250, 20);

        txtFax = new JTextField();
        txtFax.setBounds(15, 265, 175, 20);

        lblCelular = new JLabel("Celular");
        lblCelular.setFont(new Font("Serif", Font.BOLD, 16));
        lblCelular.setForeground(Color.darkGray);
        lblCelular.setBounds(205, 240, 250, 20);

        txtCelular = new JTextField();
        txtCelular.setBounds(205, 265, 175, 20);

        lblVendedor = new JLabel("Vendedor");
        lblVendedor.setFont(new Font("Serif", Font.BOLD, 16));
        lblVendedor.setForeground(Color.darkGray);
        lblVendedor.setBounds(390, 240, 250, 20);
        

        cmbVendedor = new JTextField();
        cmbVendedor.setBounds(390, 265, 175, 20);
        cmbVendedor.setEditable(false);

        cmdGuardar = new JButton("Guardar",new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Cliente");
        cmdGuardar.setBounds(280, 315, 110, 60);
        cmdGuardar.addActionListener(this);

        cmdCancelar = new JButton("Cancelar",new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(400, 315, 110, 60);
        cmdCancelar.addActionListener(this);


        panelVentana.add(imagen);

        panelVentana.add(lblCliente);
        panelVentana.add(lblCompañia);
        panelVentana.add(lblGiro);
        panelVentana.add(lblDireccion);
        panelVentana.add(lblCorreo);
        panelVentana.add(lblTelefono1);
        panelVentana.add(lblTelefono2);
        panelVentana.add(lblFax);
        panelVentana.add(lblCelular);
        panelVentana.add(lblTipoCliente);
        panelVentana.add(lblVendedor);

        panelVentana.add(txtCliente);
        panelVentana.add(txtDireccion);
        panelVentana.add(txtCorreo);
        panelVentana.add(txtTelefono1);
        panelVentana.add(txtTelefono2);
        panelVentana.add(txtFax);
        panelVentana.add(txtCelular);
        panelVentana.add(txtCompañia);

        panelVentana.add(cmbGiro);
        panelVentana.add(cmbTipoCliente);
        panelVentana.add(cmbVendedor);

        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Nuevo Cliente");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,420);
        this.setLocationRelativeTo(null);
        

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto){
        if(objeto.getSource() == cmdGuardar){
            int prueba =0;
            if (txtCliente.getText().length() == 0 || txtDireccion.getText().length() == 0
                    || txtCorreo.getText().length() == 0 || txtTelefono1.getText().length() == 0
                    || txtTelefono2.getText().length() == 0 || txtFax.getText().length() == 0
                    || txtCelular.getText().length() == 0 || txtCompañia.getText().length() == 0){
                int respuesta = JOptionPane.showConfirmDialog(null, "Hay Datos En Blanco Desea Ingresarlo Asi!", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta==0){
                    prueba = 1;
                }

            }else{
                prueba =1;
            }
            if(prueba == 1 ){
                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                CatalogoClientes.getInstancia().getModelo().agregarCliente(new Clientes (0,txtCliente.getText(),txtCompañia.getText(),cmbGiro.getSelectedItem().toString(),txtDireccion.getText(),txtCorreo.getText(),txtTelefono1.getText(),txtTelefono2.getText(),txtFax.getText(),txtCelular.getText(),cmbTipoCliente.getSelectedItem().toString(),VentanaLogin.getInstancia().vendedor));
                instancia.dispose();
                txtCliente.setText("");
                txtDireccion.setText("");
                txtCorreo.setText("");
                txtTelefono1.setText("");
                txtTelefono2.setText("");
                txtFax.setText("");
                txtCelular.setText("");
                txtCompañia.setText("");
            }
        }
        
        if(objeto.getSource()==cmdCancelar){
            instancia.dispose();
            txtCliente.setText("");
            txtDireccion.setText("");
            txtCorreo.setText("");
            txtTelefono1.setText("");
            txtTelefono2.setText("");
            txtFax.setText("");
            txtCelular.setText("");
            txtCompañia.setText("");
            this.setVisible(false);            
        }
    }
    public static AgregarCliente getInstancia(){
        if(instancia == null){
            instancia = new AgregarCliente();
        }
        return instancia;
    }
    public void llenarVendedor(){
        cmbVendedor.setText(VentanaLogin.getInstancia().vendedor);
    }
}
