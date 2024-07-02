package repository;

import model.Edicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdicaoRepository extends JpaRepository<Edicao, Long> {
}