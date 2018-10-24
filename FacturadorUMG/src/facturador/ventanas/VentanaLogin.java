package facturador.ventanas;

import java.awt.*;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import facturador.db.Conexion;
import facturador.menus.MenuPrincipal;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaLogin extends JDialog implements ActionListener, KeyListener {

    private String a;
    public JTextField txtLogin;
    public JPasswordField txtPassword;
    private JLabel lblLogin;
    private JLabel imagen;
    private JLabel lblPassword;
    private JButton cmdAceptar;
    private JButton cmdCancelar;
    private JPanel panel;
    private Conexion conexion;
    private static VentanaLogin instancia;
    public String loginA = "";
    public String password = "";
    public String vendedor = "";

    public static VentanaLogin getInstancia() {
        if (instancia == null) {
            instancia = new VentanaLogin();
        }
        return instancia;
    }

    public VentanaLogin() {
        this.setTitle("Inicio De Sesion");
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/msn.png")));
        imagen.setBounds(130, 35, 130, 130);


        lblLogin = new JLabel("Usuario");
        lblLogin.setBounds(30, 170, 75, 20);
        lblLogin.setFont(new Font("Serif", Font.BOLD, 12));
        lblLogin.setForeground(Color.darkGray);

        lblPassword = new JLabel("Contraseña");
        lblPassword.setBounds(30, 195, 75, 20);
        lblPassword.setFont(new Font("Serif", Font.BOLD, 12));
        lblPassword.setForeground(Color.darkGray);

        txtLogin = new JTextField(20);
        txtLogin.setBounds(110, 170, 200, 20);

        txtPassword = new JPasswordField(20);
        txtPassword.setBounds(110, 195, 200, 20);

        cmdAceptar = new JButton("Aceptar", new ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        cmdAceptar.setBounds(80, 240, 100, 60);
        cmdAceptar.addActionListener(this);
        cmdAceptar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdAceptar.setHorizontalTextPosition(SwingConstants.CENTER);

        cmdCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setBounds(200, 240, 100, 60);
        cmdCancelar.addActionListener(this);
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(lblLogin);
        panel.add(imagen);
        panel.add(lblPassword);
        panel.add(txtLogin);
        panel.add(txtPassword);
        panel.add(cmdAceptar);
        panel.add(cmdCancelar);
        this.add(panel);
        this.setSize(400, 380);
        this.setResizable(false);
        // panel.setBackground(Color.lightGray);
        txtLogin.addKeyListener(this);
        txtPassword.addKeyListener(this);

    }

    public void keyPressed(KeyEvent e) {
        a = e.getKeyText(e.getKeyCode());
        
        if (a.equals("⏎")) {
            if (txtLogin.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Un Login");
            } else if (txtPassword.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Un Password");
            } else {
                conexion = Conexion.getInstancia();
                ResultSet usuario = conexion.hacerConsulta("select usuario,nombre,rol,estado from umgAnalisis.Usuario where usuario = '" + txtLogin.getText() + "' and password = md5('" + obtenerContraseña(txtPassword.getPassword()) + "')");
                String nombresUsuario = "";
                String tipoUsuario = "";
                String login = "";
                String estado = "";
                try {
                    while (usuario.next()) {
                        login = usuario.getString("usuario");
                        loginA = login;
                        nombresUsuario = usuario.getString("nombre");
                        vendedor = nombresUsuario;
                        tipoUsuario = usuario.getString("rol");
                        estado = usuario.getString("estado");
                    }
                } catch (SQLException a) {
                    a.printStackTrace();
                }

                if (nombresUsuario.length() > 0) {
                    if (estado.trim().equalsIgnoreCase("A")) {
                        password = txtPassword.getText();
                        MenuPrincipal.getInstancia().mostrarMenu();
                        instancia.dispose();
                        VentanaPrincipal.getInstancia().lblHora.setVisible(true);
                        VentanaPrincipal.getInstancia().usuarioC.setVisible(true);
                        VentanaPrincipal.getInstancia().usuario = txtLogin.getText();
                        VentanaPrincipal.getInstancia().usuarioC.setText("Usuario Conectado: " + nombresUsuario);
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema " + "\n" + nombresUsuario);
                        //JOptionPane.showMessageDialog(null, password);
                        if (tipoUsuario.equals("1")) {
                            MenuPrincipal.getInstancia().superUsuario();
                        } else {
                            MenuPrincipal.getInstancia().usuarioNormal();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "USUARIO NO ACTIVO..." + "\n" + "Verifique.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "NO EXISTE..." + "\n" + "Verifique Sus Credenciales");
                }
                txtLogin.setText(null);
                txtPassword.setText(null);

            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdAceptar) {
            if (txtLogin.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Un Login");
            } else if (txtPassword.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Un Password");
            } else {
                conexion = Conexion.getInstancia();
                ResultSet usuario = conexion.hacerConsulta("select usuario,nombre,rol,estado from umgAnalisis.suario where usuario = '" + txtLogin.getText() + "' and password = md5('" + obtenerContraseña(txtPassword.getPassword()) + "')");
                String nombresUsuario = "";
                String tipoUsuario = "";
                String login = "";
                String estado = "";
                try {
                    while (usuario.next()) {
                        login = usuario.getString("usuario");
                        loginA = login;
                        nombresUsuario = usuario.getString("nombre");
                        vendedor = nombresUsuario;
                        tipoUsuario = usuario.getString("rol");
                        estado = usuario.getString("estado");
                    }
                } catch (SQLException a) {
                    a.printStackTrace();
                }
                if (nombresUsuario.length() > 0) {
                    if (estado.trim().equalsIgnoreCase("A")) {
                        password = txtPassword.getText();
                        MenuPrincipal.getInstancia().mostrarMenu();
                        instancia.dispose();
                        VentanaPrincipal.getInstancia().lblHora.setVisible(true);
                        VentanaPrincipal.getInstancia().usuarioC.setVisible(true);
                        VentanaPrincipal.getInstancia().usuario = txtLogin.getText();
                        VentanaPrincipal.getInstancia().usuarioC.setText("Usuario Conectado: " + nombresUsuario);
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema " + "\n" + nombresUsuario);
                        //JOptionPane.showMessageDialog(null, password);
                        if (tipoUsuario.equals("1")) {
                            MenuPrincipal.getInstancia().superUsuario();
                        } else {
                            MenuPrincipal.getInstancia().usuarioNormal();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "USUARIO NO ACTIVO..." + "\n" + "Verifique.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "NO EXISTE..." + "\n" + "Verifique Sus Credenciales");
                }
                txtLogin.setText(null);
                txtPassword.setText(null);

            }

        }
        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            this.setVisible(false);
            txtLogin.setText("");
            txtPassword.setText("");
        }


    }

    public String obtenerContraseña(char[] contraseña) {
        String resultado = "";
        for (int letra = 0; letra < contraseña.length; letra++) {
            resultado += contraseña[letra];
        }
        return resultado;

    }
}
