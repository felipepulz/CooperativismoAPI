package br.com.cooperativismo.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
@Getter
@Setter
@Table(name = "voto", indexes = {@Index(columnList = "idPauta, cpf", unique = true)})
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @Size(max = 11)
    String cpf;

    @NotNull
    @Enumerated(EnumType.STRING)
    OpcoesVoto voto;

    @Column(nullable = true)
    int idPauta;

    public Voto() {
    }

    public Voto(@NotNull @Size(max = 11) String cpf, @NotNull OpcoesVoto voto, int idPauta) {
        this.cpf = cpf;
        this.voto = voto;
        this.idPauta = idPauta;
    }

    public enum OpcoesVoto {
        SIM("SIM"), NAO("NAO");
        private final String valor;
        OpcoesVoto(String valorOpcao) {
            valor = valorOpcao;
        }
        public String getValor() {
            return valor;
        }
    }

}
