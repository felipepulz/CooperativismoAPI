package br.com.cooperativismo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "idPauta")
    @Column(nullable = true)
    List<Voto> votos;

    @NotNull
    @Size(max = 100)
    String nomePauta;

    @Size(max = 1000)
    String descricaoPauta;

    @NotNull
    @Enumerated(EnumType.STRING)
    Situacao situacao;

    @NotNull
    LocalDateTime dataInicioPauta;

    @NotNull
    LocalDateTime dataFimPauta;

    public Pauta() {
    }

    public Pauta(@NotNull @Size(max = 100) String nomePauta, @Size(max = 1000) String descricaoPauta, @NotNull Situacao situacao, @NotNull LocalDateTime dataInicioPauta, @NotNull LocalDateTime dataFimPauta) {
        this.nomePauta = nomePauta;
        this.descricaoPauta = descricaoPauta;
        this.situacao = situacao;
        this.dataInicioPauta = dataInicioPauta;
        this.dataFimPauta = dataFimPauta;
    }

    public enum Situacao {
        ABERTA("ABERTA"), ENCERRADA("ENCERRADA");
        private final String valor;
        Situacao(String valorOpcao) {
            valor = valorOpcao;
        }
        public String getValor() {
            return valor;
        }
    }

}
