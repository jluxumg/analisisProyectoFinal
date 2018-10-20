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
import javax.swing.JPasswordField;

public class AgregarVendedor extends JDialog implements ActionListener{
    private JPanel panelVentana;

    private JLabel imagen;
    private JLabel lblAgregando;
    private JLabel lblCodigoVendedor;
    private JLabel lblCorreo;
    private JLabel lblLogin;
    private JLabel lblPassword;
    private JLabel lblConfirmarPassword;
    private JLabel lblNombresUsuario;
    private JLabel lblTipoUsuario;
    private JLabel lblTelefono;
    
    public JTextField txtLogin;
    public JTextField txtNombresUsuario;
    public JTextField txtCodigoVendedor;
    public JTextField txtCorreo;
    public JTextField txtTelefono;

    public JPasswordField txtPassword;
    public JPasswordField txtConfirmarPassword;

    private JComboBox cmbTipoUsuario;
   
    private JButton cmdGuardar;
    private JButton cmdCancelar;
    

    private static AgregarVendedor instancia;
    //Constructor
    public AgregarVendedor(){
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/msn.png")));
        imagen.setBounds(150, 50, 130, 130);

        lblAgregando = new JLabel("Agregando Usuario");
        lblAgregando.setFont(new Font("Serif", Font.BOLD, 18));
        lblAgregando.setForeground(Color.darkGray);
        lblAgregando.setBounds(150, -20, 250, 100);

        lblCodigoVendedor = new JLabel("Codigo De Vendedor");
        lblCodigoVendedor.setBounds(25, 185, 120, 20);

        txtCodigoVendedor = new JTextField(50);
        txtCodigoVendedor.setBounds(185, 185, 100, 20);

        lblLogin = new JLabel("Login");
        lblLogin.setBounds(25, 210, 120, 20);

        txtLogin = new JTextField(50);
        txtLogin.setBounds(185, 210, 200, 20);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(25, 235, 120, 20);

        txtPassword = new JPasswordField(20);
        txtPassword.setBounds(185, 235, 200, 20);

        lblConfirmarPassword = new JLabel("Confirmar Password");
        lblConfirmarPassword.setBounds(25, 260, 120, 20);

        txtConfirmarPassword = new JPasswordField(20);
        txtConfirmarPassword.setBounds(185, 260, 200, 20);

        lblNombresUsuario= new JLabel("Nombres");
        lblNombresUsuario.setBounds(25, 285, 120, 20);

        txtNombresUsuario = new JTextField(50);
        txtNombresUsuario.setBounds(185, 285, 200, 20);

        lblCorreo = new JLabel ("Correo");
        lblCorreo.setBounds(25, 310, 120, 20);

        txtCorreo = new JTextField(50);
        txtCorreo.setBounds(185, 310, 200, 20);

        lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(25, 335, 120, 20);

        txtTelefono = new JTextField(50);
        txtTelefono.setBounds(185, 335, 200, 20);

        lblTipoUsuario = new JLabel("Tipo de Usuario");
        lblTipoUsuario.setBounds(25, 360, 120, 20);
        
        cmbTipoUsuario = new JComboBox();
        cmbTipoUsuario.setBounds(185, 360, 200, 20);
        cmbTipoUsuario.addItem("Vendedor");
        cmbTipoUsuario.addItem("Supervisor");        

        cmdGuardar = new JButton("Guardar",new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Usuario");
        cmdGuardar.setBounds(105, 390, 110, 60);
        cmdGuardar.addActionListener(this);

        cmdCancelar = new JButton("Cancelar",new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(225, 390, 110, 60);
        cmdCancelar.addActionListener(this);


        
        panelVentana.add(imagen);
        panelVentana.add(lblLogin);
        panelVentana.add(lblAgregando);        
        panelVentana.add(lblPassword);
        panelVentana.add(lblConfirmarPassword);              
        panelVentana.add(lblNombresUsuario);
        panelVentana.add(lblTipoUsuario);
        panelVentana.add(lblCorreo);
        panelVentana.add(lblCodigoVendedor);
        panelVentana.add(lblTelefono);
        
        panelVentana.add(txtLogin);
        panelVentana.add(txtPassword);
        panelVentana.add(txtConfirmarPassword);
        panelVentana.add(txtNombresUsuario);
        panelVentana.add(txtCorreo);
        panelVentana.add(txtCodigoVendedor);
        panelVentana.add(txtTelefono);

        panelVentana.add(cmbTipoUsuario);

        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Ingreso de Usuarios");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(450,490);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto){
        if(objeto.getSource() == cmdGuardar){
            /*if (txtLogin.getText().length() == 0 || txtPassword.getText().length() == 0
                    || txtConfirmarPassword.getText().length() == 0 || txtNombresUsuario.getText().length() == 0
                    || txtApellidosUsuario.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Debe Llenar Todos Los Campos");

            }
            else{
                if (txtPassword.getText().length() < 6){
                    JOptionPane.showMessageDialog(null, "La Password debe tener almenos 6 Caracteres");
                }
                else{
                    if(txtPassword.getText().equals(txtConfirmarPassword.getText())){
                        JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                        CatalogoUsuario.getInstancia().getModelo().agregarUsuario(new Usuario(0,txtLogin.getText(),txtPassword.getText(),txtNombresUsuario.getText(),txtApellidosUsuario.getText(), cmbTipoUsuario.getSelectedItem().toString()));
                        instancia.dispose();
                        txtLogin.setText("");
                        txtPassword.setText("");
                        txtConfirmarPassword.setText("");
                        txtNombresUsuario.setText("");
                        txtApellidosUsuario.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "La Password No Coincide");
                    }
                }

            }*/

        }
        if(objeto.getSource()==cmdCancelar){
            instancia.dispose();
            this.setVisible(false);
            
        }
    }
    public static AgregarVendedor getInstancia(){
        if(instancia == null){
            instancia = new AgregarVendedor();
        }
        return instancia;
    }
}
