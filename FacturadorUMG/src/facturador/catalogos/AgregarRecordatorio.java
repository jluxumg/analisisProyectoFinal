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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import facturador.beans.Clientes;
import facturador.db.Conexion;
import facturador.ventanas.VentanaLogin;
import facturador.ventanas.VentanaPrincipal;

public class AgregarRecordatorio extends JDialog implements ActionListener{

    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblCliente;
    private JLabel lblFecha;
    private JLabel lblObservaciones;
    private JLabel lblVendedor;    
    
    public JTextField txtCliente;
    public JTextField txtFecha;
    public JTextArea txtObservaciones;
    public JTextField txtVendedor;

    private JButton cmdGuardar;
    private JButton cmdCancelar;

    private JButton fecha;


    private static AgregarRecordatorio instancia;
    //Constructor
    public AgregarRecordatorio(){
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/nota.png")));
        imagen.setBounds(15, 10, 130, 130);
        
        lblCliente = new JLabel("Cliente");
        lblCliente.setFont(new Font("Serif", Font.BOLD, 16));
        lblCliente.setForeground(Color.darkGray);
        lblCliente.setBounds(175, 10, 100, 20);

        txtCliente = new JTextField();
        txtCliente.setBounds(175, 35, 200, 20);
        txtCliente.setEditable(false);

        lblVendedor = new JLabel("Vendedor");
        lblVendedor.setFont(new Font("Serif", Font.BOLD, 16));
        lblVendedor.setForeground(Color.darkGray);
        lblVendedor.setBounds(175, 60, 250, 20);

        txtVendedor = new JTextField();
        txtVendedor.setBounds(175, 85, 175, 20);
        txtVendedor.setEditable(false);

        lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Serif", Font.BOLD, 16));
        lblFecha.setForeground(Color.darkGray);
        lblFecha.setBounds(175, 125, 250, 20);

        txtFecha = new JTextField();
        txtFecha.setBounds(100, 150, 200, 20);
        txtFecha.setEditable(false);

        lblObservaciones = new JLabel("Observaciones");
        lblObservaciones.setFont(new Font("Serif", Font.BOLD, 16));
        lblObservaciones.setForeground(Color.darkGray);
        lblObservaciones.setBounds(150, 175, 250, 20);

        txtObservaciones = new JTextArea();
        txtObservaciones.setBounds(50, 200, 300, 60);     
       
        cmdGuardar = new JButton("Guardar",new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Seguimiento al Cliente");
        cmdGuardar.setBounds(80, 280, 110, 60);
        cmdGuardar.addActionListener(this);

        cmdCancelar = new JButton("Cancelar",new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(210, 280, 110, 60);
        cmdCancelar.addActionListener(this);

        fecha = new JButton("Fecha",new ImageIcon(getClass().getResource("/Imagenes/calendario.png")));
        fecha.setVerticalTextPosition(SwingConstants.BOTTOM);
        fecha.setHorizontalTextPosition(SwingConstants.CENTER);
        fecha.setToolTipText("Seleccionar Fecha");
        fecha.setBounds(310, 135, 110, 60);
        fecha.addActionListener(this);


        panelVentana.add(imagen);

        panelVentana.add(lblCliente);
        panelVentana.add(lblFecha);
        panelVentana.add(lblObservaciones);
        panelVentana.add(lblVendedor);
        

        panelVentana.add(txtCliente);
        panelVentana.add(txtObservaciones);
        panelVentana.add(txtFecha);
        panelVentana.add(txtVendedor);
        
        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);
        panelVentana.add(fecha);

        this.add(panelVentana);
        this.setTitle("Agregar Seguimiento");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(450,400);
        this.setLocationRelativeTo(null);


        this.setResizable(false);

       
    }

    public void actionPerformed(ActionEvent objeto){
        if(objeto.getSource() == cmdGuardar){
            if (txtFecha.getText().length() == 0 || txtObservaciones.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "No Puede Agregar Datos En Blanco");
            }else{
                Conexion.getInstancia().ejecutarSentencia("insert into seguimiento(idCliente,fecha,observaciones,vendedor)values("+CatalogoDetallesCliente.getInstancia().txtCodigo.getText()+",'"+txtFecha.getText()+"','"+txtObservaciones.getText()+"','"+txtVendedor.getText()+"')");
                JOptionPane.showMessageDialog(null, "Seguimiento Ingresado Exitosamente!");
                instancia.dispose();
                vaciar();
                this.setVisible(false);
            }


        }

        if(objeto.getSource()==cmdCancelar){
            instancia.dispose();
            vaciar();
            this.setVisible(false);

        }
        if(objeto.getSource()==fecha){
            Calendario calendario = Calendario.getInstancia();
            calendario.setVisible(true);
        }
    }
    public static AgregarRecordatorio getInstancia(){
        if(instancia == null){
            instancia = new AgregarRecordatorio();
        }
        return instancia;
    }
    public void llenar(){
        txtVendedor.setText(CatalogoDetallesCliente.getInstancia().cmbVendedor.getText());
        txtCliente.setText(CatalogoDetallesCliente.getInstancia().txtCliente.getText());
    }
    public void vaciar(){
        txtCliente.setText("");
        txtObservaciones.setText("");
        txtFecha.setText("");
        txtVendedor.setText("");
    }
}
