package service;

import model.Atividade;
import model.Espaco;
import repository.AtividadeRepository;
import repository.EspacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private EspacoRepository espacoRepository;

    public List<Atividade> getAllAtividades() {
        return atividadeRepository.findAll();
    }

    public Optional<Atividade> getAtividadeById(Long id) {
        return atividadeRepository.findById(id);
    }

    public Atividade createAtividade(Atividade atividade) {
        validateAtividade(atividade);
        return atividadeRepository.save(atividade);
    }

    public void deleteAtividadeById(Long id) {
        atividadeRepository.deleteById(id);
    }

    private void validateAtividade(Atividade atividade) {
        if (atividade.getNome() == null || atividade.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da atividade não pode ser vazio");
        }
        if (atividade.getDescricao() == null || atividade.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição da atividade não pode ser vazia");
        }
        if (atividade.getData() == null) {
            throw new IllegalArgumentException("A data da atividade não pode ser vazia");
        }
        if (atividade.getEspaco() == null || !espacoRepository.existsById(atividade.getEspaco().getId())) {
            throw new IllegalArgumentException("Espaço associado inválido");
        }
    }
}
