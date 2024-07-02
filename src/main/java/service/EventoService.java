package service;

import model.Evento;
import repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento createEvento(Evento evento) {
        validateEvento(evento);
        // Adicione lógica de negócio aqui, se necessário
        return eventoRepository.save(evento);
    }

    public void deleteEventoById(Long id) {
        eventoRepository.deleteById(id);
    }

    private void validateEvento(Evento evento) {
        if (evento.getNome() == null || evento.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do evento não pode ser vazio");
        }
        if (evento.getDataInicio() == null || evento.getDataFim() == null) {
            throw new IllegalArgumentException("As datas de início e fim do evento não podem ser nulas");
        }
        if (evento.getDataInicio().after(evento.getDataFim())) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim");
        }
    }
}
