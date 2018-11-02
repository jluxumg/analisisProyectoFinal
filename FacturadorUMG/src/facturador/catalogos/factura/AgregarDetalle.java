package facturador.catalogos.factura;


import facturador.beans.Producto;
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
import facturador.modelodedatos.ModeloDeDatosProducto;

public class AgregarDetalle extends JDialog implements ActionListener {

    private JPanel panelVentana;
    private JLabel imagen;
    private JLabel lblProducto;
    private JLabel lblCantidad;
    private JLabel lblPrecioUnitario;
    private JLabel lblPrecioTotal;    

    public JComboBox cmbProducto;
    public JTextField txtCantidad;
    public JTextField txtPrecioUnitario;
    public JTextField txtPrecioTotal;
    
    private ModeloDeDatosProducto modeloDeDatos;

    private JButton cmdGuardar;
    private JButton cmdCancelar;

    private static AgregarDetalle instancia;

    //Constructor
    public AgregarDetalle() {
        modeloDeDatos = new ModeloDeDatosProducto();
        panelVentana = new JPanel();
        panelVentana.setLayout(null);

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/producto.png")));
        imagen.setBounds(15, 10, 130, 130);

        lblProducto = new JLabel("Producto");
        lblProducto.setFont(new Font("Serif", Font.BOLD, 16));
        lblProducto.setForeground(Color.darkGray);
        lblProducto.setBounds(175, 35, 100, 20);

        cmbProducto = new JComboBox();
        cmbProducto.setBounds(250, 35, 700, 20);
        cmbProducto.addItem("Seleccione un producto");

        for (Producto producto : modeloDeDatos.obtenerListaProductos()) {
            cmbProducto.addItem(producto.getIdProducto()+"-"+producto.getDescripcion().trim());
        }

        lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Serif", Font.BOLD, 16));
        lblCantidad.setForeground(Color.darkGray);
        lblCantidad.setBounds(175, 70, 250, 20);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(250, 70, 700, 20);

        lblPrecioUnitario = new JLabel("Estado");
        lblPrecioUnitario.setFont(new Font("Serif", Font.BOLD, 16));
        lblPrecioUnitario.setForeground(Color.darkGray);
        lblPrecioUnitario.setBounds(175, 105, 250, 20);

        txtPrecioUnitario = new JTextField();
        txtPrecioUnitario.setBounds(250, 105, 300, 20);
        
        lblPrecioTotal = new JLabel("Precio Total");
        lblPrecioTotal.setFont(new Font("Serif", Font.BOLD, 16));
        lblPrecioTotal.setForeground(Color.darkGray);
        lblPrecioTotal.setBounds(175, 140, 250, 20);

        txtPrecioTotal = new JTextField();
        txtPrecioTotal.setBounds(250, 140, 150, 20);
        txtPrecioTotal.setEnabled(false);

        cmdGuardar = new JButton("Guardar", new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Cliente");
        cmdGuardar.setBounds(15, 560, 110, 60);
        cmdGuardar.addActionListener(this);

        cmdCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(135, 560, 110, 60);
        cmdCancelar.addActionListener(this);
      

        panelVentana.add(imagen);

        panelVentana.add(lblProducto);
        panelVentana.add(lblCantidad);
        panelVentana.add(lblPrecioUnitario);
        panelVentana.add(lblPrecioTotal);
        panelVentana.add(cmbProducto);
        panelVentana.add(txtCantidad);
        panelVentana.add(txtPrecioUnitario);
        panelVentana.add(txtPrecioTotal);
        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Nuevo Detalle");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000, 675);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            int prueba = 0;
            if (cmbProducto.getSelectedItem().toString().length() == 0
                    || cmbProducto.getSelectedItem().toString().equalsIgnoreCase("Seleccione un producto")
                    || txtCantidad.getText().toString().trim().length() == 0
                    || txtPrecioUnitario.getText().toString().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "Hay Datos En Blanco, por favor revisar", "ADVERTENCIA", 1);
            } else {
                prueba = 1;
            }
            if (prueba == 1) {

                /*Clientes cliente = new Clientes();
                String clienteString = cmbCliente.getSelectedItem().toString().substring(0, cmbCliente.getSelectedItem().toString().indexOf("-"));
                cliente = ManejadorDeClientes.getInstancia().BuscarClienteID(Integer.parseInt(clienteString.trim()));

                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                EncabezadoFactura ef = new EncabezadoFactura();
                ef.setIdFactura(0);
                ef.setCliente(cliente);
                ef.setDireccionFactura(txtDireccion.getText().trim());
                ef.setDocumentoFiscal(" ");
                ef.setEstado(cmbEstado.getSelectedItem().toString().substring(0, cmbEstado.getSelectedItem().toString().indexOf("-")));
                Usuario usuario = new Usuario();
                usuario.setUsuario("jlux");
                System.out.println("Usuario " + VentanaLogin.getInstancia().userSession.getUsuario());
                ef.setUsuario(VentanaLogin.getInstancia().userSession);
                Double total = new Double(0);

                ef.setTotalFactura(total);

                CatalogoFacturas.getInstancia().getModelo().agregarFactura(ef);

                instancia.dispose();
                instancia.dispose();
                txtDireccion.setText("");
                cmbEstado.setSelectedItem(null);
                cmbCliente.setSelectedItem(null);*/
            }
        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            txtPrecioTotal.setText("0");
            txtPrecioUnitario.setText("0");
            txtCantidad.setText("0");
            cmbProducto.setSelectedItem(null);
            this.setVisible(false);
        }
    }

    public static AgregarDetalle getInstancia() {
        if (instancia == null) {
            instancia = new AgregarDetalle();
        }
        return instancia;
    }
}
