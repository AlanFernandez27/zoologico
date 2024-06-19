import javax.swing.JOptionPane;

public class Mamifero extends Animal {
    public Mamifero(String nombre, int edad, String genero) {
        super(nombre, edad, genero);
    }

    @Override
    public void alimentar() {
        JOptionPane.showMessageDialog(null, "El mamífero ya comió frutas y carne.");
    }

    @Override
    public void atender() {
        JOptionPane.showMessageDialog(null, "El mamífero está siendo atendido.");
    }
}
