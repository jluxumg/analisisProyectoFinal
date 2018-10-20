package facturador.beans;
public class TipoDocumento {
    public TipoDocumento(){}
    
    private String idTipoDocumento;
    private String descripcion;

    public TipoDocumento(String idTipoDocumento, String descripcion) {
        this.idTipoDocumento = idTipoDocumento;
        this.descripcion = descripcion;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
