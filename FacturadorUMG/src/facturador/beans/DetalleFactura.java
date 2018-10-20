package facturador.beans;
public class DetalleFactura {
    public DetalleFactura(){}
    private EncabezadoFactura factura;
    private Integer correlativo;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double precioTotal;

    public DetalleFactura(EncabezadoFactura factura, Integer correlativo, Producto producto, Integer cantidad, Double precioUnitario, Double precioTotal) {
        this.factura = factura;
        this.correlativo = correlativo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }

    public EncabezadoFactura getFactura() {
        return factura;
    }

    public void setFactura(EncabezadoFactura factura) {
        this.factura = factura;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
