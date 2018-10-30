package facturador.catalogos.producto;

import facturador.catalogos.cliente.CatalogoClientes;
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
import facturador.beans.Clientes;
import facturador.beans.Producto;

public class ModificarProducto extends JDialog implements ActionListener {

    private final JPanel panelVentana;
    private final JLabel imagen;
    private final JLabel lblNombre;
    private final JLabel lblDescripcion;
    private final JLabel lblPrecio;
    private final JLabel lblCantidadTotal;
    private final JLabel lblCantidadVendida;

    public JTextField txtNombre;
    public JTextField txtDescripcion;
    public JTextField txtPrecio;
    public JTextField txtCantidadTotal;
    public JTextField txtCantidadVendida;

    private final JButton cmdGuardar;
    private final JButton cmdCancelar;

    private int numeroElemento;
    private Producto producto;
    private int idProducto;

    private static ModificarProducto instancia;

    //Constructor
    public ModificarProducto() {
        panelVentana = new JPanel();
        panelVentana.setLayout(null);
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/prd-128.png")));
        imagen.setBounds(15, 10, 130, 130);

        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Serif", Font.BOLD, 16));
        lblNombre.setForeground(Color.darkGray);
        lblNombre.setBounds(175, 10, 250, 20);

        txtNombre = new JTextField();
        txtNombre.setBounds(175, 35, 250, 20);

        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setFont(new Font("Serif", Font.BOLD, 16));
        lblDescripcion.setForeground(Color.darkGray);
        lblDescripcion.setBounds(175, 65, 250, 20);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(175, 85, 250, 20);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Serif", Font.BOLD, 16));
        lblPrecio.setForeground(Color.darkGray);
        lblPrecio.setBounds(175, 115, 250, 20);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(175, 140, 250, 20);

        lblCantidadTotal = new JLabel("Cantidad Total");
        lblCantidadTotal.setFont(new Font("Serif", Font.BOLD, 16));
        lblCantidadTotal.setForeground(Color.darkGray);
        lblCantidadTotal.setBounds(175, 170, 250, 20);

        txtCantidadTotal = new JTextField();
        txtCantidadTotal.setBounds(175, 195, 250, 20);

        lblCantidadVendida = new JLabel("Cantidad Vendida");
        lblCantidadVendida.setFont(new Font("Serif", Font.BOLD, 16));
        lblCantidadVendida.setForeground(Color.darkGray);
        lblCantidadVendida.setBounds(175, 225, 250, 20);

        txtCantidadVendida = new JTextField();
        txtCantidadVendida.setBounds(175, 250, 250, 20);

        cmdGuardar = new JButton("Guardar", new ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        cmdGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        cmdGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        cmdGuardar.setToolTipText("Guardar Producto");
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
        panelVentana.add(lblDescripcion);
        panelVentana.add(lblPrecio);
        panelVentana.add(lblCantidadTotal);
        panelVentana.add(lblCantidadVendida);

        panelVentana.add(txtNombre);
        panelVentana.add(txtDescripcion);
        panelVentana.add(txtPrecio);
        panelVentana.add(txtCantidadTotal);
        panelVentana.add(txtCantidadVendida);

        panelVentana.add(cmdGuardar);
        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Editar Producto");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            getModificarProducto();
            JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
            CatalogoProductos.getInstancia().getModelo().modificarProducto(numeroElemento, producto);
            instancia.dispose();

        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            this.setVisible(false);
        }
    }

    public static ModificarProducto getInstancia(Producto producto, int elemento) {
        if (instancia == null) {
            instancia = new ModificarProducto();
        }
        instancia.setModificarProducto(producto, elemento);
        return instancia;
    }

    public void setModificarProducto(Producto p, int elemento) {
        numeroElemento = elemento;
        idProducto = p.getIdProducto();
        txtNombre.setText(p.getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtPrecio.setText(p.getPrecio().toString());
        txtCantidadTotal.setText(p.getCantidadTotal().toString());
        txtCantidadVendida.setText(p.getCantidadVendida().toString());

    }

    public void getModificarProducto() {
        producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setNombre(txtNombre.getText().trim());
        producto.setDescripcion(txtDescripcion.getText().trim());
        producto.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
        producto.setCantidadTotal(Integer.parseInt(txtCantidadTotal.getText().trim()));
        producto.setCantidadVendida(Integer.parseInt(txtCantidadVendida.getText().trim()));
        producto.setCantidadDisponible(producto.getCantidadTotal() - producto.getCantidadVendida());

    }

    public void llenar() {

        idProducto = Integer.parseInt(CatalogoProductos.getInstancia().tblProductos.getValueAt(CatalogoProductos.getInstancia().tblProductos.getSelectedRow(), 0).toString());
        txtNombre.setText(CatalogoProductos.getInstancia().tblProductos.getValueAt(CatalogoProductos.getInstancia().tblProductos.getSelectedRow(), 1).toString());
        txtPrecio.setText(CatalogoProductos.getInstancia().tblProductos.getValueAt(CatalogoProductos.getInstancia().tblProductos.getSelectedRow(), 2).toString());
        txtCantidadTotal.setText(CatalogoProductos.getInstancia().tblProductos.getValueAt(CatalogoProductos.getInstancia().tblProductos.getSelectedRow(), 3).toString());
        txtDescripcion.setText(CatalogoProductos.getInstancia().tblProductos.getValueAt(CatalogoProductos.getInstancia().tblProductos.getSelectedRow(), 5).toString());
        txtCantidadVendida.setText(CatalogoProductos.getInstancia().tblProductos.getValueAt(CatalogoProductos.getInstancia().tblProductos.getSelectedRow(), 6).toString());

    }
}
