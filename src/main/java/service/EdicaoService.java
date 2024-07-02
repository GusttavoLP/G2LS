package service;

import model.Edicao;
import model.Evento;
import repository.EdicaoRepository;
import repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdicaoService {

    @Autowired
    private EdicaoRepository edicaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public List<Edicao> getAllEdicoes() {
        return edicaoRepository.findAll();
    }

    public Optional<Edicao> getEdicaoById(Long id) {
        return edicaoRepository.findById(id);
    }

    public Edicao createEdicao(Edicao edicao) {
        validateEdicao(edicao);
        return edicaoRepository.save(edicao);
    }

    public void deleteEdicaoById(Long id) {
        edicaoRepository.deleteById(id);
    }

    private void validateEdicao(Edicao edicao) {
        if (edicao.getNumero() == null) {
            throw new IllegalArgumentException("O número da edição não pode ser nulo");
        }
        if (edicao.getEvento() == null || !eventoRepository.existsById(edicao.getEvento().getId())) {
            throw new IllegalArgumentException("Evento associado inválido");
        }
    }
}
