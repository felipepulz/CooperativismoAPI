package br.com.cooperativismo.application.request;

import br.com.cooperativismo.domain.entity.Voto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VotoRequest {

    String nomePauta;
    Voto.OpcoesVoto voto;
    String cpf;

    public VotoRequest(String nomePauta, Voto.OpcoesVoto voto, String cpf) {
        this.nomePauta = nomePauta;
        this.voto = voto;
        this.cpf = cpf;
    }
}
