public class Validaciones {

    // Checamos que el número decimal esté entre los límites que nos pasen
    public static boolean esDoubleEnRango(double valor, double minimo, double maximo) {
        // Tiene que ser mayor o igual al mínimo Y menor o igual al máximo
        boolean estaDentro = (valor >= minimo) && (valor <= maximo);
        return estaDentro;
    }


    // Valida que el número entero no se salga del rango permitido
    public static boolean esIntEnRango(int valor, int min, int max) {
        // Misma idea que con los decimales, pero para enteros
        return valor >= min && valor <= max;
    }


    // Verificamos que la entrada sea un booleano válido (solo true o false, sin importar mayúsculas)
    public static boolean esBooleanValido(String entrada) {
        // Primero quitamos espacios de más por si el usuario los puso sin querer
        String entradaLimpia = entrada.trim();

        // Comparamos sin distinguir mayúsculas/minúsculas
        if (entradaLimpia.equalsIgnoreCase("true") || entradaLimpia.equalsIgnoreCase("false")) {
            return true;
        } else {
            return false;
        }
    }
}