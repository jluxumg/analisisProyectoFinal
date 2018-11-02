package facturador.catalogos.usuarios;

import facturador.catalogos.cliente.*;
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

public class ModificarUsuario extends JDialog implements ActionListener {

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

    private int numeroElemento;
    private Usuario usuario;
    private String idUsuario;
    private String passAnterior;

    private static ModificarUsuario instancia;

    //Constructor
    public ModificarUsuario() {
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
        txtUsuario.setEnabled(false);

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
        this.setTitle("Editar Usuario");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            getModificarUsuario();
            JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
            CatalogoUsuarios.getInstancia().getModelo().modificarUsuario(numeroElemento, usuario);
            instancia.dispose();

        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            this.setVisible(false);
        }
    }

    public static ModificarUsuario getInstancia(Usuario usuario, int elemento) {
        if (instancia == null) {
            instancia = new ModificarUsuario();
        }
        instancia.setModificarUsuario(usuario, elemento);
        return instancia;
    }

    public void setModificarUsuario(Usuario usuario, int elemento) {
        numeroElemento = elemento;
        idUsuario = usuario.getUsuario();
        passAnterior = usuario.getPassword();
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
        cmbEstado.setSelectedItem(usuario.getEstado());
        cmbRol.setSelectedItem(usuario.getRol());
        txtPassword.setText("**********");
        txtUsuario.setText(usuario.getUsuario());

    }

    public void getModificarUsuario() {
        usuario = new Usuario();
        usuario.setUsuario(idUsuario);
        usuario.setNombre(txtNombre.getText().trim());
        usuario.setApellido(txtApellido.getText().trim());
        usuario.setEstado(cmbEstado.getSelectedItem().toString().trim().equalsIgnoreCase("Activo") ? "A" : "I");
        usuario.setRol(cmbRol.getSelectedItem().toString().trim().equalsIgnoreCase("Usuario") ? "0" : "1");
        usuario.setPassword(txtPassword.getText().trim().equalsIgnoreCase("**********") ? passAnterior : txtPassword.getText().trim());

    }

    public void llenar() {

        idUsuario = CatalogoUsuarios.getInstancia().tblUsuarios.getValueAt(CatalogoUsuarios.getInstancia().tblUsuarios.getSelectedRow(), 0).toString();
        txtNombre.setText(CatalogoUsuarios.getInstancia().tblUsuarios.getValueAt(CatalogoUsuarios.getInstancia().tblUsuarios.getSelectedRow(), 1).toString());
        txtApellido.setText(CatalogoUsuarios.getInstancia().tblUsuarios.getValueAt(CatalogoUsuarios.getInstancia().tblUsuarios.getSelectedRow(), 2).toString());
        cmbEstado.setSelectedItem(CatalogoUsuarios.getInstancia().tblUsuarios.getValueAt(CatalogoUsuarios.getInstancia().tblUsuarios.getSelectedRow(), 3).toString());
        cmbRol.setSelectedItem(CatalogoUsuarios.getInstancia().tblUsuarios.getValueAt(CatalogoUsuarios.getInstancia().tblUsuarios.getSelectedRow(), 4).toString());
        txtPassword.setText(CatalogoUsuarios.getInstancia().tblUsuarios.getValueAt(CatalogoUsuarios.getInstancia().tblUsuarios.getSelectedRow(), 5).toString());

    }
}
