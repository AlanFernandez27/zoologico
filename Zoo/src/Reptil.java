import javax.swing.JOptionPane;

public class Reptil extends Animal {
    public Reptil(String nombre, int edad, String genero) {
        super(nombre, edad, genero);
    }

    @Override
    public void alimentar() {
        JOptionPane.showMessageDialog(null, "El reptil ya comió insectos y animales pequeños.");
    }

    @Override
    public void atender() {
        JOptionPane.showMessageDialog(null, "El reptil está siendo atendido.");
    }
}
