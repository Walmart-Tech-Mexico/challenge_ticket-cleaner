import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Reto para el desarrollador:
        // 1. Implementar la función 'processTicketLines' para analizar las líneas del ticket
        //    y crear una lista de objetos 'TicketItem'.
        // 2. Implementar la función 'filterByCategory' para filtrar la lista de 'TicketItem'
        //    por una categoría específica.
        // 3. Implementar la función 'calculateTotal' para calcular el precio total de los
        //    elementos filtrados.

        String[] ticketLines = {
                "ProductoA,Electrónicos,50.00",
                "ProductoB,Ropa,25.00",
                "ProductoC,Electrónicos,100.00",
                "ProductoD,Alimentos,10.00",
                "ProductoE,Ropa,30.00"
        };

        String categoryToFilter = "Electrónicos";

        // Procesa las líneas del ticket
        List<TicketItem> items = processTicketLines(ticketLines);

        // Filtra por categoría
        List<TicketItem> filteredItems = filterByCategory(items, categoryToFilter);

        // Calcula el total
        double total = calculateTotal(filteredItems);

        System.out.println("Total de " + categoryToFilter + ": " + total);
    }

    // TODO: Implementar esta función
    public static List<TicketItem> processTicketLines(String[] ticketLines) {
        return Stream.of(ticketLines).map(line->{
            List<String> split = Arrays.stream(line.split(",")).toList();
            return new TicketItem(split.get(0),split.get(1),Double.parseDouble(split.get(2)));
        }).collect(Collectors.toList());
    }

    // TODO: Implementar esta función
    public static List<TicketItem> filterByCategory(List<TicketItem> items, String category) {
        if(category==null || category.isEmpty()){
            System.out.println("Categoria no valida");
            return new ArrayList<>();
        }
        return items.stream().filter(item->item.getCategoria().equals(category)).toList();
    }

    // TODO: Implementar esta función
    public static double calculateTotal(List<TicketItem> items) {
        return items.stream().mapToDouble(item->item.getPrecio()).sum();
    }
}

// Clase para representar un elemento del ticket
class TicketItem {
    private String producto;
    private String categoria;
    private double precio;

    public TicketItem(String producto, String categoria, double precio) {
        this.producto = producto;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto: " + producto + ", Categoría: " + categoria + ", Precio: " + precio;
    }
}