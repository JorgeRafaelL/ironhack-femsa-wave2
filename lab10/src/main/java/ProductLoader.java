import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Redundant database queries
public class ProductLoader {

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        for (int id = 1; id <= 100; id++) {
            products.add(database.getProductById(id));
        }
        return products;
    }

}

//Código mejorado
public class ProductLoader {
    public List<Product> loadProducts() {
        List<Integer> ids = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList()); //Usar IntStream, genera la lista de IDs de manera más concisa y eficiente
        return database.getProductsByIds(ids); //Consultas en Lote en lugar de buscar por cada id, lo cual minimiza la sobrecarga a la BD
    }
}
