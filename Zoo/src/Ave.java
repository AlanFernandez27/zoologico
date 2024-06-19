import javax.swing.JOptionPane;

public class Ave extends Animal {
    public Ave(String nombre, int edad, String genero) {
        super(nombre, edad, genero);
    }

    @Override
    public void alimentar() {
        JOptionPane.showMessageDialog(null, "El ave ya comió sus semillas e insectos.");
    }

    @Override
    public void atender() {
        JOptionPane.showMessageDialog(null, "El ave está siendo atendida.");
    }
}
