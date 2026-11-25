public class Ubicacion
{
    private String nombre;
    private int distancia; 

    public Ubicacion(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return "Ubicación: " + nombre + " | Distancia: " + distancia + " metros";
    }
}

