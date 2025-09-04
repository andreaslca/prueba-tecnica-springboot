package com.example.pruebaTecnicaSpringboot.service;

import com.example.pruebaTecnicaSpringboot.dto.VueloDto;
import com.example.pruebaTecnicaSpringboot.model.Vuelo;
import com.example.pruebaTecnicaSpringboot.repository.VueloRepository;
import com.example.pruebaTecnicaSpringboot.utils.FechaUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VueloService {
    private final VueloRepository repository;

    public VueloService(VueloRepository repository) {
        this.repository = repository;
    }

    //listar por fecha de salida (findAll porque el menor a mayor es predeterminado)
    public List<VueloDto> filterAll(Map<String, String> filtroVuelo) {
        return repository.findAll().stream()
                .map(v -> new VueloDto(v.getId(),
                        v.getNombreVuelo(),
                        v.getEmpresa(),
                        v.getLugarSalida(),
                        v.getLugarLlegada(),
                        v.getFechaSalida(),
                        v.getFechaLlegada(),
                        FechaUtils.duracionVuelo(v.getFechaSalida(), v.getFechaLlegada())))
                .collect(Collectors.toList());
    }

    public Optional<VueloDto> filterById(int id) {
        return repository.findById(id).map(v -> new VueloDto(v.getId(),
                v.getNombreVuelo(),
                v.getEmpresa(),
                v.getLugarSalida(),
                v.getLugarLlegada(),
                v.getFechaSalida(),
                v.getFechaLlegada(),
                FechaUtils.duracionVuelo(v.getFechaSalida(), v.getFechaLlegada())));
    }
//Cuando actualizas un vuelo los campos no puedan estar en blanco ni nulos
    public boolean nullBlank(String campo) {
        return campo == null ? true : campo.isBlank();
    }

    public String saveVuelo(Vuelo vuelo) {
        if (nullBlank(vuelo.getNombreVuelo())) {

            return "El vuelo no existe";
        }
        if (nullBlank(vuelo.getLugarSalida())) {

            return "Necesita escoger un lugar de salida";
        }
        if (nullBlank(vuelo.getLugarLlegada())) {

            return "Necesita escoger un destino";
        }
        if (nullBlank(vuelo.getEmpresa())) {
            return "Necesita seleccionar la aerolínea";
        }
        if (!FechaUtils.fechasVuelo(vuelo.getFechaSalida(), vuelo.getFechaLlegada())) {
            return "No ha seleccionado una fecha";
        }
        repository.saveVuelo(vuelo);
        return "¡Ha reservado su viaje!";
    }

    public String updateVuelo(int id, Vuelo actualizarVuelo) {
        Optional<Vuelo> optionalVuelo = repository.findById(id);

        if (optionalVuelo.isPresent()) {
            if (!FechaUtils.fechasVuelo(actualizarVuelo.getFechaSalida(), actualizarVuelo.getFechaLlegada())) {
                return "No existe eel vuelo que busca con esas fechas.";
            }
            repository.updateVuelo(id, actualizarVuelo);
            return "Vuelo actualizado correctamente.";
        } else {
            return "No se encontró un vuelo con el ID: " + id;
        }
    }

    public String deleteVuelo(int id) {
        repository.deleteVuelo(id);
        return "Vuelo cancelado.";
    }

    //filtros opcionales (mirar ejercicio viernes 29)
    public List<Vuelo> filtrarNombreAerolinea(String empresa) {
        return repository.findAll()
                .stream()
                .filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .collect(Collectors.toList());
    }

    public List<Vuelo> filtrarDestino(String ciudad) {
        return repository.findAll()
                .stream()
                .filter(v -> ciudad == null || v.getLugarLlegada().equalsIgnoreCase(ciudad))
                .collect(Collectors.toList());
    }

    public List<Vuelo> filtrarFechaSalida(LocalDate fechaSalida) {
        return repository.findAll()
                .stream()
                .filter(v -> fechaSalida == null || v.getFechaSalida().isEqual(fechaSalida))
                .collect(Collectors.toList());
    }
}
