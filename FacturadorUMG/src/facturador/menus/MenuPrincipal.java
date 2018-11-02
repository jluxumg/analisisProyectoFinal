package facturador.menus;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import facturador.catalogos.cliente.CatalogoClientes;
import facturador.catalogos.factura.CatalogoFacturas;
import facturador.catalogos.producto.CatalogoProducto;
import facturador.catalogos.usuarios.CatalogoUsuarios;
import facturador.catalogos.varios.CatalogoAcercaDe;
import facturador.ventanas.VentanaLogin;
import facturador.ventanas.VentanaPrincipal;
import javax.swing.JOptionPane;

public class MenuPrincipal implements ActionListener {

    private JMenuBar barraMenu;
    private JMenu login;
    private JMenuItem salir;
    private JMenuItem iniciarSesion;
    private JMenuItem cerrarSesion;
    private JDialog ventanaLogin;

    private JMenu Mproductos;
    private JMenu Mclientes;
    private JMenu Musuarios;
    private JMenu Mfacturar;
    private JMenuItem productos;
    private JMenuItem clientes;
    private JMenuItem usuarios;
    private JMenuItem facturar;

    private JMenu acercaDe;
    private JMenuItem acercaDe2;

    private static MenuPrincipal instancia;

    public static MenuPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new MenuPrincipal();
        }
        return instancia;
    }

    public MenuPrincipal() {
        barraMenu = new JMenuBar();

    }

    public JMenuBar getMenu() {
        login = new JMenu("Login        ");
        login.setIcon(new ImageIcon(getClass().getResource("/Imagenes/login.png")));
        login.setMnemonic('L');

        Mproductos = new JMenu("Productos");
        Mproductos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/mas22.png")));
        Mproductos.setMnemonic('P');
        Mproductos.setVisible(false);

        Mclientes = new JMenu("Clientes");
        Mclientes.setIcon(new ImageIcon(getClass().getResource("/Imagenes/clientes.png")));
        Mclientes.setMnemonic('C');
        Mclientes.setVisible(false);

        Musuarios = new JMenu("Usuarios");
        Musuarios.setIcon(new ImageIcon(getClass().getResource("/Imagenes/login.png")));
        Musuarios.setMnemonic('U');
        Musuarios.setVisible(false);

        Mfacturar = new JMenu("Facturador");
        Mfacturar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/caja.png")));
        Mfacturar.setMnemonic('F');
        Mfacturar.setVisible(false);

        productos = new JMenuItem("Productos");
        productos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/mas.png")));
        productos.setMnemonic('P');
        productos.addActionListener(this);

        clientes = new JMenuItem("Clientes");
        clientes.setIcon(new ImageIcon(getClass().getResource("/Imagenes/cliente.png")));
        clientes.setMnemonic('C');
        clientes.addActionListener(this);

        usuarios = new JMenuItem("Usuarios");
        usuarios.setIcon(new ImageIcon(getClass().getResource("/Imagenes/iniciarSesion.png")));
        usuarios.setMnemonic('U');
        usuarios.addActionListener(this);

        facturar = new JMenuItem("Facturar");
        facturar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/editar.png")));
        facturar.setMnemonic('U');
        facturar.addActionListener(this);

        iniciarSesion = new JMenuItem("Iniciar Sesion");
        iniciarSesion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/iniciarSesion.png")));
        iniciarSesion.setMnemonic('I');
        iniciarSesion.addActionListener(this);

        cerrarSesion = new JMenuItem("Cerrar Sesion");
        cerrarSesion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/cerrar sesion.png")));
        cerrarSesion.setMnemonic('C');
        cerrarSesion.setVisible(false);
        cerrarSesion.addActionListener(this);

        salir = new JMenuItem("Salir");
        salir.setIcon(new ImageIcon(getClass().getResource("/Imagenes/salir.png")));
        salir.setMnemonic('S');
        salir.addActionListener(this);
        salir.setVisible(true);

        acercaDe = new JMenu("Acerca De");
        acercaDe.setIcon(new ImageIcon(getClass().getResource("/Imagenes/acercaDe.png")));
        acercaDe.setVisible(true);

        acercaDe2 = new JMenuItem("Informacion");
        acercaDe2.setIcon(new ImageIcon(getClass().getResource("/Imagenes/info.png")));
        acercaDe2.addActionListener(this);

        login.add(iniciarSesion);
        login.add(cerrarSesion);
        login.add(salir);

        acercaDe.add(acercaDe2);

        Mproductos.add(productos);
        Mclientes.add(clientes);
        Musuarios.add(usuarios);
        Mfacturar.add(facturar);

        barraMenu.add(login);
        barraMenu.add(Mproductos);
        barraMenu.add(Mclientes);
        barraMenu.add(Musuarios);
        barraMenu.add(Mfacturar);
        barraMenu.add(acercaDe);

        //barraMenu.add(salir);
        return barraMenu;

    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == iniciarSesion) {
            iniciarSesion.setVisible(true);
            ventanaLogin = VentanaLogin.getInstancia();
            ventanaLogin.setLocationRelativeTo(null);
            ventanaLogin.setModal(true);
            ventanaLogin.setVisible(true);

            VentanaLogin.getInstancia().txtLogin.setText("");
            VentanaLogin.getInstancia().txtPassword.setText("");

        }
        if (objeto.getSource() == salir) {
            int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea salir de la Aplicacion", "Cerrar Aplicacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == 0) {
                System.exit(1);
            }

        }

        if (objeto.getSource() == cerrarSesion) {
            ocultarMenu();

        }
        if (objeto.getSource() == clientes) {
            CatalogoClientes.getInstancia().getModelo().actualizar();
            CatalogoClientes catalogoClientes = CatalogoClientes.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(catalogoClientes);

        }
        if (objeto.getSource() == productos) {
            CatalogoProducto.getInstancia().getModelo().actualizar();
            CatalogoProducto cp = CatalogoProducto.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(cp);
        }
        if (objeto.getSource() == usuarios) {
            CatalogoUsuarios.getInstancia().getModelo().actualizar();
            CatalogoUsuarios cu = CatalogoUsuarios.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(cu);
        }

        if (objeto.getSource() == facturar) {
            CatalogoFacturas.getInstancia().getModelo().actualizar();
            CatalogoFacturas catalogoFacturas = CatalogoFacturas.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(catalogoFacturas);
        }

        if (objeto.getSource() == acercaDe2) {
            CatalogoAcercaDe catalogoAcercaDe = CatalogoAcercaDe.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(catalogoAcercaDe);
        }

    }

    public void ocultarMenu() {
        iniciarSesion.setVisible(true);
        cerrarSesion.setVisible(false);
        login.setText("Login");
        salir.setVisible(true);
        Mproductos.setVisible(false);
        Mclientes.setVisible(false);
        Musuarios.setVisible(false);
        Mfacturar.setVisible(false);
        VentanaPrincipal.getInstancia().lblHora.setVisible(false);
        VentanaPrincipal.getInstancia().usuarioC.setVisible(false);
        VentanaLogin.getInstancia().vendedor = "";
        VentanaLogin.getInstancia().getParent().setVisible(false);
    }

    public void mostrarMenu() {
        login.setText("Conectado");
        cerrarSesion.setVisible(true);
        iniciarSesion.setVisible(false);
        salir.setVisible(false);
        Mproductos.setVisible(true);
        Mclientes.setVisible(true);
        Musuarios.setVisible(true);
        Mfacturar.setVisible(true);

    }

    public void superUsuario() {
        salir.setVisible(false);
    }

    public void usuarioNormal() {
        salir.setVisible(false);

    }

}
