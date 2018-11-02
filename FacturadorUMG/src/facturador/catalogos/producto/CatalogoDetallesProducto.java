package facturador.catalogos.producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class CatalogoDetallesProducto extends JDialog implements ActionListener {

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

    private JButton cmdCancelar;

    private static CatalogoDetallesProducto instancia;

    //Constructor
    public CatalogoDetallesProducto() {
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
        txtNombre.setEditable(false);

        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setFont(new Font("Serif", Font.BOLD, 16));
        lblDescripcion.setForeground(Color.darkGray);
        lblDescripcion.setBounds(175, 65, 250, 20);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(175, 85, 250, 20);
        txtDescripcion.setEditable(false);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Serif", Font.BOLD, 16));
        lblPrecio.setForeground(Color.darkGray);
        lblPrecio.setBounds(175, 115, 250, 20);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(175, 140, 250, 20);
        txtPrecio.setEditable(false);

        lblCantidadTotal = new JLabel("Cantidad Total");
        lblCantidadTotal.setFont(new Font("Serif", Font.BOLD, 16));
        lblCantidadTotal.setForeground(Color.darkGray);
        lblCantidadTotal.setBounds(175, 170, 250, 20);

        txtCantidadTotal = new JTextField();
        txtCantidadTotal.setBounds(175, 195, 250, 20);
        txtCantidadTotal.setEditable(false);

        lblCantidadVendida = new JLabel("Cantidad Vendida");
        lblCantidadVendida.setFont(new Font("Serif", Font.BOLD, 16));
        lblCantidadVendida.setForeground(Color.darkGray);
        lblCantidadVendida.setBounds(175, 225, 250, 20);

        txtCantidadVendida = new JTextField();
        txtCantidadVendida.setBounds(175, 250, 250, 20);
        txtCantidadVendida.setEditable(false);

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

        panelVentana.add(cmdCancelar);

        this.add(panelVentana);
        this.setTitle("Detalles De Producto");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            this.setVisible(false);
        }
    }

    public static CatalogoDetallesProducto getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoDetallesProducto();
        }
        return instancia;
    }

    public void llenar() {

        txtNombre.setText(CatalogoProducto.getInstancia().tblProductos.getValueAt(CatalogoProducto.getInstancia().tblProductos.getSelectedRow(), 1).toString());
        txtPrecio.setText(CatalogoProducto.getInstancia().tblProductos.getValueAt(CatalogoProducto.getInstancia().tblProductos.getSelectedRow(), 2).toString());
        txtCantidadTotal.setText(CatalogoProducto.getInstancia().tblProductos.getValueAt(CatalogoProducto.getInstancia().tblProductos.getSelectedRow(), 3).toString());
        txtDescripcion.setText(CatalogoProducto.getInstancia().tblProductos.getValueAt(CatalogoProducto.getInstancia().tblProductos.getSelectedRow(), 5).toString());
        txtCantidadVendida.setText(CatalogoProducto.getInstancia().tblProductos.getValueAt(CatalogoProducto.getInstancia().tblProductos.getSelectedRow(), 6).toString());
    }
}
