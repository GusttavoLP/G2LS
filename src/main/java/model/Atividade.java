package model;

import lombok.Data;
import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String descricao;
    private Date data;
    private Time horarioInicial;
    private Time horarioFinal;

    @OneToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    @ManyToMany(mappedBy = "atividades")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "atividades")
    private List<Edicao> edicao;
}
