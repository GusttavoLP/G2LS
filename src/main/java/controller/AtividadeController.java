package controller;

import model.Atividade;
import service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Atividade> getAllAtividades() {
        return atividadeService.getAllAtividades();
    }

    @PostMapping
    public Atividade createAtividade(@RequestBody Atividade atividade) {
        return atividadeService.createAtividade(atividade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> getAtividadeById(@PathVariable Long id) {
        return atividadeService.getAtividadeById(id)
                .map(atividade -> ResponseEntity.ok().body(atividade))
                .orElse(ResponseEntity.notFound().build());
    }

    /* 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividadeById(@PathVariable Long id) {
        return atividadeService.getAtividadeById(id)
                .map(atividade -> {
                    atividadeService.deleteAtividadeById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    */
}
