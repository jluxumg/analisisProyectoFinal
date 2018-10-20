package facturador.menus;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import facturador.catalogos.AgregarCliente;
import facturador.catalogos.AgregarRecordatorio;
import facturador.catalogos.AgregarVendedor;
import facturador.catalogos.CatalogoAcercaDe;
import facturador.catalogos.CatalogoClientes;
import facturador.catalogos.CatalogoRecordatorios;
import facturador.manejadores.ManejadorDeClientes;
import facturador.modelodedatos.ModeloDeDatosCliente;
import facturador.ventanas.VentanaLogin;
import facturador.ventanas.VentanaPrincipal;

public class MenuPrincipal implements ActionListener{
    private JMenuBar barraMenu;
    private JMenu login;
    private JMenuItem salir;
    private JMenuItem iniciarSesion;
    private JMenuItem cerrarSesion;
    private JDialog ventanaLogin;

    private JMenu Mvendedores;
    private JMenu Mclientes;
    private JMenu Mrecordatorios;
    private JMenuItem vendedores;
    private JMenuItem clientes;
    private JMenuItem recordatorios;
    
    private JMenu acercaDe;
    private JMenuItem acercaDe2;
    


    private static MenuPrincipal instancia;

    public static MenuPrincipal getInstancia(){
        if(instancia == null){
            instancia= new MenuPrincipal();
        }
        return instancia;
    }
    public MenuPrincipal(){
        barraMenu = new JMenuBar();
   
    }
    
    public JMenuBar getMenu(){
        login = new JMenu("Login");
        login.setIcon(new ImageIcon(getClass().getResource("/Imagenes/login.png")));
        login.setMnemonic('L');

        Mvendedores = new JMenu("Vendedores");
        Mvendedores.setIcon(new ImageIcon(getClass().getResource("/Imagenes/login.png")));
        Mvendedores.setMnemonic('V');
        Mvendedores.setVisible(false);

        Mclientes = new JMenu("Clientes");
        Mclientes.setIcon(new ImageIcon(getClass().getResource("/Imagenes/clientes.png")));
        Mclientes.setMnemonic('C');
        Mclientes.setVisible(false);

        Mrecordatorios = new JMenu("Recordatorios");
        Mrecordatorios.setIcon(new ImageIcon(getClass().getResource("/Imagenes/relojfecha.png")));
        Mrecordatorios.setMnemonic('R');
        Mrecordatorios.setVisible(false);

        vendedores = new JMenuItem("Vendedores");
        vendedores.setIcon(new ImageIcon(getClass().getResource("/Imagenes/iniciarSesion.png")));
        vendedores.setMnemonic('V');
        vendedores.addActionListener(this);

        clientes = new JMenuItem("Clientes");
        clientes.setIcon(new ImageIcon(getClass().getResource("/Imagenes/cliente.png")));
        clientes.setMnemonic('C');
        clientes.addActionListener(this);

        recordatorios = new JMenuItem("Recordatorios");
        recordatorios.setIcon(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")));
        recordatorios.setMnemonic('R');
        recordatorios.addActionListener(this);

        iniciarSesion = new JMenuItem("Iniciar Sesion");
        iniciarSesion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/iniciarSesion.png")));
        iniciarSesion.setMnemonic('I');
        iniciarSesion.addActionListener(this);

        cerrarSesion =new JMenuItem("Cerrar Sesion");
        cerrarSesion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/cerrar sesion.png")));
        cerrarSesion.setMnemonic('C');
        cerrarSesion.setVisible(false);
        cerrarSesion.addActionListener(this);
  
        salir= new JMenuItem("Salir");
        salir.setIcon(new ImageIcon (getClass().getResource("/Imagenes/salir.png")));
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
        
        Mvendedores.add(vendedores);
        Mclientes.add(clientes);
        Mrecordatorios.add(recordatorios);

        

        barraMenu.add(login);
        barraMenu.add(Mvendedores);
        barraMenu.add(Mclientes);
        barraMenu.add(Mrecordatorios);
        barraMenu.add(acercaDe);
       
        //barraMenu.add(salir);
        return barraMenu;


    }

    public void actionPerformed(ActionEvent objeto){
        if(objeto.getSource()==iniciarSesion){
            iniciarSesion.setVisible(true);
            ventanaLogin = VentanaLogin.getInstancia();
            ventanaLogin.setLocationRelativeTo(null);
            ventanaLogin.setModal(true);
            ventanaLogin.setVisible(true);

            VentanaLogin.getInstancia().txtLogin.setText("");
            VentanaLogin.getInstancia().txtPassword.setText("");

        }
        if (objeto.getSource()== salir){
            int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea salir de la Aplicacion", "Cerrar Aplicacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta == 0 ){
                 System.exit(1);
            }


        }

        if (objeto.getSource()==cerrarSesion){
            ocultarMenu();
                        
        }
        if(objeto.getSource()==clientes){
            CatalogoClientes.getInstancia().getModelo().actualizar();
            CatalogoClientes catalogoClientes= CatalogoClientes.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(catalogoClientes);

        }
        if(objeto.getSource()==vendedores){
            AgregarVendedor agregarVendedor= AgregarVendedor.getInstancia();
            agregarVendedor.setVisible(true);                    
        }
        if(objeto.getSource()==recordatorios){
            CatalogoRecordatorios.getInstancia().getModelo().actualizar();
            CatalogoRecordatorios catalogoRecordatorios= CatalogoRecordatorios.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(catalogoRecordatorios);
        }
        if (objeto.getSource()== acercaDe2){
            CatalogoAcercaDe catalogoAcercaDe= CatalogoAcercaDe.getInstancia();
            VentanaPrincipal.getInstancia().mdiParent(catalogoAcercaDe);
        }

        

    }
    public void ocultarMenu(){
        iniciarSesion.setVisible(true);
        cerrarSesion.setVisible(false);
        login.setText("Login");       
        salir.setVisible(true);
        Mvendedores.setVisible(false);
        Mclientes.setVisible(false);
        Mrecordatorios.setVisible(false);
        VentanaPrincipal.getInstancia().lblHora.setVisible(false);
        VentanaPrincipal.getInstancia().usuarioC.setVisible(false);
        VentanaLogin.getInstancia().vendedor="";
    }
    public void mostrarMenu(){
        login.setText("Conectado");
        cerrarSesion.setVisible(true);
        iniciarSesion.setVisible(false);
        salir.setVisible(false);
        Mvendedores.setVisible(true);
        Mclientes.setVisible(true);
        Mrecordatorios.setVisible(true);

    }
    public void superUsuario(){            
        salir.setVisible(false);
    }
    public void usuarioNormal(){
        salir.setVisible(false);
        

    }



}
