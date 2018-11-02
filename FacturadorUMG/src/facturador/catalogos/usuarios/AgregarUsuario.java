package facturador.catalogos.usuarios;

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
import facturador.beans.Usuario;

public class AgregarUsuario extends JDialog implements ActionListener {

    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblRol;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JLabel lblEstado;

    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtUsuario;
    public JTextField txtPassword;

    private JComboBox cmbRol;
    private JComboBox cmbEstado;

    private JButton cmdGuardar;
    private JButton cmdCancelar;

    private static AgregarUsuario instancia;

    //Constructor
    public AgregarUsuario() {
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/clientes128.png")));
        imagen.setBounds(15, 10, 130, 130);

        lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Serif", Font.BOLD, 16));
        lblUsuario.setForeground(Color.darkGray);
        lblUsuario.setBounds(175, 10, 250, 20);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(175, 35, 175, 20);

        lblRol = new JLabel("Rol");
        lblRol.setFont(new Font("Serif", Font.BOLD, 16));
        lblRol.setForeground(Color.darkGray);
        lblRol.setBounds(400, 10, 250, 20);

        cmbRol = new JComboBox();
        cmbRol.setBounds(400, 35, 190, 20);
        cmbRol.addItem("Usuario");
        cmbRol.addItem("Administrador");

        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Serif", Font.BOLD, 16));
        lblNombre.setForeground(Color.darkGray);
        lblNombre.setBounds(175, 70, 250, 20);

        txtNombre = new JTextField();
        txtNombre.setBounds(175, 95, 400, 20);

        lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Serif", Font.BOLD, 16));
        lblApellido.setForeground(Color.darkGray);
        lblApellido.setBounds(175, 130, 250, 20);

        txtApellido = new JTextField();
        txtApellido.setBounds(175, 155, 400, 20);

        lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Serif", Font.BOLD, 16));
        lblPassword.setForeground(Color.darkGray);
        lblPassword.setBounds(175, 190, 250, 20);

        txtPassword = new JTextField();
        txtPassword.setBounds(175, 215, 175, 20);

        lblEstado = new JLabel("Estado");
        lblEstado.setFont(new Font("Serif", Font.BOLD, 16));
        lblEstado.setForeground(Color.darkGray);
        lblEstado.setBounds(175, 250, 250, 20);

        cmbEstado = new JComboBox();
        cmbEstado.setBounds(175, 275, 200, 20);
        cmbEstado.addItem("Activo");
        cmbEstado.addItem("Inactivo");

        cmdGuardar = new JButton("Guardar", new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Usuario");
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
        panelVentana.add(lblPassword);
        panelVentana.add(lblUsuario);
        panelVentana.add(lblEstado);
        panelVentana.add(lblRol);

        panelVentana.add(txtPassword);
        panelVentana.add(txtUsuario);
        panelVentana.add(cmbEstado);
        panelVentana.add(txtApellido);
        panelVentana.add(txtNombre);

        panelVentana.add(cmbRol);

        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Nuevo Usuario");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            int prueba = 0;
            if (txtPassword.getText().length() == 0
                    || txtUsuario.getText().length() == 0
                    || cmbRol.getSelectedItem().toString().length() == 0 || txtApellido.getText().length() == 0 || cmbEstado.getSelectedItem().toString().length() == 0
                    || txtNombre.getText().length() == 0) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Hay Datos En Blanco Desea Ingresarlo Asi!", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == 0) {
                    prueba = 1;
                }

            } else {
                prueba = 1;
            }
            if (prueba == 1) {
                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");

                Usuario u = new Usuario();
                u.setUsuario(txtUsuario.getText().trim());
                u.setNombre(txtNombre.getText().trim());
                u.setApellido(txtApellido.getText().trim());
                u.setPassword(txtPassword.getText().trim());
                u.setEstado(cmbEstado.getSelectedItem().toString().trim().equalsIgnoreCase("Activo") ? "A" : "I");
                u.setRol(cmbRol.getSelectedItem().toString().trim().equalsIgnoreCase("Usuario") ? "0" : "1");

                CatalogoUsuarios.getInstancia().getModelo().agregarUsuario(u);

                instancia.dispose();
                txtNombre.setText("");
                txtApellido.setText("");
                txtPassword.setText("");
                txtUsuario.setText("");
                cmbEstado.setSelectedItem(null);
                cmbRol.setSelectedItem(null);

            }
        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            txtNombre.setText("");
            txtApellido.setText("");
            txtPassword.setText("");
            txtUsuario.setText("");
            cmbEstado.setSelectedItem(null);
            cmbRol.setSelectedItem(null);
            this.setVisible(false);
        }
    }

    public static AgregarUsuario getInstancia() {
        if (instancia == null) {
            instancia = new AgregarUsuario();
        }
        return instancia;
    }

}
