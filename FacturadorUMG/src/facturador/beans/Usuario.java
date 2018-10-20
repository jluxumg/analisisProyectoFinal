package facturador.beans;
public class Usuario {
    public Usuario(){}
    
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;
    private String estado;
    private String rol;

    public Usuario(String usuario, String nombre, String apellido, String password, String estado, String rol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
