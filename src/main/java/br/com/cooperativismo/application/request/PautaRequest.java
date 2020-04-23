package br.com.cooperativismo.application.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PautaRequest {

    String nomePauta;
    String descricaoPauta;
    int expirarPauta;

    public PautaRequest(String nomePauta, String descricaoPauta, int expirarPauta) {
        this.nomePauta = nomePauta;
        this.descricaoPauta = descricaoPauta;
        this.expirarPauta = expirarPauta;
    }
}
