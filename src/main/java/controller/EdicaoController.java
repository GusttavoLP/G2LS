package controller;

import model.Edicao;
import service.EdicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edicoes")
public class EdicaoController {

    @Autowired
    private EdicaoService edicaoService;

    @GetMapping
    public List<Edicao> getAllEdicoes() {
        return edicaoService.getAllEdicoes();
    }

    @PostMapping
    public Edicao createEdicao(@RequestBody Edicao edicao) {
        return edicaoService.createEdicao(edicao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Edicao> getEdicaoById(@PathVariable Long id) {
        return edicaoService.getEdicaoById(id)
                .map(edicao -> ResponseEntity.ok().body(edicao))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdicaoById(@PathVariable Long id) {
        return edicaoService.getEdicaoById(id)
                .map(edicao -> {
                    edicaoService.deleteEdicaoById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
