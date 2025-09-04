package com.example.pruebaTecnicaSpringboot.repository;

import com.example.pruebaTecnicaSpringboot.model.Vuelo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VueloRepository {
    //lista vuelos
    private List<Vuelo> vuelos = new ArrayList<>();

    public VueloRepository() {
        vuelos.add(new Vuelo(1, "H001-I", "Iberia", "Madrid", "Buenos Aires", LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 11)));
        vuelos.add(new Vuelo(2, "ND10-R", "Ryanair", "Barcelona", "Berlín", LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 3)));
        vuelos.add(new Vuelo(3, "CT30-V", "Vueling", "Lisboa", "París", LocalDate.of(2025, 9, 5), LocalDate.of(2025, 9, 5)));
        vuelos.add(new Vuelo(4, "LK89-T", "Transavia", "Budapest", "Ámsterdam", LocalDate.of(2025, 10, 16), LocalDate.of(2025, 10, 16)));
        vuelos.add(new Vuelo(5, "BY90-A", "AirEuropa", "Frankfurt", "Oporto", LocalDate.of(2025, 11, 23), LocalDate.of(2025, 11, 23)));
        vuelos.add(new Vuelo(6, "DS20-I", "Iberia", "Londres", "Madrid", LocalDate.of(2025, 12, 25), LocalDate.of(2025, 12, 25)));
        vuelos.add(new Vuelo(7, "ND10-R", "Ryanair", "Vigo", "Roma", LocalDate.of(2026, 1, 13), LocalDate.of(2026, 1, 13)));
        vuelos.add(new Vuelo(8, "CT30-V", "Vueling", "Mallorca", "Atenas", LocalDate.of(2026, 2, 28), LocalDate.of(2026, 2, 28)));
        vuelos.add(new Vuelo(9, "FI70-E", "Emirates", "París", "Tokyo", LocalDate.of(2026, 3, 31), LocalDate.of(2026, 4, 1)));
        vuelos.add(new Vuelo(10, "DS21-I", "Iberia", "Dublín", "Santiago", LocalDate.of(2026, 4, 14), LocalDate.of(2026, 4, 14)));
    }

//CRUD
    public List<Vuelo> findAll() {
        return vuelos;
    }

    public Optional<Vuelo> findById(int id) {
        return vuelos.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    public Vuelo saveVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
        return vuelo;
    }

    public void updateVuelo(int id, Vuelo vueloActualizado) {
        findById(id).ifPresent(v -> {
            v.setNombreVuelo(vueloActualizado.getNombreVuelo());
            v.setEmpresa(vueloActualizado.getEmpresa());
            v.setLugarSalida(vueloActualizado.getLugarSalida());
            v.setLugarLlegada(vueloActualizado.getLugarLlegada());
            v.setFechaSalida(vueloActualizado.getFechaSalida());
            v.setFechaLlegada(vueloActualizado.getFechaLlegada());
        });
    }

    public void deleteVuelo(int id) {
        vuelos.removeIf(v -> v.getId() == id);
    }
}
