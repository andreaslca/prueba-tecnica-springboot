package com.example.pruebaTecnicaSpringboot.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FechaUtils {

    //para asegurarse de que hay fecha de salida y de llegada y de que la de llegada es posterior a la de salida
    public static boolean fechasVuelo(LocalDate fechaSalida, LocalDate fechaLlegada) {
        if (fechaSalida == null) {
            return false;
        }
        if (fechaLlegada == null) {
            return false;
        }
        if (fechaLlegada.isBefore(fechaSalida)) {
            return false;
        }
        return true;
    }

    //calcula días de duración del vuelo
    public static long duracionVuelo(LocalDate fechaSalida, LocalDate fechaLlegada) {
        if (fechaSalida == null) {
            return 0;
        }
        if (fechaLlegada == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(fechaSalida, fechaLlegada);
    }
}
