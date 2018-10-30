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
import javax.swing.JOptionPane;
import facturador.beans.Producto;

public class AgregarProducto extends JDialog implements ActionListener {

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

    private static AgregarProducto instancia;

    //Constructor
    public AgregarProducto() {
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
        this.setTitle("Nuevo Producto");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 420);
        this.setLocationRelativeTo(null);

        this.setResizable(false);

        //panelVentana.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent objeto) {
        if (objeto.getSource() == cmdGuardar) {
            int prueba = 0;
            if (txtNombre.getText().length() == 0
                    || txtDescripcion.getText().length() == 0 || txtPrecio.getText().length() == 0
                    || txtCantidadTotal.getText().length() == 0 || txtCantidadVendida.getText().length() == 0) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Hay Datos En Blanco Desea Ingresarlo Asi!", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == 0) {
                    prueba = 1;
                }

            } else {
                prueba = 1;
            }
            if (prueba == 1) {
                JOptionPane.showMessageDialog(null, "Ingresado Exitosamente");
                Producto prd = new Producto();
                prd.setIdProducto(0);
                prd.setNombre(txtNombre.getText().trim());
                prd.setDescripcion(txtDescripcion.getText().trim());
                prd.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                prd.setCantidadTotal(Integer.parseInt(txtCantidadTotal.getText().trim()));
                prd.setCantidadVendida(Integer.parseInt(txtCantidadVendida.getText().trim()));
                prd.setCantidadDisponible(prd.getCantidadTotal() - prd.getCantidadVendida());

                CatalogoProductos.getInstancia().getModelo().agregarProducto(prd);

                instancia.dispose();
                txtNombre.setText("");
                txtDescripcion.setText("");
                txtPrecio.setText("");
                txtCantidadTotal.setText("");
                txtCantidadVendida.setText("");

            }
        }

        if (objeto.getSource() == cmdCancelar) {
            instancia.dispose();
            txtNombre.setText("");
            txtDescripcion.setText("");
            txtPrecio.setText("");
            txtCantidadTotal.setText("");
            txtCantidadVendida.setText("");
            this.setVisible(false);
        }
    }

    public static AgregarProducto getInstancia() {
        if (instancia == null) {
            instancia = new AgregarProducto();
        }
        return instancia;
    }

}
