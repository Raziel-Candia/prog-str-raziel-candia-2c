public class ticket {

    public ticket(){

    }//Constructor por default

    private final double DESCUENTO = 0.10;
    private final double PRECIO = 10;
    public double subtotal;
    public double descuentoCalculado;
    public double total;

    public void process(int cantidad){
        calcularSubtotal(cantidad);
        calcularDescuento();
        calcularTotal();
    }
    private void calcularSubtotal(int cantidad){
        this.subtotal = cantidad*PRECIO;
    }

    private void calcularDescuento(){
        this.descuentoCalculado = this.subtotal*DESCUENTO;
    }

    private void calcularTotal(){
        this.total = this.subtotal - this.descuentoCalculado;
    }

    public void imprimirTicket(double cantidad){
        System.out.println("---Ticket---");
        System.out.println("La cantidad de articulos fue: "+cantidad);
        System.out.println("El subtotal fue: "+this.subtotal);
        System.out.println("El descuento calculado fue de: "+this.descuentoCalculado);
        System.out.println("El total del ticket es: "+this.total);
        System.out.println("---Ticket---");
    }
}
