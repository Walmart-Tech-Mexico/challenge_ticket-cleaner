import java.util.ArrayList;
import java.util.List;

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
                "ProductoE,Ropa,30.00",
                "ProductoF,Ropa",
                "ProductoG,,10.0",
                "ProductoH,Electrónicos,Precio",
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

    public static List<TicketItem> processTicketLines(String[] ticketLines) {
        List<TicketItem> items = new ArrayList<>();

        for (String line : ticketLines) {
            String[] fields = line.split(",");

            if (fields.length != 3) {
                System.out.println("Error: Línea no válida: " + line);
                continue;
            }

            String producto = fields[0];
            String categoria = fields[1];
            double precio = 0;

            try {
                precio = Double.parseDouble(fields[2]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Precio no válido en la línea: " + line);
                continue;
            }

            items.add(new TicketItem(producto, categoria, precio));
        }

        return items;
    }

    public static List<TicketItem> filterByCategory(List<TicketItem> items, String category) {
        List<TicketItem> filteredItems = items.stream().filter(i -> i.getCategoria().equals(category)).toList();
        return filteredItems;
    }

    public static double calculateTotal(List<TicketItem> items) {
        return items.stream().mapToDouble(i -> i.getPrecio()).sum();
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