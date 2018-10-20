package facturador.catalogos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import facturador.modelodedatos.ModeloDeDatosRecordatorios;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import facturador.catalogos.CatalogoDetallesCliente;

public class CatalogoRecordatorios extends JInternalFrame implements ActionListener,KeyListener {
    //Cracion de objetos
    private static CatalogoRecordatorios instancia;
    private JPanel panelBotones;
    private JPanel panelVentana;
    private JButton cmdDetalles;
    public JTable tblRecordatorios;
    private JScrollPane scrollTablaRecordatorios;
    private ModeloDeDatosRecordatorios modeloDeDatos;
    private JLabel espacio;

    private JTextField buscar;
    private TableRowSorter<TableModel> modeloOrdenado;
    private JLabel lblBuscar;
    private JLabel imagenBuscar;
   //Creacion del constructor
    public CatalogoRecordatorios(){
        modeloDeDatos = new ModeloDeDatosRecordatorios();
        //modeloOrdenado = new TableRowSorter<TableModel>(modeloDeDatos);
        //modeloDeDatos.buscar = "";
        panelBotones = new JPanel();


        cmdDetalles = new JButton("Ver Detalles",new ImageIcon(getClass().getResource("/Imagenes/verCliente.png")));
        cmdDetalles.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdDetalles.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdDetalles.setMaximumSize(new Dimension(100,55));
        cmdDetalles.setToolTipText("Detalles Recordatorio");
        cmdDetalles.addActionListener(this);

        buscar = new JTextField(125);
        buscar.setBounds(50, 35, 125, 20);
        buscar.addKeyListener(this);

        lblBuscar = new JLabel("Buscar Codigo Cliente");
        lblBuscar.setBounds(50, 5, 200, 20);
        lblBuscar.setFont(new Font("Serif", Font.BOLD, 14));
        lblBuscar.setForeground(Color.darkGray);

        espacio = new JLabel("                                         ");
        espacio.setBounds(10, 5, 200, 20);
        espacio.setFont(new Font("Serif", Font.BOLD, 14));
        espacio.setForeground(Color.darkGray);

        imagenBuscar = new JLabel();
        imagenBuscar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/buscar.png")));
        imagenBuscar.setBounds(190, -20, 130, 130);
        //Agregar Botones al panel
        panelBotones.add(espacio);
        panelBotones.add(cmdDetalles);
        //Parametros de configuracion de JTable

        //Parametros de vista de la ventana Usuario
        this.setTitle("Listado de Recordatorios De Hoy");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Imagenes/iconoClientes.png")));
        this.setSize(800,300);
        this.setLocation(100,100);
        this.setClosable(true);
        this.setResizable(false);
        this.setMaximizable(false);
        this.add(buscar);
        this.add(lblBuscar);
        this.add(imagenBuscar);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Agrego el panel de botones al panel de la ventana
        this.getContentPane().add(panelBotones,BorderLayout.NORTH);
        //Agrego el panel de la tabla usuario
        this.getContentPane().add(getJPanelVentana(),BorderLayout.CENTER);
//        this.setVisible(true);

        //panelVentana.setBackground(Color.lightGray);
       // panelBotones.setBackground(Color.lightGray);
    }
    public void actionPerformed(ActionEvent objeto){
        
        if(objeto.getSource() == cmdDetalles){
            if (tblRecordatorios.getSelectedRow()== -1)
            {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Cliente Para Ver los Detalles");
            }else{
                

            }

        }
    }

    //Agregar el ScroollPane al panel de ventana
    private JPanel getJPanelVentana(){
        if(panelVentana == null){
            panelVentana = new JPanel();
            panelVentana.setLayout(null);
            panelVentana.add(getJScrollPane());
        }
        return panelVentana;
    }

    //Creacion del Scroll Pane
    public JScrollPane getJScrollPane(){
        if(scrollTablaRecordatorios == null){
            scrollTablaRecordatorios = new JScrollPane();
            scrollTablaRecordatorios.setBounds(new Rectangle(20,27,746,150));
            scrollTablaRecordatorios.setViewportView(getTablaRecordatorios());
        }
        return scrollTablaRecordatorios;
    }
    //Metodo para crear JTable e inicializar el modelo de datos
    public JTable getTablaRecordatorios(){
        if(tblRecordatorios == null){
            tblRecordatorios = new JTable();
            tblRecordatorios.setModel(modeloDeDatos);
        }
        return tblRecordatorios;
    }
    public static CatalogoRecordatorios getInstancia(){
        if(instancia == null){
            instancia = new CatalogoRecordatorios();
        }
        return instancia;
    }
    public ModeloDeDatosRecordatorios getModelo(){
        return modeloDeDatos;
    }
    public void actualizarModelo(){
        modeloDeDatos = new ModeloDeDatosRecordatorios();
    }
    public void keyTyped(KeyEvent e) {
       //JOptionPane.showMessageDialog(null,);

      /* this.getModelo().Buscar(buscar.getText());
      this.modeloDeDatos = new ModeloDeDatosUsuario();
        this.updateUI();*/


    }

    public void keyPressed(KeyEvent e) {


    }

    public void keyReleased(KeyEvent e) {
        //modeloOrdenado.setRowFilter(RowFilter.regexFilter(buscar.getText(), 3));
        //tblClientes.setRowSorter(modeloOrdenado);
    }


}
