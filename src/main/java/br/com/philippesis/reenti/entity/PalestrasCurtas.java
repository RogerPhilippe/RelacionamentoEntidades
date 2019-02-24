package br.com.philippesis.reenti.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor

@Entity
public class PalestrasCurtas  implements Serializable {

private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataHora;

    @Column(nullable = false)
    private int duracao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Local local;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "palestras_curtas_palestrante_temporario",
            joinColumns = @JoinColumn(name = "palestras_curtas_id"),
            inverseJoinColumns = @JoinColumn(name = "palestrante_temporario_id"))
    private List<PalestranteTemporario> palestranteTemporario;

}
