package pe.com.caichihua.backrest.demorestbackend.util.enums;

public enum EstadoType {
    ACTIVO("ACTIVO",1),
    INACTIVO("INACTIVO",0);

    private String nombre;
    private int clave;

    private EstadoType (String nombre, int clave){
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }
}
