package facturador.beans;

import java.util.Date;

public class EncabezadoFactura {

    public EncabezadoFactura() {
    }
    private Integer idFactura;
    private Clientes cliente;
    private Usuario usuario;
    private String direccionFactura;
    private String estado;
    private String documentoFiscal;
    private Double totalFactura;
    private Date fecha;

    public EncabezadoFactura(Integer idFactura, Clientes cliente, Usuario usuario, String direccionFactura, String estado, String documentoFiscal, Double totalFactura, Date fecha) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.usuario = usuario;
        this.direccionFactura = direccionFactura;
        this.estado = estado;
        this.documentoFiscal = documentoFiscal;
        this.totalFactura = totalFactura;
        this.fecha = fecha;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDireccionFactura() {
        return direccionFactura;
    }

    public void setDireccionFactura(String direccionFactura) {
        this.direccionFactura = direccionFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(String documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

    public Double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
