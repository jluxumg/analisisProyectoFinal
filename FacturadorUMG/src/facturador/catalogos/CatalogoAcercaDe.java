package facturador.catalogos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;

public class CatalogoAcercaDe extends JInternalFrame implements ActionListener{
    private JLabel lblTitulo;
    private JLabel lblTitulo2;
    private JLabel lblTitulo3;
    private JLabel lblEmpresa2;
    private JLabel lblIntegrantes;
    private JLabel luischic;
    private JLabel imagen;
    private JPanel panelVentana;
    private static CatalogoAcercaDe instancia;
    
    public CatalogoAcercaDe(){
        panelVentana = new JPanel();
        panelVentana.setLayout(null);
  

        lblTitulo = new JLabel("Inmobiliaria 1.0");
        lblTitulo.setBounds(270, 35, 180, 20);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 16));
        lblTitulo.setForeground(Color.darkGray);

        lblTitulo2 = new JLabel("Luis Fernando Chic");
        lblTitulo2.setBounds(270, 90, 180, 20);
        lblTitulo2.setFont(new Font("Serif", Font.BOLD, 14));
        lblTitulo2.setForeground(Color.darkGray);

        lblTitulo3 = new JLabel("Creado Por:");
        lblTitulo3.setBounds(270, 65, 180, 20);
        lblTitulo3.setFont(new Font("Serif", Font.BOLD, 14));
        lblTitulo3.setForeground(Color.darkGray);
              

        lblEmpresa2 = new JLabel("Copyright © Todos los derechos reservados");
        lblEmpresa2.setBounds(15, 190, 300, 20);
        lblEmpresa2.setFont(new Font("Serif", Font.BOLD, 14));
        lblEmpresa2.setForeground(Color.darkGray);

        lblIntegrantes = new JLabel("luischic2003@gmail.com");
        lblIntegrantes.setBounds(270, 110, 180, 20);
        lblIntegrantes.setFont(new Font("Serif", Font.BOLD, 14));
        lblIntegrantes.setForeground(Color.darkGray);

        luischic = new JLabel("30617940");
        luischic.setBounds(270, 130, 180, 20);
        luischic.setFont(new Font("Serif", Font.BOLD, 14));
        luischic.setForeground(Color.darkGray);

        
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/io.jpg")));
        imagen.setBounds(55, 30, 130, 130);
        
       
        panelVentana.add(lblEmpresa2);
        panelVentana.add(lblTitulo);
        panelVentana.add(lblTitulo2);
        panelVentana.add(lblTitulo3);
        panelVentana.add(lblIntegrantes);
        panelVentana.add(luischic);
       
        panelVentana.add(imagen);
        panelVentana.setBackground(Color.WHITE);


        this.add(panelVentana);
        this.setTitle("Acerca De Control De Clientes");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,250);
        this.setLocation(300,150);
        this.setResizable(false);
        this.setClosable(true);
        this.setBackground(Color.WHITE);

    }
     public void actionPerformed(ActionEvent objeto){
         
     }
    public static CatalogoAcercaDe getInstancia(){
        if(instancia == null){
            instancia = new CatalogoAcercaDe();
        }
        return instancia;
    }
   
}
