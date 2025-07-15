import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
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

    // Convierte las líneas del ticket en objetos TicketItem
    public static List<TicketItem> processTicketLines(String[] ticketLines) {
        List<TicketItem> items = new ArrayList<>();
        for (String line : ticketLines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String producto = parts[0].trim();
                String categoria = parts[1].trim();
                double precio = Double.parseDouble(parts[2].trim());
                items.add(new TicketItem(producto, categoria, precio));
            }
        }
        return items;
    }

    // Filtra los TicketItem por categoría
    public static List<TicketItem> filterByCategory(List<TicketItem> items, String category) {
        return items.stream()
                .filter(item -> item.getCategoria().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Suma los precios de los TicketItem
    public static double calculateTotal(List<TicketItem> items) {
        return items.stream()
                .mapToDouble(TicketItem::getPrecio)
                .sum();
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