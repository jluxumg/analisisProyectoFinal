package facturador.catalogos.factura;

import facturador.beans.DetalleFactura;
import facturador.beans.Producto;
import facturador.manejadores.ManejadorDeProductos;
import facturador.modelodedatos.ModeloDeDatosDetalleFacturaTabla;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
    public Integer cantidadDisponible;
    
    private ModeloDeDatosProducto modeloDeDatos;
    
    private JButton cmdGuardar;
    private JButton cmdCancelar;
    
    private static AgregarDetalle instancia;
    DecimalFormat formateador = new DecimalFormat("0.00");
    Producto productoSeleccionado;

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
        cmbProducto.setBounds(290, 35, 500, 20);
        
        cmbProducto.addItem("Seleccione un producto");
        
        for (Producto producto : modeloDeDatos.obtenerListaProductos()) {
            cmbProducto.addItem(producto.getIdProducto() + "-" + producto.getDescripcion().trim());
        }
        cmbProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productoSeleccionado();
            }
        });
        lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Serif", Font.BOLD, 16));
        lblCantidad.setForeground(Color.darkGray);
        lblCantidad.setBounds(175, 70, 250, 20);
        
        txtCantidad = new JTextField();
        txtCantidad.setBounds(290, 70, 50, 20);
        txtCantidad.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        
        txtCantidad.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            
            public void insertUpdate(DocumentEvent e) {
                warn();
            }
            
            public void warn() {
                if (txtCantidad.getText().toString().trim().length() > 0) {
                    if (Integer.parseInt(txtCantidad.getText()) > 0 && Double.parseDouble(txtPrecioUnitario.getText()) > 0) {
                        Double valor = (Integer.parseInt(txtCantidad.getText()) * Double.parseDouble(txtPrecioUnitario.getText()));
                        txtPrecioTotal.setText(formateador.format(valor));
                    }                    
                } else {
                    txtPrecioTotal.setText("0");
                }
            }
        });
        
        lblPrecioUnitario = new JLabel("Precio Unitario");
        lblPrecioUnitario.setFont(new Font("Serif", Font.BOLD, 16));
        lblPrecioUnitario.setForeground(Color.darkGray);
        lblPrecioUnitario.setBounds(175, 105, 250, 20);
        
        txtPrecioUnitario = new JTextField();
        txtPrecioUnitario.setBounds(290, 105, 150, 20);
        
        lblPrecioTotal = new JLabel("Precio Total");
        lblPrecioTotal.setFont(new Font("Serif", Font.BOLD, 16));
        lblPrecioTotal.setForeground(Color.darkGray);
        lblPrecioTotal.setBounds(175, 140, 250, 20);
        
        txtPrecioTotal = new JTextField();
        txtPrecioTotal.setBounds(290, 140, 150, 20);
        txtPrecioTotal.setEnabled(false);
        
        cmdGuardar = new JButton("Guardar", new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Cliente");
        cmdGuardar.setBounds(15, 175, 110, 60);
        cmdGuardar.addActionListener(this);
        
        cmdCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        cmdCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdCancelar.setToolTipText("Cancelar");
        cmdCancelar.setBounds(135, 175, 110, 60);
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
        this.setSize(820, 300);
        this.setLocationRelativeTo(null);
        
        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }
    
    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            int prueba = 0;
            System.out.println("Cantidad " + getCantidadDisponible());
            if (cmbProducto.getSelectedItem().toString().length() == 0
                    || cmbProducto.getSelectedItem().toString().equalsIgnoreCase("Seleccione un producto")
                    || txtCantidad.getText().toString().trim().length() == 0
                    || txtPrecioUnitario.getText().toString().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Hay Datos En Blanco, por favor revisar", "ADVERTENCIA", 1);
            } else if (Integer.parseInt(txtCantidad.getText()) > getCantidadDisponible()) {
                JOptionPane.showMessageDialog(null, "No existe la cantidad disponible solicitada", "ADVERTENCIA", 1);
            } else {
                prueba = 1;
            }
            if (prueba == 1) {
                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                DetalleFactura  detalle = new DetalleFactura();
                detalle.setCorrelativo(0);
                detalle.setProducto(productoSeleccionado);
                detalle.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));
                detalle.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText().toString()));
                detalle.setPrecioTotal(Double.parseDouble(txtPrecioTotal.getText().toString()));
                Facturador.getInstancia().modeloDetalle.agregar(detalle);

                instancia.dispose();
                txtCantidad.setText("");
                txtPrecioTotal.setText("");
                txtPrecioUnitario.setText("");
                cmbProducto.setSelectedItem(null);
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
    
    private void productoSeleccionado() {
        
        System.out.println("RECUEPERA PRECIO");
        if (cmbProducto.getSelectedItem()!= null && !cmbProducto.getSelectedItem().toString().equalsIgnoreCase("Seleccione un producto")) {
            Integer seleccionado = Integer.parseInt(cmbProducto.getSelectedItem().toString().substring(0, cmbProducto.getSelectedItem().toString().indexOf("-")));
            productoSeleccionado = ManejadorDeProductos.getInstancia().obtenerProducto(seleccionado);
            txtPrecioUnitario.setText(formateador.format(productoSeleccionado.getPrecio()));
            cantidadDisponible = productoSeleccionado.getCantidadDisponible();
        }
    }
    
    public static AgregarDetalle getInstancia() {
        if (instancia == null) {
            instancia = new AgregarDetalle();
        }
        return instancia;
    }
    
    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }
    
    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
}
