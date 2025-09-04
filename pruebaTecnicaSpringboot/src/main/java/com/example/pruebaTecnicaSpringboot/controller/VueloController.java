package com.example.pruebaTecnicaSpringboot.controller;

import com.example.pruebaTecnicaSpringboot.dto.VueloDto;
import com.example.pruebaTecnicaSpringboot.model.Vuelo;
import com.example.pruebaTecnicaSpringboot.service.VueloService;
import com.example.pruebaTecnicaSpringboot.utils.FechaUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
    private final VueloService service;

    public VueloController(VueloService service) {
        this.service = service;
    }
//obtener toda la lista de vuelos
    @GetMapping("/listavuelos")
    public List<VueloDto> getAllVuelos() {

        List<VueloDto> vuelos = service.filterAll(Collections.emptyMap());
        vuelos.sort(Comparator.comparing(VueloDto::getFechaSalida));
        return vuelos;
    }
//obtener el id que se quiera buscar
    @GetMapping("/idvuelo/{id}")
    public ResponseEntity<VueloDto> filterById(@PathVariable int id) {
        Optional<VueloDto> vuelo = service.filterById(id);
        if (vuelo.isPresent()) {
            return ResponseEntity.ok(vuelo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//guardar un nuevo vuelo
    @PostMapping("/reservarvuelo")
    public String saveVuelo(@RequestBody Vuelo vuelo) {
        return service.saveVuelo(vuelo);
    }
//actualizar los datos de un vuelo
    @PutMapping("/actualizarvuelo/{id}")
    public String updateVuelo(@PathVariable int id, @RequestBody Vuelo actualizarVuelo) {
        return service.updateVuelo(id, actualizarVuelo);
    }
//borrar un vuelo al proporcionar un id
    @DeleteMapping("/eliminarvuelo/{id}")
    public String deleteVuelo(@PathVariable int id) {
        return service.deleteVuelo(id);
    }
    //filtros opcionales por query params
    @GetMapping("/filtrovuelo")
    public List<VueloDto> getVuelosFiltrados(
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String lugarLlegada,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida) {

        return service.filtrarNombreAerolinea(empresa).stream()
                .filter(v -> lugarLlegada == null || v.getLugarLlegada().equalsIgnoreCase(lugarLlegada))
                .filter(v -> fechaSalida == null || v.getFechaSalida().isEqual(fechaSalida))
                .map(v -> new VueloDto(
                        v.getId(),
                        v.getNombreVuelo(),
                        v.getEmpresa(),
                        v.getLugarSalida(),
                        v.getLugarLlegada(),
                        v.getFechaSalida(),
                        v.getFechaLlegada(),
                        FechaUtils.duracionVuelo(v.getFechaSalida(), v.getFechaLlegada())))
                .sorted(Comparator.comparing(VueloDto::getFechaSalida))
                .collect(Collectors.toList());
    }
}



