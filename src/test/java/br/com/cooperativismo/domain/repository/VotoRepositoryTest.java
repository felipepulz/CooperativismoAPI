package br.com.cooperativismo.domain.repository;

import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.domain.entity.Voto;
import br.com.cooperativismo.infrastructure.util.SystemUtil;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.Assert.*;

@DataJpaTest
@DBRider
@DataSet(cleanBefore = true, value = {"dataset/voto.yml"})
public class VotoRepositoryTest {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Test
    public void mustSucceedSaveVoto() {

        Pauta pauta = pautaRepository.save(
                new Pauta(
                        "Algum nome",
                        "Alguma descrição",
                        Pauta.Situacao.ABERTA,
                        SystemUtil.getDataAtual(),
                        SystemUtil.getDataAtualMaisMinutos(1)
                )
        );

        Voto voto = votoRepository.save(
                new Voto("84868050079", Voto.OpcoesVoto.SIM, pauta.getId())
        );
        assertNotNull(voto);
        assertTrue(voto.getId() > 0);
    }

}
