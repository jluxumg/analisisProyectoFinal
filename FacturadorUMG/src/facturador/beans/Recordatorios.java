package facturador.beans;

public class Recordatorios {
    public int idSeguimiento;
    public int idCliente;
    public String fecha;
    public String observaciones;
    public String vendedor;

    public Recordatorios(){}

    public Recordatorios(int idSeguimiento, int idCliente, String fecha, String observaciones, String vendedor) {
        this.idSeguimiento = idSeguimiento;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.vendedor = vendedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    

    
}
