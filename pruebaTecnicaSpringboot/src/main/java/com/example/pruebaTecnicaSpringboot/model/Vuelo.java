package com.example.pruebaTecnicaSpringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vuelo {
    int id;
    String nombreVuelo;
    String empresa;
    String lugarSalida;
    String lugarLlegada;
    LocalDate fechaSalida;
    LocalDate fechaLlegada;
}
