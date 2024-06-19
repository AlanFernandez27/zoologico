import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    private static Zoo zoo = new Zoo();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zoológico");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel panel = new JPanel(new BorderLayout());

            // Panel para las opciones
            JPanel optionsPanel = new JPanel(new GridLayout(7, 1));
            JButton btnAgregar = new JButton("1. Agregar Animal");
            JButton btnVer = new JButton("2. Ver Animales");
            JButton btnBuscar = new JButton("3. Buscar Animal");
            JButton btnAlimentar = new JButton("4. Alimentar Animal");
            JButton btnAtender = new JButton("5. Atender Animal");
            JButton btnEliminar = new JButton("6. Eliminar Animal");
            JButton btnSalir = new JButton("7. Salir");

            optionsPanel.add(btnAgregar);
            optionsPanel.add(btnVer);
            optionsPanel.add(btnBuscar);
            optionsPanel.add(btnAlimentar);
            optionsPanel.add(btnAtender);
            optionsPanel.add(btnEliminar);
            optionsPanel.add(btnSalir);

            // Panel para el contenido
            JPanel contentPanel = new JPanel();

            panel.add(optionsPanel, BorderLayout.WEST);
            panel.add(contentPanel, BorderLayout.CENTER);

            // Acciones de los botones
            btnAgregar.addActionListener(e -> agregarAnimal());
            btnVer.addActionListener(e -> verAnimales());
            btnBuscar.addActionListener(e -> buscarAnimal());
            btnAlimentar.addActionListener(e -> alimentarAnimal());
            btnAtender.addActionListener(e -> atenderAnimal());
            btnEliminar.addActionListener(e -> eliminarAnimal());
            btnSalir.addActionListener(e -> System.exit(0));

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static void agregarAnimal() {
        JTextField nombreField = new JTextField();
        JTextField edadField = new JTextField();
        JTextField generoField = new JTextField();

        JComboBox<String> tipoBox = new JComboBox<>(new String[]{"Mamífero", "Ave", "Reptil"});

        Object[] message = {
                "Tipo:", tipoBox,
                "Nombre:", nombreField,
                "Edad:", edadField,
                "Género:", generoField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Agregar Animal", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String tipo = (String) tipoBox.getSelectedItem();
            String nombre = nombreField.getText();
            int edad = Integer.parseInt(edadField.getText());
            String genero = generoField.getText();

            Animal animal = null;
            switch (tipo) {
                case "Mamífero":
                    animal = new Mamifero(nombre, edad, genero);
                    break;
                case "Ave":
                    animal = new Ave(nombre, edad, genero);
                    break;
                case "Reptil":
                    animal = new Reptil(nombre, edad, genero);
                    break;
            }
            if (animal != null) {
                zoo.agregarAnimal(animal);
                JOptionPane.showMessageDialog(null, "Animal agregado correctamente.");
            }
        }
    }

    private static void verAnimales() {
        List<Animal> animales = zoo.verAnimales();
        StringBuilder sb = new StringBuilder();
        for (Animal animal : animales) {
            sb.append(animal).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Animales", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void buscarAnimal() {
        String[] opciones = {"Nombre", "Tipo"};
        String criterio = (String) JOptionPane.showInputDialog(null, "Buscar por:", "Buscar Animal", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (criterio != null) {
            if (criterio.equals("Nombre")) {
                String nombre = JOptionPane.showInputDialog("Nombre:");
                Animal animal = zoo.buscarAnimalPorNombre(nombre);
                if (animal != null) {
                    JOptionPane.showMessageDialog(null, animal.toString(), "Animal Encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Animal no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (criterio.equals("Tipo")) {
                String[] tipos = {"Mamífero", "Ave", "Reptil"};
                String tipo = (String) JOptionPane.showInputDialog(null, "Tipo:", "Buscar Animal", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
                if (tipo != null) {
                    List<Animal> animales = zoo.buscarAnimalPorTipo(getClassForTipo(tipo));
                    StringBuilder sb = new StringBuilder();
                    for (Animal animal : animales) {
                        sb.append(animal).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString(), "Animales Encontrados", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private static Class<? extends Animal> getClassForTipo(String tipo) {
        switch (tipo) {
            case "Mamífero":
                return Mamifero.class;
            case "Ave":
                return Ave.class;
            case "Reptil":
                return Reptil.class;
            default:
                return Animal.class;
        }
    }

    private static void alimentarAnimal() {
        String nombre = JOptionPane.showInputDialog("Nombre del animal:");
        Animal animal = zoo.buscarAnimalPorNombre(nombre);
        if (animal != null) {
            animal.alimentar();
        } else {
            JOptionPane.showMessageDialog(null, "Animal no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atenderAnimal() {
        String nombre = JOptionPane.showInputDialog("Nombre del animal:");
        Animal animal = zoo.buscarAnimalPorNombre(nombre);
        if (animal != null) {
            animal.atender();
        } else {
            JOptionPane.showMessageDialog(null, "Animal no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void eliminarAnimal() {
        String nombre = JOptionPane.showInputDialog("Nombre del animal:");
        Animal animal = zoo.buscarAnimalPorNombre(nombre);
        if (animal != null) {
            zoo.eliminarAnimal(animal);
            JOptionPane.showMessageDialog(null, "Animal eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Animal no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
