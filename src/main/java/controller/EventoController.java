package controller;

import model.Evento;
import service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoService.createEvento(evento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(evento -> ResponseEntity.ok().body(evento))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(evento -> {
                    eventoService.deleteEventoById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
