package br.com.cooperativismo.infrastructure.services;

import br.com.cooperativismo.application.request.VotoRequest;
import br.com.cooperativismo.application.response.VotoResponse;
import br.com.cooperativismo.data.PautaData;
import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.domain.entity.Voto;
import br.com.cooperativismo.domain.gateway.UserInfoClient;
import br.com.cooperativismo.domain.gateway.response.ResponseClient;
import br.com.cooperativismo.domain.repository.PautaRepository;
import br.com.cooperativismo.domain.repository.VotoRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VotoServiceImplTest {

    private VotoServiceImpl votoService;
    private VotoRepository votoRepository;
    private PautaRepository pautaRepository;
    private UserInfoClient userInfoClient;

    @Before
    public void initialize() {
        votoRepository = mock(VotoRepository.class);
        pautaRepository = mock(PautaRepository.class);
        userInfoClient = mock(UserInfoClient.class);
        votoService = new VotoServiceImpl(votoRepository, pautaRepository, userInfoClient);
    }

    @Test
    public void givenAnEmptyVotoNameShouldError() {
        VotoResponse response = votoService.saveVoto(new VotoRequest("", Voto.OpcoesVoto.SIM, "84868050079"));
        assertTrue(response.getResponse().equalsIgnoreCase("Nome da Pauta deve ser informado."));
    }

    @Test
    public void givenAnEmptyVotoCpfShouldError() {
        VotoResponse response = votoService.saveVoto(new VotoRequest("", Voto.OpcoesVoto.SIM, ""));
        assertTrue(response.getResponse().equalsIgnoreCase("CPF inválido."));
    }

    @Test
    public void givenNamePautaNotRegisteredShouldReturnError() {
        when(pautaRepository.countByNomePauta("Algum nome")).thenReturn((long) 0);
        VotoResponse response = votoService.saveVoto(new VotoRequest("Algum nome", Voto.OpcoesVoto.SIM, "84868050079"));
        assertTrue(response.getResponse().equalsIgnoreCase("Nome da Pauta não encontrado."));
    }

    @Test
    public void givenACpfThatHasAlreadyVotedOnTheSamePautaShouldReturnError() {
        when(pautaRepository.countByNomePauta("Algum nome")).thenReturn((long) 1);
        when(pautaRepository.findPautaByNomePauta("Algum nome")).thenReturn(new PautaData().getPauta());
        when(votoRepository.countByCpfAndIdPauta("84868050079", 1)).thenReturn((long) 1);
        VotoResponse response = votoService.saveVoto(new VotoRequest("Algum nome", Voto.OpcoesVoto.SIM, "84868050079"));
        assertTrue(response.getResponse().equalsIgnoreCase("CPF 84868050079 já votou nesta Pauta."));
    }

    @Test
    public void givenValidDataThisPautaShouldReturnError() {
        when(pautaRepository.countByNomePauta("Algum nome")).thenReturn((long) 1);
        Pauta pauta = new PautaData().getPauta();
        pauta.setSituacao(Pauta.Situacao.ENCERRADA);
        when(pautaRepository.findPautaByNomePauta("Algum nome")).thenReturn(pauta);
        when(votoRepository.countByCpfAndIdPauta("84868050079", 1)).thenReturn((long) 0);
        VotoResponse response = votoService.saveVoto(new VotoRequest("Algum nome", Voto.OpcoesVoto.SIM, "84868050079"));
        assertTrue(response.getResponse().equalsIgnoreCase("Pauta já está encerrada."));
    }

    @Test
    public void givenValidDataTheCpfIsInvalidForVotingMustReturnError() {
        when(pautaRepository.countByNomePauta("Algum nome")).thenReturn((long) 1);
        when(pautaRepository.findPautaByNomePauta("Algum nome")).thenReturn(new PautaData().getPauta());
        when(votoRepository.countByCpfAndIdPauta("84868050079", 1)).thenReturn((long) 0);
        when(userInfoClient.checkCpf("84868050079")).thenReturn(new ResponseClient("UNABLE_TO_VOTE"));
        VotoResponse response = votoService.saveVoto(new VotoRequest("Algum nome", Voto.OpcoesVoto.SIM, "84868050079"));
        assertTrue(response.getResponse().equalsIgnoreCase("CPF inválido (UNABLE_TO_VOTE)."));
    }

}
