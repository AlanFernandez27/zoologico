import java.io.Serializable;

public abstract class Animal implements Serializable {
    private String nombre;
    private int edad;
    private String genero;

    public Animal(String nombre, int edad, String genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public abstract void alimentar();
    public abstract void atender();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [Nombre=" + nombre + ", Edad=" + edad + ", Género=" + genero + "]";
    }
}
