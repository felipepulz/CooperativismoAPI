package br.com.cooperativismo.infrastructure.services;

import br.com.cooperativismo.application.request.VotoRequest;
import br.com.cooperativismo.application.response.VotoResponse;
import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.domain.entity.Voto;
import br.com.cooperativismo.domain.gateway.UserInfoClient;
import br.com.cooperativismo.domain.gateway.response.ResponseClient;
import br.com.cooperativismo.domain.repository.PautaRepository;
import br.com.cooperativismo.domain.repository.VotoRepository;
import br.com.cooperativismo.domain.services.VotoService;
import br.com.cooperativismo.infrastructure.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    private UserInfoClient validarCpf;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository, PautaRepository pautaRepository, UserInfoClient validarCpf) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
        this.validarCpf = validarCpf;
    }

    @Override
    public VotoResponse saveVoto(VotoRequest request) {

        if(SystemUtil.nullBlank(request.getVoto().getValor())){
            return new VotoResponse("Votos devem ser de opção: SIM ou NAO.");
        }

        if(SystemUtil.nullBlank(request.getCpf())){
            return new VotoResponse("CPF inválido.");
        }

        if(SystemUtil.nullBlank(request.getNomePauta())){
            return new VotoResponse("Nome da Pauta deve ser informado.");
        }

        long quantidadeRegistro = this.pautaRepository.countByNomePauta(request.getNomePauta());
        if(quantidadeRegistro == 0){
            return new VotoResponse("Nome da Pauta não encontrado.");
        }

        Pauta pauta = this.pautaRepository.findPautaByNomePauta(request.getNomePauta());
        long quantidadeVotos = this.votoRepository.countByCpfAndIdPauta(request.getCpf(), pauta.getId());
        if(quantidadeVotos != 0){
            return new VotoResponse("CPF ".concat(request.getCpf().concat(" já votou nesta Pauta.")));
        }

        if(pauta.getSituacao() == Pauta.Situacao.ENCERRADA){
            return new VotoResponse("Pauta já está encerrada.");
        }

        ResponseClient response = this.validarCpf.validarCpf(request.getCpf());
        if(response.getStatus().equalsIgnoreCase("UNABLE_TO_VOTE")){
            return new VotoResponse("CPF inválido (".concat(response.getStatus().concat(").")));
        }

        this.votoRepository.save(new Voto(request.getCpf(), request.getVoto(), pauta.getId()));
        return new VotoResponse("Voto registrado com sucesso!");
    }

}
