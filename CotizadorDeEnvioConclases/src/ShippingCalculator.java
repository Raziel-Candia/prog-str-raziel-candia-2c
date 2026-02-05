public class ShippingCalculator {

    // para ver el costo antes del IVA
    public double calcularSubtotal(double pesoKg, int distanciaKm, int tipoServicio, boolean esZonaRemota) {
        // los costos segun el tipo de envio
        double costoBase;
        if (tipoServicio == 1) {
            costoBase = 50; // estandar
        } else {
            costoBase = 90; // expres
        }

        // este va a ser el costo por kilo
        double costoPorPeso = pesoKg * 12;

        // este es el costo de la distancia depende cuanto
        double costoPorDistancia;
        if (distanciaKm <= 50) {
            costoPorDistancia = 20; // este es el precio mas bajo
        }
        else if (distanciaKm <= 200) {
            costoPorDistancia = 60; // este es el de 51 kilometros
        }
        else {
            costoPorDistancia = 120; // y este es el mas caro
        }

        // aqui sumamos toso
        double subtotalBase = costoBase + costoPorPeso + costoPorDistancia;

        // aqui tomamos en cuenta si es zona remota u le agregamos lo que pide
        double subtotalFinal;
        if (esZonaRemota) {
            subtotalFinal = subtotalBase * 1.10;
        } else {
            subtotalFinal = subtotalBase;
        }

        return subtotalFinal;
    }


    // aqui calculmpos el iva
    public double calcularIVA(double subtotal) {
        double ivaCalculado = subtotal * 0.16;
        return ivaCalculado;
    }


    // aqui ponemos el subtotal y el iva
    public double calcularTotal(double subtotal, double iva) {
        // y puees aqui solo lo simas y ya
        return subtotal + iva;
    }
}
