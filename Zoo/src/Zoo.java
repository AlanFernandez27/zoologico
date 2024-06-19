import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<Animal> animales;
    private static final String DATA_FILE = "zoologico.dat";

    public Zoo() {
        animales = new ArrayList<>();
        cargarDatos();
    }

    public void agregarAnimal(Animal animal) {
        animales.add(animal);
        guardarDatos();
    }

    public List<Animal> verAnimales() {
        return animales;
    }

    public Animal buscarAnimalPorNombre(String nombre) {
        for (Animal animal : animales) {
            if (animal.getNombre().equalsIgnoreCase(nombre)) {
                return animal;
            }
        }
        return null;
    }

    public List<Animal> buscarAnimalPorTipo(Class<? extends Animal> tipo) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animales) {
            if (animal.getClass() == tipo) {
                result.add(animal);
            }
        }
        return result;
    }

    public void eliminarAnimal(Animal animal) {
        animales.remove(animal);
        guardarDatos();
    }

    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(animales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            animales = (List<Animal>) ois.readObject();
        } catch (FileNotFoundException e) {
            animales = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
