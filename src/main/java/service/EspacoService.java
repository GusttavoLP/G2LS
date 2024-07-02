package service;


import model.Espaco;
import model.Edicao;
import repository.EspacoRepository;
import repository.EdicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    @Autowired
    private EspacoRepository espacoRepository;

    @Autowired
    private EdicaoRepository edicaoRepository;

    public List<Espaco> getAllEspacos() {
        return espacoRepository.findAll();
    }

    public Optional<Espaco> getEspacoById(Long id) {
        return espacoRepository.findById(id);
    }

    public Espaco createEspaco(Espaco espaco) {
        validateEspaco(espaco);
        return espacoRepository.save(espaco);
    }

    public void deleteEspacoById(Long id) {
        espacoRepository.deleteById(id);
    }

    private void validateEspaco(Espaco espaco) {
        if (espaco.getNome() == null || espaco.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do espaço não pode ser vazio");
        }
        if (espaco.getCapacidade() == null || espaco.getCapacidade() <= 0) {
            throw new IllegalArgumentException("A capacidade do espaço deve ser maior que zero");
        }
        if (espaco.getEdicao() == null || !edicaoRepository.existsById(espaco.getEdicao().getId())) {
            throw new IllegalArgumentException("Edição associada inválida");
        }
    }
}
