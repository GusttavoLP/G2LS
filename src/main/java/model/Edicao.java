package model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Edicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(mappedBy = "edicao")
    private List<Espaco> espacos;
}