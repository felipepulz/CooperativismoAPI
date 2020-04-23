package br.com.cooperativismo.infrastructure.services;

import br.com.cooperativismo.application.error.NotValidException;
import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.domain.repository.PautaRepository;
import br.com.cooperativismo.domain.services.PautaService;
import br.com.cooperativismo.infrastructure.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta savePauta(PautaRequest request) {

        if(SystemUtil.nullBlank(request.getNomePauta())){
            throw new NotValidException("Nome da Pauta deve ser informado.");
        }

        if(SystemUtil.nullBlank(request.getDescricaoPauta())){
            throw new NotValidException("Descrição da Pauta deve ser informado.");
        }

        long quantidadePauta = this.pautaRepository.countByNomePauta(request.getNomePauta());
        if(quantidadePauta > 0){
            throw new NotValidException("Nome da Pauta já registrado.");
        }

        return this.pautaRepository.save(
                new Pauta(
                        request.getNomePauta(),
                        request.getDescricaoPauta(),
                        Pauta.Situacao.ABERTA,
                        SystemUtil.getDataAtual(),
                        request.getExpirarPauta() > 0 ?
                                SystemUtil.getDataAtualMaisMinutos(request.getExpirarPauta()) :
                                SystemUtil.getDataAtualMaisMinutos(1)
                )
        );
    }

}
