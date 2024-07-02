package model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;

    @OneToMany(mappedBy = "evento")
    private List<Edicao> edicoes;
}