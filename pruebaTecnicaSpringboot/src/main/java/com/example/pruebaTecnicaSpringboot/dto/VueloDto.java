package com.example.pruebaTecnicaSpringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VueloDto {
    int id;
    String nombreVuelo;
    String empresa;
    String lugarSalida;
    String lugarLlegada;
    LocalDate fechaSalida;
    LocalDate fechaLlegada;
    private long duracionVuelo;
}
