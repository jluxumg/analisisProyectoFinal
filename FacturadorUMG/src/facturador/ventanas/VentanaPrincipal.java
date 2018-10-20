package facturador.ventanas;

import facturador.menus.MenuPrincipal;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import facturador.catalogos.Hilo;

public class VentanaPrincipal extends JFrame {

    public JDesktopPane panelVentana;
    public Date fecha;
    public String usuario;
    public JLabel usuarioC;
    public JLabel lblHora;
    public JLabel lblFecha;
    public JLabel lblSoloHora;
    private Container contenedor;
    private static VentanaPrincipal instancia;

    public VentanaPrincipal() {

        DateFormat formFecha =
                DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("es", "GT"));

        fecha = new Date();
        usuarioC = new JLabel("Usuario Conectado");
        usuarioC.setBounds(600, 0, 400, 20);
        usuarioC.setFont(new Font("Serif", Font.BOLD, 14));
        usuarioC.setForeground(Color.WHITE);

        lblFecha = new JLabel(formFecha.format(fecha));
        lblFecha.setBounds(500, 25, 400, 20);

        lblFecha.setVisible(false);



        this.setTitle("Inmobiliaria");
        this.setSize(1280, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);



        panelVentana = new JDesktopPane() {

            public void paintComponent(Graphics g) {
                g.drawImage((new ImageIcon(getClass().getResource("/Imagenes/f6.jpg")).getImage()), 0, 0, getWidth(), getHeight(), this);
                setOpaque(false);
            }
        };

        contenedor = this.getContentPane();
        contenedor.add(panelVentana, BorderLayout.CENTER);
        panelVentana.add(usuarioC);
        usuarioC.setVisible(false);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setJMenuBar(MenuPrincipal.getInstancia().getMenu());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")).getImage());


        Date hora = new Date();
        String fechaConFormato;
        SimpleDateFormat formHora = new SimpleDateFormat("' 'h:mm a",
                new Locale("es", "GT"));
        fechaConFormato = formHora.format(hora);
        lblHora = new JLabel("Fecha: " + formFecha.format(fecha) + ", Hora De Inicio: " + fechaConFormato);
        lblHora.setBounds(600, 25, 400, 20);
        lblHora.setFont(new Font("Serif", Font.BOLD, 14));
        lblHora.setForeground(Color.WHITE);

        lblSoloHora = new JLabel("Hora: " + fechaConFormato);
        lblSoloHora.setBounds(10, 10, 400, 20);
        lblSoloHora.setFont(new Font("Serif", Font.BOLD, 14));
        lblSoloHora.setForeground(Color.WHITE);

        panelVentana.add(lblSoloHora);

        panelVentana.add(lblHora);
        panelVentana.add(lblFecha);
        lblHora.setVisible(false);
        Hilo elHilo = new Hilo();
        elHilo.start();
    }

    public static VentanaPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }

    public void mdiParent(JInternalFrame ventana) {
        boolean encontrado = false;
        try {
            JInternalFrame ventanas[] = panelVentana.getAllFrames();
            for (int i = 0; i < ventanas.length; i++) {
                if (ventanas[i] == ventana) {
                    encontrado = true;
                    break;
                }
            }
            if (encontrado == false) {
                panelVentana.add(ventana);
            }
            ventana.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
