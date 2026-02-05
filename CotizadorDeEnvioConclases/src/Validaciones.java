public class Validaciones {

    // para checar si esta en el limite
    public static boolean esDoubleEnRango(double valor, double minimo, double maximo) {
        // para ver si es mayoor o igual
        boolean estaDentro = (valor >= minimo) && (valor <= maximo);
        return estaDentro;
    }


    // para ver si esta en el limite que pide
    public static boolean esIntEnRango(int valor, int min, int max) {
        // esta es para los enteros la otra era de decimales
        return valor >= min && valor <= max;
    }


    // aqui metemos un verdadero o falso pero en inglés, un booleano
    public static boolean esBooleanValido(String entrada) {

        // por si ponen mayusculas y minusculas para que no marque error
        if (entradaLimpia.equalsIgnoreCase("true") || entradaLimpia.equalsIgnoreCase("false")) {
            return true;
        } else {
            return false;
        }
    }
}