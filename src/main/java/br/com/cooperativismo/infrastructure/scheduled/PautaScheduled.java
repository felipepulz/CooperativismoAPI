package br.com.cooperativismo.infrastructure.scheduled;

import br.com.cooperativismo.application.events.KafkaEvent;
import br.com.cooperativismo.application.events.KafkaEventBroadcast;
import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.domain.repository.PautaRepository;
import br.com.cooperativismo.infrastructure.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class PautaScheduled {

    private PautaRepository pautaRepository;
    private final KafkaEventBroadcast kafkaEventBroadcast;

    @Autowired
    public PautaScheduled(PautaRepository pautaRepository, KafkaEventBroadcast kafkaEventBroadcast) {
        this.pautaRepository = pautaRepository;
        this.kafkaEventBroadcast = kafkaEventBroadcast;
    }

    private final long SEGUNDO = 1000;

    @Scheduled(fixedRate = SEGUNDO * 5)
    public void checkedBySecunds() {
        List<Pauta> list = this.pautaRepository.findAllByDataFimPautaBeforeAndSituacaoEquals(SystemUtil.getDataAtual(), Pauta.Situacao.ABERTA);
        list.forEach(pauta -> {
            pauta.setSituacao(Pauta.Situacao.ENCERRADA);
            this.pautaRepository.save(pauta);
            var event = new KafkaEvent(pauta.getNomePauta());
            event.registerData(pauta);
            kafkaEventBroadcast.fireEvent(event);
        });
    }

}
