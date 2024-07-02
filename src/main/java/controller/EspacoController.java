package controller;

import model.Espaco;
import service.EspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacos")
public class EspacoController {

    @Autowired
    private EspacoService espacoService;

    @GetMapping
    public List<Espaco> getAllEspacos() {
        return espacoService.getAllEspacos();
    }

    @PostMapping
    public Espaco createEspaco(@RequestBody Espaco espaco) {
        return espacoService.createEspaco(espaco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Espaco> getEspacoById(@PathVariable Long id) {
        return espacoService.getEspacoById(id)
                .map(espaco -> ResponseEntity.ok().body(espaco))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspacoById(@PathVariable Long id) {
        return espacoService.getEspacoById(id)
                .map(espaco -> {
                    espacoService.deleteEspacoById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
