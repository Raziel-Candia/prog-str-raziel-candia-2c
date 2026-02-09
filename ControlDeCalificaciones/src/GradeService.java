public class GradeService {

    // aqui vas a calcular el promedio de los tres parciales
    public double calcularPromedio(double p1, double p2, double p3) {
        return (p1 + p2 + p3) / 3;
    }

    // aqui calculamos la calificacion final (70% promedio + 30% asistencia)
    public double calcularFinal(double promedio, int asistencia) {
        return (promedio * 0.7) + (asistencia * 0.3);
    }

    // aqui pones el estado del alumno depende de lo que puso el prfe en las reglas
    public String determinarEstado(double calificacionFinal, int asistencia, boolean entregaProyecto) {
        // aqui primero pones el de la asistencia para ver si pasa
        if (asistencia < 80) {
            return "lo siento reprobaste por asistencia:(";
        }

        // aqui compruebas si hizo el proyecto o no
        if (!entregaProyecto) {
            return "reprobaste por no entregar el proyecto:(";
        }

        // aqui si paso las dos reglas pues si pasa
        if (calificacionFinal >= 70) {
            return "APROBADOOOO:)";
        } else {
            return "lo siento, reprobado por calificación:(";
        }
    }
}