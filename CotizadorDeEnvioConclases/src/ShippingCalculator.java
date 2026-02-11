public class ShippingCalculator {
    // Calcular el subtotal del envío según las reglas del negocio
    public double calcularSubtotal(double pesoKg, int distanciaKm, int tipoServicio, boolean zonaRemota) {
        double subtotal = 0;
        // Costo base según el tipo de servicio
        if (tipoServicio == 1) { // Estándar
            subtotal = 50;
        } else { // Express
            subtotal = 90;
        }
        // Costo adicional por peso
        subtotal = subtotal + (pesoKg * 12);
        // Costo adicional por distancia
        if (distanciaKm <= 50) {
            subtotal = subtotal + 20;
        } else if (distanciaKm <= 200) {
            subtotal = subtotal + 60;
        } else {
            subtotal = subtotal + 120;
        }
        // Recargo si la zona es remota
        if (zonaRemota == true) {
            subtotal = subtotal * 1.10;
        }
        return subtotal;
    }
    // Calcula el IVA a partir del subtotal
    public double calcularIVA(double subtotal) {
        return subtotal * 0.16;
    }
    // Calcula el total sumando subtotal e IVA
    public double calcularTotal(double subtotal, double iva) {
        return subtotal + iva;
    }
}