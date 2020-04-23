package br.com.cooperativismo.data;

import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.infrastructure.util.SystemUtil;
import java.util.List;


public class PautaData {

    public Pauta getPauta() {
        Pauta pauta = new Pauta();
        pauta.setId(1);
        pauta.setSituacao(Pauta.Situacao.ABERTA);
        pauta.setDataFimPauta(SystemUtil.getDataAtualMaisMinutos(1));
        pauta.setDataInicioPauta(SystemUtil.getDataAtual());
        pauta.setNomePauta("Algum nome");
        pauta.setDescricaoPauta("Alguma descrição");
        pauta.setVotos(List.of());
        return pauta;
    }

}
